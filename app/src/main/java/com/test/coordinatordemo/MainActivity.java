package com.test.coordinatordemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.commit451.nativestackblur.NativeStackBlur;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {


    private ImageView img;
    private Bitmap bitmap;
    private int m;

    @Override
    protected int getViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        List<String> lists = getTabList();
        TabLayout tablelayout= (TabLayout) findViewById(R.id.tablelayout);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);

        AppBarLayout app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        List<Fragment> fragments=new ArrayList<>();
        for (int i=0;i<2;i++)
        {
            CurrencyFragment  currencyFragment=  CurrencyFragment.newInstance(lists.get(i));
            fragments.add(currencyFragment);
        }


        img = (ImageView) findViewById(R.id.img);

        viewPager.setAdapter(new MyAdatapter(lists,this,fragments,getSupportFragmentManager()));
        tablelayout.setupWithViewPager(viewPager);

        app_bar_layout.addOnOffsetChangedListener(this);

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_icon);
    }



    public List<String> getTabList() {
        List<String>  list=new ArrayList<>();
        list.add("热门");
        list.add("推荐");
        return list;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

          //对Bitmap进行模糊化处理，第一个参数代表原始Bitmap，第二个参数代表模糊半径
        //半径越大，处理后的图片就越模糊

        m = (int) (mm(String.valueOf(percentage))*10);
        Log.i("onOffsetChanged","m=>"+ m);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Bitmap bm = NativeStackBlur.process(bitmap, m);
                img.setImageBitmap(bm);
            }
        });


    }

    public double mm(String  percentage)
    {
        DecimalFormat df = new DecimalFormat("#.0");
        return Double.parseDouble(percentage);
    }
}
