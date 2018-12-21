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
import com.example.shoppingcar.bean.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<User.ResultsBean> mList;
    private Context context;

    public MyAdapter(ArrayList<User.ResultsBean> mLista, Context contexta) {
        this.mList = mLista;
        this.context = contexta;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recy_on, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        User.ResultsBean bean = mList.get(position);
        holder.textOn.setText(bean.getType());
        Glide.with(context).load(bean.getUrl()).into(holder.imageOn);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageOn;
        private TextView textOn;

        public ViewHolder(View itemView) {
            super(itemView);
            imageOn = itemView.findViewById(R.id.image_On);
            textOn = itemView.findViewById(R.id.text_On);
        }
    }
}
