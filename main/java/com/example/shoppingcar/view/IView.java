package com.example.shoppingcar.view;

import com.example.shoppingcar.bean.User;

public interface IView {
    void setSuccess(User success);
    void setError(String error);
}
