package com.suctan.huigangdemo.adapter.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.cart.CartBean;
import com.suctan.huigangdemo.bean.dowant.DoWantOrderBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class ShoppingCartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CartBean> CartBeanList;
    private int maxNum;
    private int tempCartNum;

    public ShoppingCartAdapter(Context context, ArrayList<CartBean> CartBeanList) {
        this.context = context;
        this.CartBeanList = CartBeanList;
    }

    @Override
    public int getCount() {
        return CartBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return CartBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        final CartBean mCartBean = CartBeanList.get(i);
        maxNum = mCartBean.getMax_num();
        tempCartNum = mCartBean.getOrder_num();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, viewGroup, false);
            holder = new MyViewHolder();
            holder.checkbox_cartitem = (CheckBox) view.findViewById(R.id.checkbox_cartitem);
            holder.imv_cart_image = (ImageView) view.findViewById(R.id.imv_cart_image);
            holder.tv_cartitem_servertype = (TextView) view.findViewById(R.id.tv_cartitem_servertype);
            holder.tv_cartitem_momeny = (TextView) view.findViewById(R.id.tv_cartitem_momeny);
            holder.tv_cartitem_decrease = (TextView) view.findViewById(R.id.tv_cartitem_decrease);
            holder.tv_cartitem_countshow = (TextView) view.findViewById(R.id.tv_cartitem_countshow);
            holder.tv_cartitem_arise = (TextView) view.findViewById(R.id.tv_cartitem_arise);
            holder.tv_cartitem_delete = (TextView) view.findViewById(R.id.tv_cartitem_delete);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        if (mCartBean.getOrder_pic() != null) {
            LoadImageManager.getImageLoader().displayImage(mCartBean.getOrder_pic(), holder.imv_cart_image);
        }
        String serverType = null;
        if (mCartBean.getOrder_type() == 0) {
            serverType = "上门做";
        } else if (mCartBean.getOrder_type() == 1) {
            serverType = "送上门";
        } else {
            serverType = "自提";
        }
        holder.tv_cartitem_servertype.setText(serverType);
        holder.tv_cartitem_momeny.setText(mCartBean.getOrder_price() + "");
        holder.tv_cartitem_countshow.setText(mCartBean.getOrder_num() + "");

        holder.tv_cartitem_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tempCartNum == 0) {
                    return;
                }
                tempCartNum--;
                Listener.onDecreaseCart(i, tempCartNum);
            }
        });
        holder.tv_cartitem_arise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tempCartNum >= maxNum) {
                    Toast.makeText(BaseApplication.getContext(), "无法再添加！", Toast.LENGTH_LONG).show();
                    return;
                }
                tempCartNum++;
                Listener.onAriseCart(i, tempCartNum);
            }
        });

        holder.tv_cartitem_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listener.onDetele(i);
            }
        });


        holder.checkbox_cartitem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Listener.onCheckBoxItemClick(i);
                mCartBean.setCheck(b);
            }
        });
        if (mCartBean.isCheck()) {
            holder.checkbox_cartitem.setChecked(true);
        } else {
            holder.checkbox_cartitem.setChecked(false);
        }
        return view;
    }

    OnCartListClickListner Listener;

    public void setOnCartListClick(OnCartListClickListner Listener) {
        this.Listener = Listener;
    }


    public interface OnCartListClickListner {
        void onDecreaseCart(int position, int count);

        void onAriseCart(int position, int count);

        void onDetele(int position);

        void onCheckBoxItemClick(int position);
    }


    class MyViewHolder {
        CheckBox checkbox_cartitem;
        ImageView imv_cart_image;
        TextView tv_cartitem_servertype;
        TextView tv_cartitem_momeny;
        TextView tv_cartitem_decrease;
        TextView tv_cartitem_countshow;
        TextView tv_cartitem_arise;
        TextView tv_cartitem_delete;
    }
}
