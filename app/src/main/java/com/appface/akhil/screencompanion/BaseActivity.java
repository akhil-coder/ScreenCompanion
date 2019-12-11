package com.appface.akhil.screencompanion;

import android.os.Bundle;

import com.blankj.utilcode.util.Utils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.init(this);
    }
}
