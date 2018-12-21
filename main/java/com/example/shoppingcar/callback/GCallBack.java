package com.example.shoppingcar.callback;

import com.example.shoppingcar.bean.UserBean;

public interface GCallBack {
    void getDatas(UserBean datas);
    void getErrors(String errors);
}
