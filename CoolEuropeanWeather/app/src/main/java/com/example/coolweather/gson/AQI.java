package com.example.coolweather.gson;

/**
 * @author xilinhan
 * @description: 空气质量指数（Air Quality Index，简称AQI）
 * @date :2022/3/8 18:33
 */
public class AQI {

    public AQICity city;

    public class AQICity{

        public String aqi;

        public String pm25;
    }
}
