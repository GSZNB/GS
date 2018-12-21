package com.example.shoppingcar.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.shoppingcar.bean.Data;
import com.example.shoppingcar.callback.DCallBack;
import com.example.shoppingcar.util.OkHttps;
import com.google.gson.Gson;

import java.io.IOException;

public class DModelImpl  implements DModel {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String jsonStr= (String) msg.obj;
                    Gson gson=new Gson();
                    Data data=gson.fromJson(jsonStr,Data.class);
                    dCallBack.setData(data);
                    break;
            }
        }
    };
    private DCallBack dCallBack;
    @Override
    public void getData(final String dUrl, final DCallBack dCallBack) {
        this.dCallBack=dCallBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String dok = OkHttps.getintent().dget(dUrl);
                    handler.sendMessage(handler.obtainMessage(0,dok));
                } catch (IOException e) {
                    Looper.prepare();
                    dCallBack.setError("异常");
                    Looper.loop();
                }
            }
        }).start();
    }
}
