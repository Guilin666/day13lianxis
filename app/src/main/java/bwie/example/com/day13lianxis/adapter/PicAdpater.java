package bwie.example.com.day13lianxis.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.day13lianxis.DetailActivity;
import bwie.example.com.day13lianxis.R;
import bwie.example.com.day13lianxis.model.ItemBean;

public class PicAdpater extends RecyclerView.Adapter<PicAdpater.MyViewHolder> {
    private Context context;
    private List<ItemBean.DataBean.ListBean> list = new ArrayList<>();

    public PicAdpater(Context context, List<ItemBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PicAdpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.pic_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PicAdpater.MyViewHolder myViewHolder, final int i) {
        myViewHolder.img_s.setImageURI(list.get(i).getIcon());
        myViewHolder.img_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.picChange(list.get(i).getPscid());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("pid", list.get(i).getPscid());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img_s;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_s = (SimpleDraweeView) itemView.findViewById(R.id.img_s);
        }
    }

    public interface PicListener {
        void picChange(int pscid);
    }

    private PicListener listener;

    public void setPicListener(PicListener listener) {
        this.listener = listener;
    }
}
