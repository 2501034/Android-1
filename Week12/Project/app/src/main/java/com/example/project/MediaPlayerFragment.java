package com.example.project;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class MediaPlayerFragment extends Fragment {

    private SwitchCompat uiSwitch;
    private TextView textView;
    private TextView start, duration, end;
    private SeekBar seekBar;
    private Button button;
    private View customLayout;
    private View controlView;

    private MediaController controller;
    private boolean prepared = false;
    private MediaPlayerManager manager;
    private String[] NAME = {"코난", "Lemon Tree", "풍문으로 들었소", "헤어졌다 만났다"};
    private int type = 1;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable updater = new Runnable() {

        @Override
        public void run() {
            if (prepared && manager.isPlaying()) {
                updateUI();
                handler.postDelayed(this, 500);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mediaplayer, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uiSwitch = view.findViewById(R.id.toggle);
        textView = view.findViewById(R.id.textView);
        start = view.findViewById(R.id.start);
        duration = view.findViewById(R.id.duration);
        end = view.findViewById(R.id.end);
        seekBar = view.findViewById(R.id.seekBar);
        button = view.findViewById(R.id.button);
        customLayout = view.findViewById(R.id.customLayout);
        controlView = view.findViewById(R.id.controller);

        setupController();
        setupSwitch();
        setupButton();
        setSeekBar();
        addMenu();
        loadSource(type);
    }

    private void loadSource(int type) {
        prepared = false;
        handler.removeCallbacks(updater);
        try {
            manager.load(requireContext(), type, new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    prepared = true;
                    textView.setText(NAME[type - 1]);
                    updateUI();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addMenu() {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@org.jspecify.annotations.NonNull Menu menu, @org.jspecify.annotations.NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@org.jspecify.annotations.NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.item1){
                    type = 1;
                } else if (id == R.id.item2) {
                    type = 2;
                } else if (id == R.id.item3) {
                    type = 3;
                } else if (id == R.id.item4) {
                    type = 4;
                }
                menuItem.setChecked(true);
                return true;
            }
        });
    }

    private void setSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && prepared) {
                    manager.seekTo(progress);
                    duration.setText(TimeUtil.convert(progress));
                    textView.setText(NAME[type - 1]);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void setupButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prepared) {
                    if (manager.isPlaying()) {
                        manager.pause();
                        handler.removeCallbacks(updater);

                    } else {
                        manager.play();
                        handler.post(updater);
                    }
                    updateUI();
                }
            }
        });
    }



    private void updateUI() {
        if (prepared) {
            int position = manager.getDuration();
            int current = manager.getCurrentPosition();
            seekBar.setMax(position);
            seekBar.setProgress(current);
            start.setText("00:00");
            duration.setText(TimeUtil.convert(current));
            end.setText(TimeUtil.convert(position));
            button.setText(manager.isPlaying() ? "정지" : "재생");
        }
    }

    private void setupSwitch() {
        uiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    customLayout.setVisibility(GONE);
                    controlView.setVisibility(VISIBLE);
                    controller.show(0);
                }else {
                    customLayout.setVisibility(VISIBLE);
                    controlView.setVisibility(GONE);
                    controller.show();
                }
            }
        });
    }

    private void setupController() {
        MediaController.MediaPlayerControl control = new MediaController.MediaPlayerControl() {
            @Override
            public boolean canPause() {
                return false;
            }

            @Override
            public boolean canSeekBackward() {
                return false;
            }

            @Override
            public boolean canSeekForward() {
                return false;
            }

            @Override
            public int getAudioSessionId() {
                return 0;
            }

            @Override
            public int getBufferPercentage() {
                return 0;
            }

            @Override
            public int getCurrentPosition() {
                return 0;
            }

            @Override
            public int getDuration() {
                return 0;
            }

            @Override
            public boolean isPlaying() {
                return false;
            }

            @Override
            public void pause() {

            }

            @Override
            public void seekTo(int pos) {

            }

            @Override
            public void start() {

            }
        };
        controller = new MediaController(requireContext());
        controller.setAnchorView(controlView);
        controller.setMediaPlayer(control);
    }



}
