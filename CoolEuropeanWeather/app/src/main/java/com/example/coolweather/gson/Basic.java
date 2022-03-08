package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author xilinhan
 * @description: 使用@SerializedName注解来将对象里的属性跟json里字段对应值匹配起来,建立一定的映射关系
 * @date :2022/3/8 18:28
 */
public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String updateTime;
    }
}
