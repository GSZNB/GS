package com.example.shoppingcar.presenter;

import com.example.shoppingcar.bean.Data;
import com.example.shoppingcar.callback.DCallBack;
import com.example.shoppingcar.model.DModelImpl;
import com.example.shoppingcar.view.DView;

public class DPresenterImpl implements DPresenter {
    private DModelImpl dModel;
    private DView dView;

    public DPresenterImpl(DView dView) {
        this.dView = dView;
        dModel=new DModelImpl();
    }

    @Override
    public void startReson(String dUrl) {
        dModel.getData(dUrl, new DCallBack() {
            @Override
            public void setData(Data data) {
                dView.setData(data);
            }

            @Override
            public void setError(String error) {
                dView.setError(error);
            }
        });
    }
}
