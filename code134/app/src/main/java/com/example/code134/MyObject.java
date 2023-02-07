package com.example.code134;

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/21 16:32
 */
public class MyObject {
    private Context mContext;
    MyObject(Context context){
        this.mContext = context;
    }
    @JavascriptInterface
    public void showToast(String name){
        Toast.makeText(mContext, name + ".你好1", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void showList(){
        //显示一个普通的列表对话框
        new AlertDialog.Builder(mContext)
                .setTitle("图书列表")
                .setIcon(R.drawable.ic_launcher_background)
                .setItems(new String[]{"", ""}, null)
                .setPositiveButton("确定", null)
                .create()
                .show();
    }
}
