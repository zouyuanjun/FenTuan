package com.lejiaokeji.fentuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.databean.Item_Shop;

import java.util.List;

public class Home_Re_Adapter extends RecyclerView.Adapter{
    protected Context mContext;
    protected List<Item_Shop> mDatas;

    public Home_Re_Adapter(Context mContext, List<Item_Shop> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_rv_good,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecyclerHolder)holder).title.setText(mDatas.get(position).getTitle());
        ((RecyclerHolder)holder).tv_item_price.setText(mDatas.get(position).getCoupon_price());
        ((RecyclerHolder)holder).tv_item_yongjing.setText(mDatas.get(position).getTitle());
        ((RecyclerHolder)holder).sdv_item_goodsphoto.setImageURI(mDatas.get(position).getGood_img());
        ((RecyclerHolder)holder).sdv_qh_price.setImageURI(mDatas.get(position).getGood_img());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView tv_item_price;
        TextView tv_item_yongjing;
        SimpleDraweeView  sdv_item_goodsphoto;
        SimpleDraweeView  sdv_qh_price;

        private RecyclerHolder(View itemView) {
            super(itemView);
            sdv_item_goodsphoto=itemView.findViewById(R.id.sdv_item_goodsphoto);
            tv_item_price =  itemView.findViewById(R.id.tv_item_price);
            title = itemView.findViewById(R.id.tv_item_shoptitle);
            tv_item_yongjing = itemView.findViewById(R.id.tv_item_yongjing);
            sdv_qh_price=itemView.findViewById(R.id.sdv_qh_price);;
        }
    }
}
