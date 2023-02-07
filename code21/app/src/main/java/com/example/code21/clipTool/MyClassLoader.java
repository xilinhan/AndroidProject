package com.example.code21.clipTool;

/**
 * @author xilinhan
 * @description:
 * @date :2022/7/30 19:26
 */
public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}
