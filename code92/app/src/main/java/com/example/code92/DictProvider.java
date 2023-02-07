package com.example.code92;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/18 13:19
 */
public class DictProvider extends ContentProvider {

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;
    private static final String PATH_WORDS = "words";
    private static final String PATH_WORD = "word/#";
    private MyDatabaseHelper deOpenHelper;
    static {
        //为UriMatcher注册两个Uri
        sUriMatcher.addURI(Words.AUTHORITY, PATH_WORDS, WORDS);
        sUriMatcher.addURI(Words.AUTHORITY, PATH_WORD, WORD);
    }
    //第一次调用该DictProvider的时候，系统先创建DictProvider对象，并且回调该方法
    @Override
    public boolean onCreate() {
        deOpenHelper = new MyDatabaseHelper(this.getContext(), "myDict.db3", null, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    //返回制定Uri参数对应的数据的MIME类型
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)){
            case WORDS:
                return "vnd.android.cursor.dir/com.example.code92.dict";
            case WORD:
                return "vnd.android.cursor.item/com.example.code92.dict";
            default:
                //扔出非法参数异常
                throw  new IllegalArgumentException("未知Uri" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //获取数据库实例
        SQLiteDatabase sqLiteDatabase = deOpenHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)){
            case WORDS:
                long rowId = sqLiteDatabase.insert("dict", Words.Word._ID, values);
                if(rowId > 0){
                    //在已有的Uri后面追加ID
                    Uri wordUri = ContentUris.withAppendedId(uri, rowId);
                    //通知数据已经改变
                    getContext().getContentResolver().notifyChange(wordUri, null);
                    return wordUri;
                }
            default:
                throw new IllegalStateException("未知URi ：" + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        //在删除操作进行后，需要通知数据已经改变

        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
