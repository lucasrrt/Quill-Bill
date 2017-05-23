package io.github.lucasrrt.quillbill;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by ricarte on 22/05/17.
 */

public class AJAXCall {
    interface HTTPCallback<T>{
        public void call(T t);
    }
    static public void http(String method, String path, JSONObject params, HTTPCallback<String> callback, HTTPCallback<String> callbackError){
        AsyncTask task = new AsyncTask() {
            private String str;
            private int code;
            @Override
            protected Object doInBackground(Object[] objects) {
                try{
                    String newPath = path;
                    if(params != null){
                        newPath += "?";
                        Iterator<?> keys = params.keys();
                        while(keys.hasNext()){
                            String key = (String)keys.next();
                            newPath+= URLEncoder.encode(key, StandardCharsets.UTF_8.toString())+"="+URLEncoder.encode(params.getString(key), StandardCharsets.UTF_8.toString());
                            if(keys.hasNext())
                                newPath+="&";
                        }
                    }
                    URL url = new URL(newPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod(method);
                    conn.setRequestProperty("Content-Type","application/json");
                    conn.connect();

                    code = conn.getResponseCode();

                    InputStream is = conn.getInputStream();
                    str = convertStreamToString(is);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object object){
                if (code == 200){
                    callback.call(str);
                } else {
                    callbackError.call("ERRO: "+code);
                }
            }
        };
        task.execute();
    }
    static String convertStreamToString(InputStream is){
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    static public void get(String path, JSONObject params, HTTPCallback<String> callback, HTTPCallback<String> callbackError){
        http("GET", path, params, callback, callbackError);
    }
    static public void post(String path, JSONObject params, HTTPCallback<String> callback, HTTPCallback<String> callbackError){
        http("POST", path, params, callback, callbackError);
    }
    static public void put(String path, JSONObject params, HTTPCallback<String> callback, HTTPCallback<String> callbackError){
        http("PUT", path, params, callback, callbackError);
    }
    static public void delete(String path, JSONObject params, HTTPCallback<String> callback, HTTPCallback<String> callbackError){
        http("DELETE", path, params, callback, callbackError);
    }
}
