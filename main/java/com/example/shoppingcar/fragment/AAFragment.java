package com.example.shoppingcar.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shoppingcar.R;
import com.example.shoppingcar.adapter.DAdapter;
import com.example.shoppingcar.adapter.MyAdapter;
import com.example.shoppingcar.bean.Data;
import com.example.shoppingcar.bean.User;
import com.example.shoppingcar.presenter.DPresenterImpl;
import com.example.shoppingcar.presenter.PresenterImpl;
import com.example.shoppingcar.view.DView;
import com.example.shoppingcar.view.IView;

import java.util.ArrayList;

public class AAFragment extends Fragment implements IView,DView {

    private RecyclerView Recy_On;
    private RecyclerView Recy_List;
    private ArrayList<User.ResultsBean> mList=new ArrayList<>();
    private String mUrl="https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
    private ArrayList<Data.DataBean> dList=new ArrayList<>();
    private String dUrl="http://www.wanandroid.com/tools/mockapi/6523/restaurants_offset_0_limit_4";
    private MyAdapter adapter;
    private PresenterImpl presenter;
    private RecyclerView recyclerView;
    private DAdapter dAdapter;
    private DPresenterImpl dPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_aa, container, false);
        //控件
        initView(view);
        //网格布局适配器
        adapter = new MyAdapter(mList,getActivity());
        Recy_On.setAdapter(adapter);
        //网格布局
        GridLayoutManager manager=new GridLayoutManager(getActivity(),4);
        Recy_On.setLayoutManager(manager);
        //线性适配器
        dAdapter = new DAdapter(dList,getActivity());
        Recy_List.setAdapter(dAdapter);
        //线性布局
        Recy_List.setLayoutManager(new LinearLayoutManager(getActivity()));
        //获取网格P层
        presenter = new PresenterImpl(this);
        presenter.stratRequest(mUrl);
        //获取线性P层
        dPresenter = new DPresenterImpl(this);
        dPresenter.startReson(dUrl);
        return view;
    }

    private void initView(View view) {
        Recy_On = view.findViewById(R.id.Recy_On);
        Recy_List=view.findViewById(R.id.Recy_List);
    }

    @Override
    public void setSuccess(User success) {
            mList.addAll(success.getResults());
            adapter.notifyDataSetChanged();
    }

    @Override
    public void setData(Data data) {
        dList.addAll(data.getData());
        dAdapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String error) {
        Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
    }
}
