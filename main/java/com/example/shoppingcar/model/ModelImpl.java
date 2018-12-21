package com.example.shoppingcar.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.example.shoppingcar.bean.User;
import com.example.shoppingcar.callback.MyCallBack;
import com.example.shoppingcar.util.OkHttps;
import com.google.gson.Gson;

import java.io.IOException;

public class ModelImpl implements Model {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String jsonStr= (String) msg.obj;
                    Gson gson=new Gson();
                    User user=gson.fromJson(jsonStr,User.class);
                    callBack.setData(user);
                    break;
            }
        }
    };
    private MyCallBack callBack;
    @Override
    public void getData(final String mUrl, final MyCallBack callBack) {
        this.callBack=callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String ok = OkHttps.getintent().get(mUrl);
                    handler.sendMessage(handler.obtainMessage(0,ok));
                } catch (IOException e) {
                    Looper.prepare();
                    callBack.setError("异常");
                    Looper.loop();
                }
            }
        }).start();
    }
}
