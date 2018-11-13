package bwie.example.com.day13lianxis.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.day13lianxis.R;
import bwie.example.com.day13lianxis.model.ListBean;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context context;
    private List<ListBean.DataBean> data=new ArrayList<>();

    public ListAdapter(List<ListBean.DataBean> data, Context context) {
        this.context=context;
        this.data=data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.list_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_name.setText(data.get(i).getName());
        myViewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cid = data.get(i).getCid();
                listListener.listChange(cid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);

        }
    }


    private ListListener listListener;
    public void setListListener(ListListener listListener){
        this.listListener=listListener;
    }

    public interface ListListener{
        void listChange(int cid);
    }


}
