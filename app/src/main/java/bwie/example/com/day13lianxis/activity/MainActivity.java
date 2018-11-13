package bwie.example.com.day13lianxis.activity;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import bwie.example.com.day13lianxis.R;
import bwie.example.com.day13lianxis.mvp.presenter.BaseActivityPresenter;
import bwie.example.com.day13lianxis.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainActivityPresenter>{

    @BindView(R.id.recycle_left)
    RecyclerView recycle_left;

    @Override
    public Class getDegateClass() {
        return MainActivityPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        degate.initView(recycle_left);
    }
}
