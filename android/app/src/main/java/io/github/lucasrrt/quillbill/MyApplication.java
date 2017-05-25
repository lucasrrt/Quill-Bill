package io.github.lucasrrt.quillbill;

import android.app.Application;

/**
 * Created by ricarte on 25/05/17.
 */

public class MyApplication extends Application {
    private String token, port, url_pattern;

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public void setPort(String port){
        this.port = port;
    }

    public String getPort(){
        return this.port;
    }

    public void setUrl_pattern(String url_pattern){
        this.url_pattern = url_pattern;
    }

    public String getUrl_pattern(){
        return this.url_pattern;
    }
}
