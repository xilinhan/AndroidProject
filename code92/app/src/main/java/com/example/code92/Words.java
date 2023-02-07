package com.example.code92;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/18 11:54
 */
public class Words {
    //定义该ContentProvider的Authorities
    public static final String AUTHORITY = "com.example.code92.dictprovider";
    //定义一个静态内部类，定义该ContentProvider所包含的数据列的列名
    public static final class Word implements BaseColumns{
        //定义Content所允许的三个数据列
        public final static String _ID = "_id";
        public final static String WORD = "word";
        public final static String DETAIL = "detail";
        //定义该Content提供服务的两个URi
        public final static Uri DICT_URI = Uri.parse("content://" + AUTHORITY + "/words");
        public final static Uri WORD_URI = Uri.parse("content://" + AUTHORITY + "/word");
    }
}
