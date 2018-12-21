package com.example.shoppingcar.presenter;

import com.example.shoppingcar.bean.UserBean;
import com.example.shoppingcar.callback.GCallBack;
import com.example.shoppingcar.model.GModelImpl;
import com.example.shoppingcar.view.GView;

public class GPresenterImpl implements GPresenter{
    private GModelImpl gModel;
    private GView gView;

    public GPresenterImpl(GView gView) {
        this.gView = gView;
        gModel=new GModelImpl();
    }

    @Override
    public void startResquest(String gUrl) {
        gModel.getData(gUrl, new GCallBack() {
            @Override
            public void getDatas(UserBean datas) {
                gView.setDatas(datas);
            }

            @Override
            public void getErrors(String errors) {
                gView.setErrors(errors);
            }
        });
    }
}
