package com.example.shoppingcar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingcar.R;
import com.example.shoppingcar.bean.User;
import com.example.shoppingcar.bean.UserBean;
import com.example.shoppingcar.weight.JiaJianView;

import java.util.ArrayList;
import java.util.List;

public class GAdapter extends BaseExpandableListAdapter {
    private ArrayList<UserBean.DataBean> gList;
    private Context context;

    public GAdapter(ArrayList<UserBean.DataBean> gList, Context context) {
        this.gList = gList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return gList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return gList.get(groupPosition).getSpus().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder=null;
        if (convertView==null){
            groupHolder = new GroupHolder();
            convertView=View.inflate(context,R.layout.group_list,null);
            groupHolder.textGroup=convertView.findViewById(R.id.textGroup);
            groupHolder.boxGroup=convertView.findViewById(R.id.boxGroup);
            convertView.setTag(groupHolder);
        }else {
            groupHolder= (GroupHolder) convertView.getTag();
        }
        UserBean.DataBean dataBean = gList.get(groupPosition);
        groupHolder.textGroup.setText(dataBean.getName()+"");
        //复选框监听
        boolean childAllCheck = isChildAllCheck(groupPosition);
        groupHolder.boxGroup.setChecked(childAllCheck);
        groupHolder.boxGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterCallBack!=null){
                    adapterCallBack.setGroupCheck(groupPosition);
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder=null;
        if (convertView==null){
            childHolder = new ChildHolder();
            convertView=View.inflate(context,R.layout.child_list,null);
            childHolder.textName=convertView.findViewById(R.id.textName);
            childHolder.textPrice=convertView.findViewById(R.id.textPrice);
            childHolder.imageChild=convertView.findViewById(R.id.imageChild);
            childHolder.boxChild=convertView.findViewById(R.id.boxChild);
            childHolder.ZDY_View=convertView.findViewById(R.id.ZDY_View);
            convertView.setTag(childHolder);
        }else {
            childHolder= (ChildHolder) convertView.getTag();
        }
        UserBean.DataBean.SpusBean spusBean = gList.get(groupPosition).getSpus().get(childPosition);
        childHolder.textName.setText(spusBean.getName()+"");
        childHolder.textPrice.setText(spusBean.getSkus().get(0).getPrice());
        Glide.with(context).load(spusBean.getPic_url()).into(childHolder.imageChild);
        //复选框监听
        childHolder.boxChild.setChecked(spusBean.isChildChecked());
        childHolder.ZDY_View.setNumber(spusBean.getPraise_num());
        childHolder.boxChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterCallBack!=null){
                    adapterCallBack.setChildCheck(groupPosition,childPosition);
                }
            }
        });
        childHolder.ZDY_View.setOnChange(new JiaJianView.OnCountChange() {
            @Override
            public void setCount(int count) {
                if (adapterCallBack!=null){
                    adapterCallBack.setNumber(groupPosition,childPosition,count);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class GroupHolder{
        TextView textGroup;
        CheckBox boxGroup;
    }
    class ChildHolder{
        ImageView imageChild;
        TextView textName;
        TextView textPrice;
        CheckBox boxChild;
        JiaJianView ZDY_View;
    }
    //这个应该组的全选和反选
    //点击group的CheckBox让所有child选中（1）
    public void childAllCheck(int groupPosition,boolean isCheck){
        UserBean.DataBean dataBean = gList.get(groupPosition);
        List<UserBean.DataBean.SpusBean> spus = dataBean.getSpus();
        for (int i = 0; i <spus.size() ; i++) {
            UserBean.DataBean.SpusBean spusBean = spus.get(i);
            spusBean.setChildChecked(isCheck);
        }
    }
    //判断小组是否被选中（2）
    public boolean isChildAllCheck(int groupPosition){
        boolean boo=true;
        UserBean.DataBean dataBean = gList.get(groupPosition);
        List<UserBean.DataBean.SpusBean> spus = dataBean.getSpus();
        for (int i = 0; i <spus.size() ; i++) {
            UserBean.DataBean.SpusBean spusBean = spus.get(i);
            if (!spusBean.isChildChecked()){
                return false;
            }
        }
        return boo;
    }
    //点击child给他进行赋值(1组内)
    public void setChildChecked(int groupPositon, int childPositon, boolean isCheckBox) {
        UserBean.DataBean.SpusBean spusBean = gList.get(groupPositon).getSpus().get(childPositon);
        spusBean.setChildChecked(isCheckBox);
    }
    //查看当前这个商品有没有被选中(2组内)
    public boolean isChildChecked(int groupPositon, int childPositon) {
        UserBean.DataBean.SpusBean spusBean = gList.get(groupPositon).getSpus().get(childPositon);
        if (spusBean.isChildChecked()) {
            return true;
        }
        return false;
    }
    //底部功能操作
    //因为咱们底部视图有个一全选和反选(1)
    public boolean isAllGoods(){
        boolean boo=true;
        for (int i = 0; i <gList.size() ; i++) {
            UserBean.DataBean dataBean = gList.get(i);
            for (int j = 0; j <dataBean.getSpus().size() ; j++) {
                UserBean.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                if (!spusBean.isChildChecked()){
                    return false;
                }
            }
        }
        return boo;
    }
    //底部全选（最大的全选反选）(2)
    public void setAllGoodsCheck(boolean isAllCheck){
        for (int i = 0; i <gList.size() ; i++) {
            UserBean.DataBean bean = gList.get(i);
            for (int j = 0; j <bean.getSpus().size() ; j++) {
                UserBean.DataBean.SpusBean spusBean = bean.getSpus().get(j);
                spusBean.setChildChecked(isAllCheck);
            }
        }
    }
    //计算所有商品的价格（价格用float）
    public float getAllPrice(){
        //初始化价格为0
        float allPrice=0;
        for (int i = 0; i <gList.size() ; i++) {
            UserBean.DataBean dataBean = gList.get(i);
            for (int j = 0; j <dataBean.getSpus().size() ; j++) {
                UserBean.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                if (spusBean.isChildChecked()){
                    allPrice+=spusBean.getPraise_num()*Float.parseFloat(spusBean.getSkus().get(0).getPrice());
                }
            }
        }
        return allPrice;
    }
    //计算所有商品的数量(数量用int)
    public int getAllNumber(){
        //初始化数量为0
        int allNumber=0;
        for (int i = 0; i <gList.size() ; i++) {
            UserBean.DataBean dataBean = gList.get(i);
            for (int j = 0; j <dataBean.getSpus().size() ; j++) {
                UserBean.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                if (spusBean.isChildChecked()){
                    allNumber+=spusBean.getPraise_num();
                }
            }
        }
        return allNumber;
    }
    //给商品数量进行赋值(用于加减商品数量)
    public void setShangPingNumber(int groupPosition, int childPosition, int number) {
        UserBean.DataBean.SpusBean spusBean = gList.get(groupPosition).getSpus().get(childPosition);
        spusBean.setPraise_num(number);
    }
    //先写接口回调
    public interface AdapterCallBack{
        //第一个小组
        void setGroupCheck(int groupPosition);
        //第二个是组内
        void setChildCheck(int groupPosition,int childPosition);
        //第三个是自定义View加减
        void setNumber(int groupPosition, int childPosition, int count);
    }
    private AdapterCallBack adapterCallBack;

    public void setCallbase(AdapterCallBack adapterCallBack){
        this.adapterCallBack=adapterCallBack;
    }
}
