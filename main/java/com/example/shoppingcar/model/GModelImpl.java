package com.example.shoppingcar.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.shoppingcar.bean.UserBean;
import com.example.shoppingcar.callback.GCallBack;
import com.example.shoppingcar.util.OkHttps;
import com.google.gson.Gson;

import java.io.IOException;

public class GModelImpl implements GModel {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String JSONStr= (String) msg.obj;
                    Gson gson=new Gson();
                    UserBean userBean= gson.fromJson(JSONStr,UserBean.class);
                    gCallBack.getDatas(userBean);
                    break;
            }
        }
    };
    private GCallBack gCallBack;
    @Override
    public void getData(final String gUrl, final GCallBack gCallBack) {
        this.gCallBack=gCallBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String goks = OkHttps.getintent().gets(gUrl);
                    handler.sendMessage(handler.obtainMessage(0,goks));
                } catch (IOException e) {
                    Looper.prepare();
                    gCallBack.getErrors("异常");
                    Looper.loop();
                }
            }
        }).start();
    }
}
