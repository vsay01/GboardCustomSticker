package com.demo.sticker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    Button mBtnAddSticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBtnAddSticker = findViewById(R.id.btn_add_sticker);
        mBtnAddSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppIndexingUpdateService.enqueueWork(HomeActivity.this);
            }
        });
    }
}