package com.example.code45;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/14 17:10
 */
public class BookDetailActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //指定加载某一个布局文件，该布局文件中只需要定一个FrameLayout，用来当作fragment的容器
        setContentView(R.layout.support_simple_spinner_dropdown_item);
        //将ActionBar上的应用图标转换成可点击的按钮
        getActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null){
            BookListFragment fragment = new BookListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("",0);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.book_detail_container,fragment).commit();
        }
    }
}
