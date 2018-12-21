package com.example.shoppingcar.model;

import com.example.shoppingcar.bean.User;
import com.example.shoppingcar.callback.MyCallBack;

public interface Model {
    void getData(String mUrl, MyCallBack callBack);
}
