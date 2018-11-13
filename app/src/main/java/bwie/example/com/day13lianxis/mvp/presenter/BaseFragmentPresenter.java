package bwie.example.com.day13lianxis.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bwie.example.com.day13lianxis.mvp.view.DegateImpl;


public abstract class BaseFragmentPresenter <T extends DegateImpl>extends Fragment {
    private  T degate;
    public  abstract Class<T> getDegateClass();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            degate = getDegateClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        degate.create(inflater,container,savedInstanceState);
        return degate.getRootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        degate.setContext(getActivity());
        degate.iniData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        degate.destoryView();
        degate=null;
    }
}

