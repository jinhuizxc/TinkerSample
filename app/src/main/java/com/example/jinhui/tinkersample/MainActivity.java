package com.example.jinhui.tinkersample;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"onCreate");

        Log.e(TAG,"i am on patch log");
        etUserName = (EditText) findViewById(R.id.et_user_name);

        findViewById(R.id.btn_show_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name = etUserName.getText().toString();
//                Toast.makeText(getApplicationContext(),"输入的名字为:" + name,Toast.LENGTH_SHORT).show();
                // 测试0为bug版本，然后测试1为补丁版本
                Toast.makeText(v.getContext(), "" + (1 / 1), Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_load_patch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patchPath  = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tinkersample/patch_signed_7zip.apk";
                // V/MainActivity: 路径位置 =/storage/emulated/0
                Log.v(TAG,"路径位置 =" + Environment.getExternalStorageDirectory().getAbsolutePath());
                File file = new File(patchPath);
                if (file.exists()) {
                    Toast.makeText(MainActivity.this, "补丁文件存在", Toast.LENGTH_SHORT).show();
                    TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patchPath);
                    // 执行过之后进程kill，补丁文件默认被删除，然后打开刚才的demo，点击按钮弹出1，这或许就是热更新吧！
                } else {
                    Toast.makeText(MainActivity.this, "补丁文件不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
