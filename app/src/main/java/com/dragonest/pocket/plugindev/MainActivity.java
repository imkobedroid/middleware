package com.dragonest.pocket.plugindev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dragonest.pocket.annotation.DIActivity;
import com.dragonest.pocket.annotation.DIView;
import com.dragonest.pocket.annotation.Test;
import com.dragonest.pocket.routeframework.BRouter;
import com.dragonest.pocket.testmodule1.ActivityUser;

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
        BRouter.getInstance().init(this);
        btn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ActivityUser.class))
                );
    }


}
