package io.github.lucasrrt.quillbill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String userId, url_pattern, port;
    TextView print, bills_count;
    ArrayList<JSONObject> bills;
    ListView bills_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        port = "4567";
        url_pattern = "http://192.168.0.21:"+port+"/";

        print = (TextView) findViewById(R.id.print);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userId = null;
            } else {
                userId= extras.getString("ID");
            }
        } else {
            userId= (String) savedInstanceState.getSerializable("ID");
        }
        helloUser();

        bills = new ArrayList<>();

        bills_list = (ListView) findViewById(R.id.output);
        bills_count = (TextView) findViewById(R.id.bills_count);
        AJAXCall.HTTPCallback<String> callback = (data)->{
            try{
                JSONArray array = new JSONArray(data);
                String[] stringArray = new String[array.length()];
                for(int t = 0; t<array.length(); t++){
                    stringArray[t] = array.getJSONObject(t).getString("name");
                }
                bills_list.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, android.R.id.text1, stringArray));

            } catch (Exception e){
                e.printStackTrace();
            }
        };
        AJAXCall.HTTPCallback<String> callbackError = (data)->{
            try {
                Toast.makeText(this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }catch (Exception e){ }
        };

        String url = url_pattern + "bills/" + userId;
        AJAXCall.get(url, null, callback, callbackError);

    }

    public void helloUser(){
        AJAXCall.HTTPCallback<String> callback = (data)->{
            try{
                JSONArray array = new JSONArray(data);
                String username = array.getJSONObject(0).getString("username");
                print.setText("Olá "+username);
            } catch (Exception e){
                e.printStackTrace();
            }
        };

        AJAXCall.HTTPCallback<String> callbackError = (data)->{
            try{
                Toast.makeText(this, "Erro na conexão", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                e.printStackTrace();
            }
        };

        String url = url_pattern+"users/"+userId;
        AJAXCall.get(url, null, callback, callbackError);
    }
}
