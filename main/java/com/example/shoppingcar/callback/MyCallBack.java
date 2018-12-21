package com.example.shoppingcar.callback;

import com.example.shoppingcar.bean.User;

public interface MyCallBack {
    void setData(User data);
    void setError(String error);
}
