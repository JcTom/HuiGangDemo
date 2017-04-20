package com.suctan.huigangdemo.adapter.eatfood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigangdemo.R;

/**
 * Created by Tom on 2017/4/13.
 */

public class EatRecycleViewAdapter extends RecyclerView.Adapter<EatRecycleViewAdapter.EatRecycleHolder> {

    private Context context;

    public EatRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EatRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tongchufang_item, null, false);
        EatRecycleHolder holder = new EatRecycleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EatRecycleHolder holder, int position) {
        EatRecycleHolder holder1 = holder;


    }


    @Override
    public int getItemCount() {
        return 5;
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

