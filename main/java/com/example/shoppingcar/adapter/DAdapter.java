package com.example.shoppingcar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingcar.R;
import com.example.shoppingcar.bean.Data;

import java.util.ArrayList;

public class DAdapter extends RecyclerView.Adapter<DAdapter.ViewHolder> {
    private ArrayList<Data.DataBean> dList;
    private Context context;

    public DAdapter(ArrayList<Data.DataBean> dList, Context context) {
        this.dList = dList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.recy_list, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Data.DataBean dataBean = dList.get(i);
        viewHolder.textList.setText(dataBean.getName());
        Glide.with(context).load(dataBean.getPic_url()).into(viewHolder.imageList);
        viewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return dList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textList;
        private ImageView imageList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageList=itemView.findViewById(R.id.image_List);
            textList = itemView.findViewById(R.id.text_List);
        }
    }
}
