package com.lejiaokeji.fentuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.databean.Item_Shop;
import com.lejiaokeji.fentuan.wxapi.Constants;

import java.util.List;

public class Home_Re_Adapter extends RecyclerView.Adapter{
    protected Context mContext;
    protected List<Item_Shop> mDatas;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((RecyclerHolder)holder).title.setText(mDatas.get(position).getGoods_name());
        ((RecyclerHolder)holder).tv_item_price.setText("￥"+mDatas.get(position).getCoupon_price());
        ((RecyclerHolder)holder).tv_item_yongjing.setText(mDatas.get(position).getCommission());
        ((RecyclerHolder)holder).sdv_item_goodsphoto.setImageURI(mDatas.get(position).getGoods_img());
        String youhuiquan=mDatas.get(position).getDiscount_price();
        youhuiquan=youhuiquan.substring(0,youhuiquan.length()-2);
        ((RecyclerHolder)holder).tv_youhuiquan.setText(youhuiquan+"元");
        if (Constants.SELECT_JD){
            ((RecyclerHolder)holder).im_mall_ico.setBackground(ContextCompat.getDrawable(mContext,R.drawable.home_ic_jd));
        }else {
            ((RecyclerHolder)holder).im_mall_ico.setBackground(ContextCompat.getDrawable(mContext,R.drawable.home_ic_pdd));
        }
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(v,position);
                    return false;
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView tv_item_price;
        TextView tv_item_yongjing;
        TextView tv_youhuiquan;
        ImageView im_mall_ico;
        SimpleDraweeView  sdv_item_goodsphoto;
        SimpleDraweeView  sdv_qh_price;

        private RecyclerHolder(View itemView) {
            super(itemView);
            im_mall_ico=itemView.findViewById(R.id.im_mall_ico);
            sdv_item_goodsphoto=itemView.findViewById(R.id.sdv_item_goodsphoto);
            tv_item_price =  itemView.findViewById(R.id.tv_item_price);
            title = itemView.findViewById(R.id.tv_item_shoptitle);
            tv_item_yongjing = itemView.findViewById(R.id.tv_item_yongjing);
            sdv_qh_price=itemView.findViewById(R.id.sdv_qh_price);
            tv_youhuiquan=itemView.findViewById(R.id.tv_youhuiquan);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }

}
