package com.example.shoppingcar.presenter;

import com.example.shoppingcar.bean.User;
import com.example.shoppingcar.callback.MyCallBack;
import com.example.shoppingcar.model.ModelImpl;
import com.example.shoppingcar.view.IView;

public class PresenterImpl implements IPresenter {
    private IView view;
    private ModelImpl model;

    public PresenterImpl(IView view) {
        this.view = view;
        model=new ModelImpl();
    }

    @Override
    public void stratRequest(String mUrl) {
        model.getData(mUrl, new MyCallBack() {
            @Override
            public void setData(User data) {
                view.setSuccess(data);
            }

            @Override
            public void setError(String error) {
                view.setError(error);
            }
        });
    }
}
