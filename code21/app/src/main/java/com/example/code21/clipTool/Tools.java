package com.example.code21.clipTool;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

/**
 * @author xilinhan
 * @description: 剪贴板工具类
 * @date :2022/7/25 17:17
 */
public class Tools {

    private Context mContext;

    public Tools() {
    }

    public Tools(Context context) {
        mContext = context;
    }

    /*
    * 实现文本的复制功能
    * */
    public void copy(String content){
        if(TextUtils.isEmpty(content)){
            ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(null, content.trim());
            clipboardManager.setPrimaryClip(clipData);
        }
    }

    /*
    * 获取剪贴板的内容
    * */
    public String getClipContent(){
        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        if(clipboardManager != null){
            if(clipboardManager.hasPrimaryClip() && clipboardManager.getPrimaryClip().getItemCount() > 0){
                CharSequence clipData = clipboardManager.getPrimaryClip().getItemAt(0).getText();
                String addedTextString = String.valueOf(clipData);
                if(!TextUtils.isEmpty(addedTextString)){
                    return addedTextString;
                }
            }
        }
        return "";
    }

    /*
    * 清除剪贴板内容
    * */
    public void removeClipContent(){
        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        if(clipboardManager != null){
            clipboardManager.setPrimaryClip(clipboardManager.getPrimaryClip());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                clipboardManager.clearPrimaryClip();
            }else {
                clipboardManager.setPrimaryClip(null);
            }
        }
    }

    /*
    * 添加剪贴板数据改变监听器
    * */
    public void setListener(ClipboardManager clipboardManager){
        if(clipboardManager != null){
            clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    /*
                    * 剪贴板中的数据被改变的时候，这个方法被回调
                    * */
                    System.out.println("sout");
                }
            });
        }
    }

    public void test(){

    }

}
