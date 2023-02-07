package com.example.qyz131;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author xilinhan
 * @description:
 * @date :2022/5/8 9:55
 */
public class MyAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;
    Context mContext;
    private ViewHolder mViewHolder;


    public MyAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Fruit> objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        if(null == convertView){
            convertView = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
            mViewHolder = new ViewHolder();
            //mViewHolder.mImageView = convertView.findViewById(R.id.aligned);
            //mViewHolder.mButton = convertView.findViewById(R.id.aligned);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, "");
        Bitmap bitmap = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BitmapFactory.Options options = new BitmapFactory.Options();
            bitmap = BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null ,options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mViewHolder.mImageView.setImageBitmap(bitmap);
        return convertView;
    }
}

class Fruit{
    int imgID;
    String name;
}

class ViewHolder{
    ImageView mImageView;
    Button mButton;
}
