package com.example.recyclerviewdemo;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/26 18:48
 */
public class Data {

    private String mString;
    private int type;

    public Data() {
    }

    public Data(String string) {
        mString = string;
    }

    public String getString() {
        return mString;
    }

    public void setString(String string) {
        mString = string;
    }

    public int getType() {
        return type;
    }
}
