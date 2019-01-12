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

public class OperationsFragment extends Fragment {

    private setOperationsListener listener;
    private ImageButton imageButton;
    private boolean option;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operations, container, false);
        option = false;
        imageButton = view.findViewById(R.id.imageButtonPlay);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//if options is false the sound will start up, but if it's true the sound will stop.
                if (!option){
                    option = true;
                    imageButton.setImageResource(R.drawable.ic_pause_coral);
                    listener.onOperations(option);
                }else{
                    option = false;
                    imageButton.setImageResource(R.drawable.ic_play_arrow_coral);
                    listener.onOperations(option);
                }
            }
        });
        return view;
    }

    public interface setOperationsListener{
        void onOperations(boolean optionSelected);
    }

    @Override
    public void onAttach(Context context) {//If a context implement the setOperationsListener interface. assign this to interface
        super.onAttach(context);
        if (context instanceof setOperationsListener){
            listener = (setOperationsListener) context;
        }else{
            throw new RuntimeException(context + "must implement the setOperationsListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
