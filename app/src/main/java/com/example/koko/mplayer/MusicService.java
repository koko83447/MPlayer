package com.example.koko.mplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by koko on 2017/5/30.
 */

public class MusicService extends Service {
    private MediaPlayer player;

    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.qwer);
        try {
            player.setOnCompletionListener(listener);
            player.prepare();
        } catch (Exception ex) {
            Log.d("MPlayer", "onCreate:" + ex.getMessage());
        }
    }

    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer nouse) {
            try {
                player.stop();
                player.prepare();
            } catch (Exception ex) {
                Log.d("MPlayer", "Listener:" + ex.getMessage());
            }
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Boolean isPause = intent.getBooleanExtra("ISPAUSE", true);
        try {
            if (isPause == true) {
                if (player.isPlaying() == true)
                    player.pause();
            } else {
                player.start();
            }
        } catch (Exception ex) {
            Log.d("MPlayer", "onStart():" + ex.getMessage());
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        try {
            player.stop();
            player.prepare();
        } catch (Exception ex) {
            Log.d("MPlayer", "onDestroy():" + ex.getMessage());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}