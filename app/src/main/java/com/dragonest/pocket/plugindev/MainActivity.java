package com.dragonest.pocket.plugindev;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dragonest.pocket.annotation.DIActivity;
import com.dragonest.pocket.annotation.DIView;
import com.dragonest.pocket.annotation.Test;

import org.jetbrains.annotations.TestOnly;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 11/26/21
 * @project Plugin Dev
 * @instructions
 */

@Test
@DIActivity
public class MainActivity extends AppCompatActivity {


    @DIView(value = R.id.btn)
    TextView btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DIMainActivity.bindView(this);
        btn.setOnClickListener(v -> Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show());
    }


}
