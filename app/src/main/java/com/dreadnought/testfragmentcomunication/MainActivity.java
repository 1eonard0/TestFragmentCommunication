package com.dreadnought.testfragmentcomunication;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements OperationsFragment.setOperationsListener, VolumeFragment.setOnVolumeChangeListener{

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.at_the_game);//My sound
        mediaPlayer.setLooping(true);//enable repeat one and another time
        mediaPlayer.setVolume(.5f,.5f);//default volume

        getSupportFragmentManager().beginTransaction()//Add two fragments
                .replace(R.id.frameLayoutOptions, new OperationsFragment())
                .replace(R.id.frameLayoutVolume,new VolumeFragment())
                .commit();
    }

    @Override
    public void onOperations(boolean optionSelected) {// method implemented from OperationsFragment
        if (optionSelected && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }else if (!optionSelected && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    public void onVolumenChanged(boolean isButtom, boolean mute, float volume) {// method implemented from OperationsFragment
        if (isButtom){
            if (mute){
                mediaPlayer.setVolume(0f,0f);
            }else{
                mediaPlayer.setVolume(volume,volume);
            }
        }else{
            mediaPlayer.setVolume(volume,volume);
        }
    }
}
