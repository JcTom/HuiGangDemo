package com.suctan.huigangdemo.adapter.eatfood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.wanteat.WantEatOtherBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/13.
 */

public class EatRecycleViewAdapter extends RecyclerView.Adapter<EatRecycleViewAdapter.EatRecycleHolder> {

    private Context context;
    private ArrayList<EatFoodBean> eatFoodBeenList;

    public EatRecycleViewAdapter(Context context, ArrayList<EatFoodBean> eatFoodBeenList) {
        this.context = context;
        this.eatFoodBeenList = eatFoodBeenList;
    }

    @Override
    public EatRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tongchufang_item, null, false);
        EatRecycleHolder holder = new EatRecycleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EatRecycleHolder holder, final int position) {
        EatRecycleHolder holder1 = holder;
        EatFoodBean eatFoodBean = eatFoodBeenList.get(position);
        if (eatFoodBean.getOrder_pic() != null) {
            LoadImageManager.getImageLoader().displayImage(eatFoodBean.getOrder_pic(), holder1.id_index_gallery_item_image);
        }
        holder1.id_index_gallery_item_text.setText(eatFoodBean.getOrder_title());
        holder1.id_index_gallery_item_Momeny.setText("￥" + eatFoodBean.getOrder_price());

        holder1.id_index_gallery_item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lisetner.addCartIndex(position);
            }
        });

    }

    OnAddCartButtonLisetner Lisetner;

    public void setOnAddCartClick(OnAddCartButtonLisetner Lisetner) {
        this.Lisetner = Lisetner;
    }

    public interface OnAddCartButtonLisetner {
        void addCartIndex(int addIndex);
    }

    @Override
    public int getItemCount() {
        return eatFoodBeenList.size();
    }

    class EatRecycleHolder extends RecyclerView.ViewHolder {
        ImageView id_index_gallery_item_image;
        TextView id_index_gallery_item_text;
        TextView id_index_gallery_item_Momeny;


        public EatRecycleHolder(View itemView) {
            super(itemView);
            id_index_gallery_item_image = (ImageView) itemView.findViewById(R.id.id_index_gallery_item_image);
            id_index_gallery_item_text = (TextView) itemView.findViewById(R.id.id_index_gallery_item_text);
            id_index_gallery_item_Momeny = (TextView) itemView.findViewById(R.id.id_index_gallery_item_Momeny);

        }
    }
}

