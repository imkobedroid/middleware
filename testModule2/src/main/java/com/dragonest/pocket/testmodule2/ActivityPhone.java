package com.dragonest.pocket.testmodule2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dragonest.pocket.annotation.Path;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 11/30/21
 * @project Plugin Dev
 * @instructions
 */
@Path("/kobe")
public class ActivityPhone extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_phone);

        findViewById(R.id.btn).setOnClickListener(v -> {
            String s = getIntent().getExtras().getString("test");
            Toast.makeText(ActivityPhone.this, s, Toast.LENGTH_SHORT).show();
        });
    }
}
