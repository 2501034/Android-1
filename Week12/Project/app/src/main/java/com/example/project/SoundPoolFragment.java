package com.example.project;

import static android.widget.Toast.LENGTH_SHORT;
import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class SoundPoolFragment extends Fragment {

    private SoundPool pool;
    private boolean loaded = false;
    private final int[] soundID = new int[4];
    private Button button1, button2, button3, button4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sound, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AudioManager manager = (AudioManager) requireContext().getSystemService(Context.AUDIO_SERVICE);
        double currentvol = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
        double maxVol = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = (float) (currentvol / maxVol);

        ToggleButton toggleButton = view.findViewById(R.id.toggle);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AudioAttributes.Builder builder = new AudioAttributes.Builder();
                    builder.setUsage(AudioAttributes.USAGE_GAME);
                    builder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
                    AudioAttributes attributes = builder.build();

                    SoundPool.Builder builder1 = new SoundPool.Builder();
                    builder1.setAudioAttributes(attributes);
                    builder1.setMaxStreams(5);
                    pool = builder1.build();
                    pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        @Override
                        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                            loaded = true;
                        }
                    });
                    try {
                        soundID[0] = pool.load(getContext(), R.raw.destroy, 1);
                        soundID[1] = loadAsset("gun.wav");
                        soundID[2] = pool.load(getContext(), R.raw.one, 1);
                        soundID[3] = loadAsset("two.wav");
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), LENGTH_SHORT).show();
                    }
                    setButton(true);
                } else {
                    pool.release();
                    loaded = false;
                    setButton(false);
                }
            }
        });
        button1 = view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loaded)
                    pool.play(soundID[0], volume, volume, 1, 0, 1f);
            }
        });

        button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loaded)
                    pool.play(soundID[1], volume, volume, 1, 0, 1);
            }
        });

        button3 = view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loaded)
                    pool.play(soundID[2], 1, 1, 0, 0, 1);
                rotateButton(button3,1000);
            }
        });

        button4 = view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loaded) {
                    pool.play(soundID[3], 1, 1, 0, 0, 1);
                    rotateButton(button4, 2000);
                }
            }
        });
        toggleButton.performClick();
    }

    private int loadAsset(String s) {
        AssetFileDescriptor descriptor = null;
        try {
            descriptor = requireContext().getAssets().openFd(s);
        }catch (IOException e) {
            e.getMessage();
        }
        return pool.load(descriptor, 1);
    }

    private void setButton(boolean b) {
        button1.setEnabled(b);
        button2.setEnabled(b);
        button3.setEnabled(b);
        button4.setEnabled(b);
    }


    private void rotateButton(Button button, int i) {
        RotateAnimation animation = new RotateAnimation(0, 360, button.getWidth(),
                button.getHeight() / 2.0f);
        animation.setDuration(i);
        button.startAnimation(animation);
    }

    private int loadedAsset(String s) {
        AssetFileDescriptor descriptor = null;
        try {
            descriptor = requireContext().getAssets().openFd(s);
        }catch (IOException e) {
            e.getMessage();
        }
        return pool.load(descriptor, 1);
    }
}


