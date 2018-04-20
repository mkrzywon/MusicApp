package com.example.android.musicapp;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {

    private static final String KEY_IMAGE = "image";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SOUND = "sound";

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private final int forwardTime = 5000;
    private final int backwardTime = 5000;
    private int audioResource;

    private double startTime = 0;
    private double finalTime = 0;

    @BindView(R.id.back_to_fragment_button) ImageButton backButton;
    @BindView(R.id.play_button) ImageButton playButton;
    @BindView(R.id.pause_button) ImageButton pauseButton;
    @BindView(R.id.stop_button) ImageButton stopButton;
    @BindView(R.id.forward_button) ImageButton forwardButton;
    @BindView(R.id.backward_button) ImageButton backwardButton;

    @BindView(R.id.player_image) ImageView playerImage;

    @BindView(R.id.now_playing) TextView nowPlaying;
    @BindView(R.id.volume_control) TextView volumeControl;
    @BindView(R.id.player_author) TextView playerAuthor;
    @BindView(R.id.player_song_title) TextView playerTitle;

    @BindView(R.id.seekBar) SeekBar seekBar;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            switch (focusChange) {

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player);

        ButterKnife.bind(this);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            int mplaylistImageId = bundle.getInt(KEY_IMAGE);
            int mAuthorId = bundle.getInt(KEY_AUTHOR);
            int mTilteId = bundle.getInt(KEY_TITLE);
            int mAudioResourceId = bundle.getInt(KEY_SOUND);

            initialisation();
            animations();
            buttons();

            playerImage.setImageResource(mplaylistImageId);
            playerAuthor.setText(mAuthorId);
            playerTitle.setText(mTilteId);
            String tempAudio = String.valueOf(mAudioResourceId);
            audioResource = Integer.valueOf(tempAudio);

        }
    }

    private void initialisation() {

        playButton.setSelected(false);
        pauseButton.setSelected(false);

    }

    private void animations() {

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(playerAuthor, getString(R.string.alpha), 0, 1);
        anim1.setDuration(5000);
        anim1.start();

        ObjectAnimator anim2 = ObjectAnimator.ofFloat(playerTitle, getString(R.string.alpha), 0, 1);
        anim2.setDuration(7000);
        anim2.start();

        ObjectAnimator anim3 = ObjectAnimator.ofFloat(nowPlaying, getString(R.string.scalex), 0, 1);
        anim3.setDuration(2000);
        anim3.start();

    }

    private void buttons() {

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(getApplicationContext(), audioResource);

                    // Start the audio file
                    mMediaPlayer.start();

                    finalTime = mMediaPlayer.getDuration();
                    startTime = mMediaPlayer.getCurrentPosition();

                    playButton.setSelected(true);
                    pauseButton.setSelected(false);

                    setVolume();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

                playButton.setSelected(false);
                pauseButton.setSelected(true);

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMediaPlayer.stop();

                playButton.setSelected(false);
                pauseButton.setSelected(false);

            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mMediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),getString(R.string.jumped_forward),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),getString(R.string.no_jump_forward),Toast.LENGTH_SHORT).show();
                }
            }
        });

        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int temp = (int)startTime;

                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mMediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),getString(R.string.jumped_backward),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),getString(R.string.no_jump_backward),Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                //Play sound while pressing the button
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
                mp.start();
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();

    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void setVolume() {
        try {
            seekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            seekBar.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            volumeControl.setText(String.valueOf(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC)));

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onStopTrackingTouch(SeekBar arg0){
                    //Touch gesture is finished
                }

                public void onStartTrackingTouch(SeekBar arg0){
                    //Touch gesture has started
                }

                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2){
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    volumeControl.setText(String.valueOf(progress));
                }
            });
        }
        catch (Exception e){
            e.getMessage();
        }
    }
}
