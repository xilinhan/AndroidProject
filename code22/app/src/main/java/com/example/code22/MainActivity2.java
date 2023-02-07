package com.example.code22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "xilh2";
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mButton = findViewById(R.id.button2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Log.i(TAG, "onCreate2: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart2: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume2: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart2: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause2: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop2: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy2: ");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i(TAG, "onConfigurationChanged2: ORIENTATION_PORTRAIT");
        }else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i(TAG, "onConfigurationChanged2: ORIENTATION_LANDSCAPE");
        }else {
            Log.i(TAG, "onConfigurationChanged2: UNDEFINED");
        }
    }
}