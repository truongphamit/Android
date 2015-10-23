package com.android.truongpq.tet;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ListSMSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.guide:
                new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_menu_help).setTitle("Hướng dẫn")
                        .setMessage("Chạm để tùy chọn gửi hoặc sao chép, giữ để thêm hoặc bỏ yêu thích.")
                        .show();
                break;
            case R.id.introduce:
                new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_info).setTitle("Giới thiệu")
                        .setMessage("Những mẫu tin nhắn yêu thương giúp bạn bày tỏ tình cảm với những người thân yêu.")
                        .show();
                break;
            case R.id.contact:
                new AlertDialog.Builder(this).setIcon(android.R.drawable.stat_sys_speakerphone).setTitle("Liên hệ")
                        .setMessage("Họ tên: Phạm Quang Trường \n Email: TruongPhamIt@gmail.com")
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
