package com.example.coolweather.draw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coolweather.R;

public class my_test_view extends AppCompatActivity {

    Button button1;
    Button button2;
    topBarClickListener mmTopBarClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test_view);

        //按钮得点击事件，由于每个调用者所需要这些按钮能够实现得功能都是不一样的，因此，不能直接在UI模板中添加具体的实现逻辑，只能通过接口回调的思想，将具体的实现逻辑交给调用者。
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmTopBarClickListener.rightClick();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmTopBarClickListener.leftClick();
            }
        });

    }

    //暴暴露一个方法给调用者来注册接口回调
    //通过接口来获得回调者对接口方法得实现
    public void setOnTopClickListener(topBarClickListener mTopBarClickListener){
        this.mmTopBarClickListener = mTopBarClickListener;
    }
}