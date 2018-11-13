package bwie.example.com.day13lianxis.presenter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.List;

import bwie.example.com.day13lianxis.R;
import bwie.example.com.day13lianxis.adapter.ItemAdapter;
import bwie.example.com.day13lianxis.adapter.ListAdapter;
import bwie.example.com.day13lianxis.model.DetailBean;
import bwie.example.com.day13lianxis.model.ItemBean;
import bwie.example.com.day13lianxis.model.ListBean;
import bwie.example.com.day13lianxis.mvp.view.DegateImpl;
import bwie.example.com.day13lianxis.utils.OkUtils;

public class MainActivityPresenter extends DegateImpl {

    private RecyclerView recycle_right;
    private RecyclerView recycle_left;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void iniData() {
        super.iniData();
//        RecyclerView recycle_left=(RecyclerView)get(R.id.recycle_left);
        recycle_right = (RecyclerView)get(R.id.recycle_right);
        doLeft(recycle_left);

    }

    private void doLeft(final RecyclerView recycle_left) {
        new OkUtils().get("http://www.zhaoapi.cn/product/getCatagory").setOkLisener(new OkUtils.OkListener() {
            @Override
            public void success(String data) {
                if (data.contains("<")) {
                    return;
                }
                ListBean listBean = new Gson().fromJson(data, ListBean.class);
                ListAdapter listAdapter = new ListAdapter( listBean.getData(),context);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycle_left.setLayoutManager(linearLayoutManager);
                recycle_left.setAdapter(listAdapter);
                listAdapter.setListListener(new ListAdapter.ListListener() {
                    @Override
                    public void listChange(int cid) {
                        doRight(cid);
                    }
                });
            }
        });
//
    }

    private void doRight(int cid) {

        new OkUtils().get("http://www.zhaoapi.cn/product/getProductCatagory?cid="+cid).setOkLisener(new OkUtils.OkListener() {
            @Override
            public void success(String data) {
                if (data.contains("<")) {
                    return;
                }
                ItemBean itemBean = new Gson().fromJson(data, ItemBean.class);
                List<ItemBean.DataBean> data1 = itemBean.getData();
//                toast(data1.toString());
                ItemAdapter itemAdapter = new ItemAdapter(context, data1);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycle_right.setLayoutManager(linearLayoutManager);
                recycle_right.setAdapter(itemAdapter);
                itemAdapter.setItemListener(new ItemAdapter.ItemListener() {
                    @Override
                    public void ItemChange(int pscid) {
                        doDetail(pscid);
                    }
                });
            }
        });
    }

    private void doDetail(int pscid) {
        new OkUtils().get("http://www.zhaoapi.cn/product/getProductDetail?pid="+pscid).setOkLisener(new OkUtils.OkListener() {
            @Override
            public void success(String data) {
                DetailBean detailBean = new Gson().fromJson(data, DetailBean.class);

            }
        });
    }

    private Context context;
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        this.context=context;

    }

    public void initView(RecyclerView recycle_left) {
        this.recycle_left=recycle_left;
    }

}
