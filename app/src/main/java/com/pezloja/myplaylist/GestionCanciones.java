package com.pezloja.myplaylist;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.SeekBar;

public class GestionCanciones {
    static Runnable handlerTask;
    static MediaPlayer mp;
    static SeekBar sk_musica2;

    public static void startCancion(Cancion c, Context contexto, SeekBar sk_musica) {
        if (mp == null) {
            mp = MediaPlayer.create(contexto, c.getAudio());
            sk_musica.setMax(mp.getDuration());
        } else if (mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = MediaPlayer.create(contexto, c.getAudio());
            sk_musica.setMax(mp.getDuration());
        }
        sk_musica2 = sk_musica;
        mp.start();
        startTimer(sk_musica);
    }

    public static void pauseCancion() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }
    }

    public static void stopCancion(SeekBar sk_musica) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
            sk_musica.setProgress(0);
        }
    }

    public static void mover(int posicion) {
        if (mp != null) {
            mp.seekTo(posicion);
        }
    }

    public static void startTimer(SeekBar sk_musica) {
        handlerTask = () -> {
            if (mp != null && mp.isPlaying()) {
                sk_musica.setProgress(mp.getCurrentPosition());
                new Handler().postDelayed(handlerTask, 1000);
            }
        };
        handlerTask.run();
    }
}
