package bwie.example.com.day13lianxis.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.day13lianxis.R;
import bwie.example.com.day13lianxis.model.ItemBean;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private Context context;
    private List<ItemBean.DataBean> data1=new ArrayList<>();
    public ItemAdapter(Context context, List<ItemBean.DataBean> data1) {
        this.context=context;
        this.data1=data1;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        PicAdpater picAdpater = new PicAdpater(context, data1.get(i).getList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        myViewHolder.picrecycle_main.setAdapter(picAdpater);
        myViewHolder.picrecycle_main.setLayoutManager(gridLayoutManager);
        picAdpater.setPicListener(new PicAdpater.PicListener() {
            @Override
            public void picChange(int pscid) {
                listener.ItemChange(pscid);
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_seller.setText(data1.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_seller;
        RecyclerView picrecycle_main;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_seller= (TextView)itemView.findViewById(R.id.tv_seller);
            picrecycle_main=(RecyclerView)itemView.findViewById(R.id.picrecycle_main);
        }
    }


    private ItemListener listener;
    public void setItemListener(ItemListener listener){
        this.listener=listener;
    }
    public interface ItemListener{
        void ItemChange(int pscid);
    }
}
