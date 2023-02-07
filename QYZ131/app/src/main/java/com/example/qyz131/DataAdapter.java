package com.example.qyz131;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

/**
 * @author xilinhan
 * @description:
 * @date :2022/5/7 21:38
 */
public class DataAdapter extends ArrayAdapter {

    Context mContext;

    public DataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        init(context);
    }

    public DataAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        init(context);
    }

    public DataAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
        init(context);
    }

    public DataAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
        init(context);
    }

    private void init(Context context){
        mContext = context;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView iv_pic_item = null;
        int density = (int) ScreenUtil.getDeviceDensity(mContext);

        if(convertView == null){
            iv_pic_item = new ImageView(mContext);
            //设置布局图片
            iv_pic_item.setLayoutParams(new ViewGroup.LayoutParams(
                    80 * density,
                    100 * density
            ));
            //设置显示比例类型
            iv_pic_item.setScaleType(ImageView.ScaleType.FIT_XY);
        }else {
            iv_pic_item = (ImageView) convertView;
        }
        iv_pic_item.setBackgroundColor(android.R.color.black);
        File file = new File("");
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        iv_pic_item.setImageBitmap(bitmap);
        return iv_pic_item;

    }
}
