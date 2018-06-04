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
import com.lejiaokeji.fentuan.databean.Item_Shop_RecommentBean;

import java.util.List;

public class Recommend_Rv_Adapter extends RecyclerView.Adapter{
    protected Context mContext;
    protected List<Item_Shop_RecommentBean> mDatas;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Recommend_Rv_Adapter(Context mContext, List<Item_Shop_RecommentBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_rv_shoprecommend,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((RecyclerHolder)holder).title.setText(mDatas.get(position).getShop_title());
        ((RecyclerHolder)holder).tv_time.setText(mDatas.get(position).getTime());
        ((RecyclerHolder)holder).tv_item_yongjing.setText(mDatas.get(position).getYongjin());
        ((RecyclerHolder)holder).tv_nickname.setText(mDatas.get(position).getName());
        ((RecyclerHolder)holder).tv_sharecount.setText(mDatas.get(position).getSharecount());
        ((RecyclerHolder)holder).sdv_item_headerphoto.setImageURI(mDatas.get(position).getHeaderurl());
        ((RecyclerHolder)holder).sdv_origin.setImageURI(mDatas.get(position).getOrigin());
        ((RecyclerHolder)holder).sdv_item_goodsphoto3.setImageURI(mDatas.get(position).getSimpleDraweeView3());
        ((RecyclerHolder)holder).sdv_item_goodsphoto2.setImageURI(mDatas.get(position).getSimpleDraweeView2());
        ((RecyclerHolder)holder).sdv_item_goodsphoto1.setImageURI(mDatas.get(position).getSimpleDraweeView1());
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
        TextView tv_time;
        TextView tv_item_yongjing;
        TextView tv_nickname;
        TextView tv_sharecount;
        SimpleDraweeView sdv_item_headerphoto;
        SimpleDraweeView  sdv_origin;
        SimpleDraweeView sdv_item_goodsphoto3;
        SimpleDraweeView  sdv_item_goodsphoto2;
        SimpleDraweeView sdv_item_goodsphoto1;

        private RecyclerHolder(View itemView) {
            super(itemView);
            sdv_item_headerphoto=itemView.findViewById(R.id.sbv_item_shoprecommend_head);
            tv_nickname =  itemView.findViewById(R.id.tv_item_shoprecommend_name);
            title = itemView.findViewById(R.id.tv_shoprecommend_title);
            tv_item_yongjing = itemView.findViewById(R.id.tv_recomment_yongjing);
            tv_time=itemView.findViewById(R.id.tv_item_shoprecommend_time);;
            tv_sharecount=itemView.findViewById(R.id.tv_sharecount);
            sdv_origin =  itemView.findViewById(R.id.sdv_item_origin);
            sdv_item_goodsphoto1 = itemView.findViewById(R.id.sdv_item_recomment1);
            sdv_item_goodsphoto2 = itemView.findViewById(R.id.sdv_item_recomment2);
            sdv_item_goodsphoto3=itemView.findViewById(R.id.sdv_item_recomment3);;




        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }

}
