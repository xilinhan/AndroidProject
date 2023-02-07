package com.example.qyz131;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * @author xilinhan
 * @description:
 * @date :2022/5/8 10:41
 */
public class PicSelector {

    //使用本地图库，需要借助系统Intent Action
    //返回码：本地图库
    private static final int RESULT_IMAGE = 100;
    //IMAGE TYPE
    private static final String IMAGE_TYPE = "image/*";
    //Temp照片路径
    public static String TEMP_IMAGE_PATH;
    //返回码：照相机
    private static final int RESULT_CAMERA = 200;

    //获取本地图库的方法
    private void getLocationPic(){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_TYPE
        );
        //startActivityForResult(intent, RESULT_IMAGE);
    }

    //使用相机拍照
    private void usePhoto(){
        TEMP_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + "/temp.png";
        //系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = Uri.fromFile(new File(TEMP_IMAGE_PATH));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        //startActivityForResult(intent, RESULT_CAMERA);
    }

}
