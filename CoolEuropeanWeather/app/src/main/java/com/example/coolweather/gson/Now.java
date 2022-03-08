package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/8 18:38
 */
public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {

        @SerializedName("txt")
        public String info;

    }
}
