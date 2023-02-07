package com.example.qyz7;

import android.os.Build;

/**
 * @author xilinhan
 * @description:
 * @date :2022/5/5 21:49
 */
public class GetEquipmentInfo {

    String brand, model, androidVersion, romName, romVersion, sign, sdk;
    String device, product, cpu, board, display, id, version_codes_base, maker, user, tags;
    String hardware, host, unknown, type, radio, serial, cpu2;
    GetEquipmentInfo(){
        product = "手机产品名：" + Build.PRODUCT;
        cpu = "CPU_ABI" + Build.CPU_ABI;
        cpu2 = "CPU_ABI2" + Build.CPU_ABI2;
        tags = "标签" + Build.TAGS;
        device = "驱动" + Build.DEVICE;
        display = "屏幕" + Build.DISPLAY;
        brand = "品牌" + Build.BRAND;
        host = "主机地址" + Build.HOST;

    }
}
