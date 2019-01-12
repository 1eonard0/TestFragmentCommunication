package com.dreadnought.testfragmentcomunication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class VolumeFragment extends Fragment {

    private setOnVolumeChangeListener listener;
    private ImageButton imageButton;
    private boolean mute;
    private SeekBar seekBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume,container,false);
        mute = false;
        imageButton = view.findViewById(R.id.imageButtonMute);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mute){//If the mute is false sound's volume will set to 0 and the imagebutton will change. But if mute is true the volume
                    //will be the current according to the current position of the seekbar
                    mute = true;
                    imageButton.setImageResource(R.drawable.ic_volume_off_coral);
                    listener.onVolumenChanged(true,mute,0f);
                }else{
                    mute = false;
                    imageButton.setImageResource(R.drawable.ic_volume_up_coral);
                    listener.onVolumenChanged(true,mute,(float)(seekBar.getProgress()*0.01));
                }
            }
        });
        seekBar = view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {//everytime that seekbar is moved the sound's volume will be actualized
                listener.onVolumenChanged(false,mute,(float)(progress*0.01));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }

    public interface setOnVolumeChangeListener{
        void onVolumenChanged(boolean isButtom, boolean mute, float volume);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof setOnVolumeChangeListener){//If a context implement the setOnVolumeChangeListener interface. assign this to interface
            listener = (setOnVolumeChangeListener)context;
        }else{
            throw new RuntimeException(context + "must implement the setOnVolumeChangeListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
