package com.dragonest.pocket.testmodule1;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dragonest.pocket.routeframework.BRouter;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 11/30/21
 * @project Plugin Dev
 * @instructions
 */
public class ActivityUser extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        findViewById(R.id.btn).setOnClickListener(v -> {
            Bundle bundle=new Bundle();
            bundle.putString("test","hi kobe");
            BRouter.getInstance().jump("/kobe",bundle);
        });
    }
}
