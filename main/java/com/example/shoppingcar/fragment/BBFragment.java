package com.example.shoppingcar.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.shoppingcar.R;
import com.example.shoppingcar.adapter.GAdapter;
import com.example.shoppingcar.bean.User;
import com.example.shoppingcar.bean.UserBean;
import com.example.shoppingcar.presenter.GPresenterImpl;
import com.example.shoppingcar.view.GView;

import java.util.ArrayList;

public class BBFragment extends Fragment implements GView {
    private ExpandableListView Expan_List;
    private CheckBox Box_All;
    private TextView Price;
    private TextView Number;
    private ArrayList<UserBean.DataBean> gList=new ArrayList<>();
    private String gUrl="http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private GPresenterImpl gPresenter;
    private GAdapter gAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bb, container, false);
        initView(view);
        gPresenter = new GPresenterImpl(this);
        gPresenter.startResquest(gUrl);
        gAdapter = new GAdapter(gList,getActivity());
        Expan_List.setAdapter(gAdapter);
        gAdapter.setCallbase(new GAdapter.AdapterCallBack() {
            @Override
            public void setGroupCheck(int groupPosition) {
                boolean childAllCheck = gAdapter.isChildAllCheck(groupPosition);
                gAdapter.childAllCheck(groupPosition,!childAllCheck);
                gAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }

            @Override
            public void setChildCheck(int groupPosition, int childPosition) {
                boolean childChecked = gAdapter.isChildChecked(groupPosition, childPosition);
                gAdapter.setChildChecked(groupPosition,childPosition,!childChecked);
                gAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }

            @Override
            public void setNumber(int groupPosition, int childPosition, int count) {
                gAdapter.setShangPingNumber(groupPosition,childPosition,count);
                gAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }
        });
        //底部全选反选
        Box_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allGoods = gAdapter.isAllGoods();
                gAdapter.setAllGoodsCheck(!allGoods);
                gAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }
        });
        return view;
    }

    private void initView(View view) {
        Expan_List = (ExpandableListView) view.findViewById(R.id.Expan_List);
        Box_All = (CheckBox) view.findViewById(R.id.Box_All);
        Price = (TextView) view.findViewById(R.id.Price);
        Number = (TextView) view.findViewById(R.id.Number);
        //去掉自带的小箭头
        Expan_List.setGroupIndicator(null);
    }
    private void flushBottomLayout(){
        //刷新全选反选
        boolean allGoods = gAdapter.isAllGoods();
        Box_All.setChecked(allGoods);
        //刷新价格
        float allPrice = gAdapter.getAllPrice();
        //刷新数量
        int allNumber = gAdapter.getAllNumber();
        Price.setText("价格："+allPrice);
        Number.setText("总数（0）："+allNumber);
    }
    @Override
    public void setDatas(UserBean datas) {
        gList.addAll(datas.getData());
        gAdapter.notifyDataSetChanged();
    }

    @Override
    public void setErrors(String errors) {

    }
}
