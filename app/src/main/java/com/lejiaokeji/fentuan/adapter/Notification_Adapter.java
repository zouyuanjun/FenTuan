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
import com.lejiaokeji.fentuan.databean.Item_notificationBean;

import java.util.List;

public class Notification_Adapter extends RecyclerView.Adapter{
    protected Context mContext;
    protected List<Item_notificationBean> mDatas;
    private Notification_Adapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(Notification_Adapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Notification_Adapter(Context mContext, List<Item_notificationBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_notification_page,parent,false);
        return new Notification_Adapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((Notification_Adapter.RecyclerHolder)holder).title.setText(mDatas.get(position).getTitle());
        ((RecyclerHolder)holder).time.setText(mDatas.get(position).getTime());
        ((RecyclerHolder)holder).content.setText(mDatas.get(position).getContent());
        if (mDatas.get(position).getPicture().isEmpty()){
            ((RecyclerHolder)holder).photo.setVisibility(View.GONE);
        }else {
            ((RecyclerHolder)holder).photo.setImageURI(mDatas.get(position).getPicture());
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
        TextView time;
        TextView content;
        SimpleDraweeView photo;

        private RecyclerHolder(View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.tv_notification_time);
            content =  itemView.findViewById(R.id.tv_notification_content);
            title = itemView.findViewById(R.id.tv_notification_title);
            photo = itemView.findViewById(R.id.sdv_notification);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }

}