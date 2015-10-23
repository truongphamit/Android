package com.android.truongpq.tet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            case R.id.introduce:
                new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_info).setTitle("Giới thiệu")
                        .setMessage("Những mẫu tin nhắn yêu thương giúp bạn bày tỏ tình cảm với những người thân yêu.")
                        .show();
                break;
            case R.id.contact:
                new AlertDialog.Builder(this).setIcon(android.R.drawable.stat_sys_speakerphone).setTitle("Liên hệ")
                        .setMessage("Họ tên: Phạm Quang Trường\nEmail: TruongPhamIt@gmail.com")
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Thoát")
                .setMessage("Bạn có chắc chắn muốn thoát?")
                .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("Hủy", null).show();
    }
}
