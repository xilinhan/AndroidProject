package com.example.code19;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/22 17:54
 */
public class DialogUtil {
    public static void showDialog(Context context, String s, boolean goHome){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(s).setCancelable(false);
        if(goHome){
            builder.setPositiveButton("ok", (dialog, which) -> {
                Intent intent = new Intent(context, AuctionClientActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            });
        }else {
            builder.setPositiveButton("ok", null);
        }
        builder.create().show();
    }

    public static void showDialog(Context context, View view){
        new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("ok", null)
                .create()
                .show();
    }
}
