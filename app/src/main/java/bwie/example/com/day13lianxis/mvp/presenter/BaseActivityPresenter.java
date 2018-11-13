package bwie.example.com.day13lianxis.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import bwie.example.com.day13lianxis.mvp.view.DegateImpl;


public abstract class BaseActivityPresenter<T extends DegateImpl> extends AppCompatActivity{

    protected   T degate;

    public  abstract Class<T> getDegateClass();
    public BaseActivityPresenter(){
        try {
            degate = getDegateClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        degate.setContext(this);
        degate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(degate.getRootView());
        ButterKnife.bind(this);
        initView();
        degate.iniData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        degate.destoryView();
        degate=null;
    }

    public void initView(){

    }

}

