package com.example.adaml.androidecommerce.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adaml.androidecommerce.Interface.ItemClickListener;
import com.example.adaml.androidecommerce.R;

/**
 * Created by adaml on 17/11/2017.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView item_name;
    public ImageView item_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ItemViewHolder(View itemView) {
        super(itemView);

        item_name = (TextView) itemView.findViewById(R.id.item_name);
        item_image = (ImageView) itemView.findViewById(R.id.item_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(), false);
    }
}
