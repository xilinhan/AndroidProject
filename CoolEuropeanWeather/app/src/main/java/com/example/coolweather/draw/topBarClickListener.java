package com.example.coolweather.draw;

/**
 * @author xilinhan
 * @description: 接口对象，实现回调机制，在回调方法中，通过映射得接口对象调用接口中得方法，而不用去考虑如何实现，具体的实现由调用者去创建
 * @date :2022/3/20 12:35
 */
public interface topBarClickListener {
    void leftClick();
    void rightClick();
}
