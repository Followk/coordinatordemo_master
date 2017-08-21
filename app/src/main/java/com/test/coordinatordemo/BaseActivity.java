package com.test.coordinatordemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

/**
 * Created by Administrator on 2017/8/17.
 */

public abstract class BaseActivity extends FragmentActivity {


    public View rootView;
    public Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
        activity = this;
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        rootView = LayoutInflater.from(this).inflate(getViewLayoutId(),null);
        setContentView(rootView);
        initPresenter();
        initView();
    }


    /**
     * 初始化控件
     * @return
     */
    protected   abstract int  getViewLayoutId();


    //初始化控制层
    protected abstract void   initPresenter();

    protected   abstract  void  initView();
}
