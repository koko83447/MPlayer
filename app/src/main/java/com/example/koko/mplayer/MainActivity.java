package com.example.koko.mplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView)findViewById(R.id.lblOutput);
    }
    public void btnStart_Click(View view){
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("ISPAUSE",false);
        startService(intent);
        output.setText("播放中...");
    }
    public void btnPause_Click(View view){
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("ISPAUSE",true);
        startService(intent);
        output.setText("暫停中...");
    }
    public void btnStop_Click(View view){
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
        output.setText("停止播放");
    }
}
