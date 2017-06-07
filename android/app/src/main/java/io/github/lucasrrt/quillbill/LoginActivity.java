package io.github.lucasrrt.quillbill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Intent intent;
    String port, url_pattern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        port = "4567";
        url_pattern = "http://192.168.0.21:"+port+"/";

        ((MyApplication) this.getApplication()).setPort(port);
        ((MyApplication) this.getApplication()).setUrl_pattern(url_pattern);
    }

    public void SignIn (View view){
        AJAXCall.HTTPCallback<String> callback = (data)->{
            try{
                //JSONArray array = new JSONArray(data);
                ((MyApplication) this.getApplication()).setToken(data);
                intent = new Intent(this, MainActivity.class);
                //String strName = array.getJSONObject(0).getString("id");
                intent.putExtra("USERNAME", username.getText().toString());
                startActivity(intent);
                finish();
            } catch (Exception e){
                e.printStackTrace();
            }
        };
        AJAXCall.HTTPCallback<String> callbackError = (data)->{
            try {
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                e.printStackTrace();
            }
        };
        String url = url_pattern + "authentications";
        try {
            JSONObject login = new JSONObject();
            login.put("username", username.getText().toString());
            login.put("password", password.getText().toString());
            AJAXCall.post(url, login, callback, callbackError);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void SignUp (View view){
        AJAXCall.HTTPCallback<String> callback = (data)->{
            try{
//                JSONArray array = new JSONArray(data);
//                intent = new Intent(this, MainActivity.class);
//                String strName = array.getJSONObject(0).getString("id");
//                intent.putExtra("ID", strName);
//                startActivity(intent);
//                finish();
                Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                e.printStackTrace();
            }
        };
        AJAXCall.HTTPCallback<String> callbackError = (data)->{
            try {
                Toast.makeText(this, "Cadastro inválido", Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                e.printStackTrace();
            }
        };
        String url = url_pattern + "users";
        try {
            JSONObject login = new JSONObject();
            login.put("username", username.getText().toString());
            login.put("password", password.getText().toString());
            AJAXCall.post(url, login, callback, callbackError);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void Force(View view){
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("ID", "DefaultUser");
        startActivity(intent);
        finish();
    }
}
