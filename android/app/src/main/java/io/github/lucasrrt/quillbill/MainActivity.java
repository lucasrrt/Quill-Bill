package io.github.lucasrrt.quillbill;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String userId, url_pattern, port;
    TextView print, bills_count;
    ArrayList<JSONObject> bills;
    ListView bills_list;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        port = "4567";
        url_pattern = "http://192.168.0.21:" + port + "/";

        print = (TextView) findViewById(R.id.print);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                userId = null;
            } else {
                userId = extras.getString("ID");
            }
        } else {
            userId = (String) savedInstanceState.getSerializable("ID");
        }
        helloUser();

        bills = new ArrayList<>();

        bills_list = (ListView) findViewById(R.id.output);
        bills_count = (TextView) findViewById(R.id.bills_count);
        AJAXCall.HTTPCallback<String> callback = (data) -> {
            try {
                JSONArray array = new JSONArray(data);
                String[] stringArray = new String[array.length()];
                for (int t = 0; t < array.length(); t++) {
                    stringArray[t] = array.getJSONObject(t).getString("name");
                }
                bills_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray));

                //STOP HERE
                bills_list.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id)->{
                        Intent intent = new Intent(MainActivity.this, BillActivity.class);
                        intent.putExtra("BILL", bills.get(position).toString());
                        startActivity(intent);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        AJAXCall.HTTPCallback<String> callbackError = (data) -> {
            try {
                Toast.makeText(this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
            }
        };

        String url = url_pattern + "bills/" + userId;
        AJAXCall.get(url, null, callback, callbackError);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void helloUser() {
        AJAXCall.HTTPCallback<String> callback = (data) -> {
            try {
                JSONArray array = new JSONArray(data);
                String username = array.getJSONObject(0).getString("username");
                print.setText("Olá " + username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        AJAXCall.HTTPCallback<String> callbackError = (data) -> {
            try {
                Toast.makeText(this, "Erro na conexão", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        String url = url_pattern + "users/" + userId;
        AJAXCall.get(url, null, callback, callbackError);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
