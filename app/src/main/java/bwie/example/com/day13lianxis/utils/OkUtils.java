package bwie.example.com.day13lianxis.utils;


import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkUtils {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = (String) msg.obj;
            lisener.success(data);
        }

    };

    private OkListener lisener;

    public OkUtils get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request build = new Request.Builder().url(url).build();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Message message = Message.obtain();
                message.obj = data;
                handler.sendMessage(message);
            }


        });
        return this;
    }

    public void setOkLisener(OkListener lisener) {
        this.lisener = lisener;
    }


    public interface OkListener {
        void success(String data);
    }


}

