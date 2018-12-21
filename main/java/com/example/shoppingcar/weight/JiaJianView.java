package com.example.shoppingcar.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcar.R;

public class JiaJianView extends LinearLayout implements View.OnClickListener {
    private TextView num;
    private TextView add;
    private TextView del;
    //商品数量
    private int mCount;
    public JiaJianView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.jiajianview, this);
        initViews();
    }
    private void initViews() {
         add = findViewById(R.id.add_A);
        add.setOnClickListener(this);
        del = findViewById(R.id.del_A);
        del.setOnClickListener(this);
        num = findViewById(R.id.num);
    }
    //先给初始值赋值
    public void setNumber(int number){
        this.mCount=number;
        num.setText(number+"");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_A:
                mCount++;
                num.setText(mCount+"");
                if (mCountChange!=null){
                    mCountChange.setCount(mCount);
                }
                break;
            case R.id.del_A:
                if (mCount>0){
                    mCount--;
                    num.setText(mCount+"");
                    if (mCountChange!=null){
                        mCountChange.setCount(mCount);
                    }
                }else {
                    Toast.makeText(getContext(), "商品已售空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //接口回调
    public interface OnCountChange {
        void setCount(int count);
    }

    private OnCountChange mCountChange;

    public void setOnChange(OnCountChange countChange) {
        this.mCountChange = countChange;
    }
}
