package com.example.bear.programmerdaily.activity;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.bear.programmerdaily.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioPlayerActivity extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.play_btn)
    ImageButton playBtn;
    private Handler mProgressRefresher;
    private AudioManager mAudioManager;
    private PreviewPlayer mPlayer;
    private int mDuration;
    private boolean mSeeking = false;
    private Uri mUri;
    private String musicPath;
    private int mMediaId;
    private boolean mUiPaused;
    private boolean mPausedByTransientLossOfFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        mUri = intent.getData();
        if (mUri == null) {
            finish();
            return;
        }
        String scheme = mUri.getScheme();
        setContentView(R.layout.activity_audio_player);
        ButterKnife.bind(this);

        mProgressRefresher = new Handler();
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        PreviewPlayer player = (PreviewPlayer) getLastNonConfigurationInstance();

        if (player == null) {
            mPlayer = new PreviewPlayer();
            mPlayer.setActivity(this);
            try {
                mPlayer.setDataSourceAndPrepare(mUri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mPlayer = player;
            mPlayer.setActivity(this);
        }
        AsyncQueryHandler mAsyncQueryHandler = new AsyncQueryHandler(getContentResolver()) {
            @Override
            protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
                if (cursor != null && cursor.moveToFirst()) {
                    int idIdx = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                    if (idIdx >0 ) {
                        mMediaId = idIdx;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        };
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        seekBar.setProgress(mDuration);
        updatePlayPause();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this,"播放失败！",Toast.LENGTH_SHORT).show();
        finish();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (isFinishing()) return;
        mPlayer = (PreviewPlayer) mp;
        mPlayer.start();
        showPostPrepareUI();
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        PreviewPlayer player = mPlayer;
        mPlayer = null;
        return player;
    }

    private void showPostPrepareUI() {
        mDuration = mPlayer.getDuration();
        if (mDuration!= 0) {
            seekBar.setMax(mDuration);
            if (!mSeeking) {
                seekBar.setProgress(mPlayer.getCurrentPosition());
            }
        }
        seekBar.setOnSeekBarChangeListener(mSeekListener);
        mAudioManager.requestAudioFocus(mAudioFocusListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (mProgressRefresher != null) {
            mProgressRefresher.removeCallbacksAndMessages(null);
            mProgressRefresher.postDelayed(new ProgressRefresher(),200);
        }
    }
    class ProgressRefresher implements Runnable {

        @Override
        public void run() {
            if (mPlayer != null && !mSeeking && mDuration != 0) {
                seekBar.setProgress(mPlayer.getCurrentPosition());
            }
            mProgressRefresher.removeCallbacksAndMessages(null);
            if (!mUiPaused) {
                mProgressRefresher.postDelayed(new ProgressRefresher(),200);
            }
        }
    }
    @OnClick(R.id.play_btn)
    public void playPauseClick() {
        if (mPlayer == null) {
            return;
        }
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
        updatePlayPause();
    }
    private void updatePlayPause() {
        if (playBtn != null && mPlayer != null) {
            if (mPlayer.isPlaying()) {
                playBtn.setImageResource(R.drawable.ic_pause_24dp);
            } else {
                playBtn.setImageResource(R.drawable.ic_play_arrow_24dp);
                mProgressRefresher.removeCallbacksAndMessages(null);
            }
        }
    }
    private SeekBar.OnSeekBarChangeListener mSeekListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (!fromUser) {
                return;
            }
            if (mPlayer == null) {
                return;
            }
            mPlayer.seekTo(progress);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            mSeeking = true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mSeeking = false;
        }
    };
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {

        @Override
        public void onAudioFocusChange(int focusChange) {
            if (mPlayer == null) {
                mAudioManager.abandonAudioFocus(this);
                return;
            }
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    mPausedByTransientLossOfFocus = false;
                    mPlayer.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    if (mPlayer.isPlaying()) {
                        mPausedByTransientLossOfFocus = true;
                        mPlayer.pause();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    if (mPausedByTransientLossOfFocus) {
                        mPausedByTransientLossOfFocus = false;
                        start();
                    }
                    break;
            }
            updatePlayPause();
        }
    };

    private void start() {
        mAudioManager.requestAudioFocus(mAudioFocusListener,AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        mPlayer.start();
        mProgressRefresher.postDelayed(new ProgressRefresher(), 200);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUiPaused = false;
        if (mPlayer.isPrepared()) {
            showPostPrepareUI();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mUiPaused = true;
        if (mProgressRefresher != null) {
            mProgressRefresher.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onDestroy() {
        stopPlayback();
        super.onDestroy();
    }

    @Override
    protected void onUserLeaveHint() {
        stopPlayback();
        finish();
        super.onUserLeaveHint();
    }

    private void stopPlayback() {
        if (mProgressRefresher != null) {
            mProgressRefresher.removeCallbacksAndMessages(null);
        }
        if (mPlayer !=null) {
            mPlayer.release();
            mPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusListener);
        }
    }

    public void playMusic(){

    }

    private static class PreviewPlayer extends MediaPlayer implements MediaPlayer.OnPreparedListener {
        boolean mIsPrepared = false;
        AudioPlayerActivity mAudioPlayerActivity;

        @Override
        public void onPrepared(MediaPlayer mp) {
            mIsPrepared = true;
            mAudioPlayerActivity.onPrepared(mp);
        }
        public void setActivity(AudioPlayerActivity activity) {
            mAudioPlayerActivity = activity;
            setOnPreparedListener(this);
            setOnErrorListener(mAudioPlayerActivity);
            setOnCompletionListener(mAudioPlayerActivity);
        }
        public void setDataSourceAndPrepare(Uri uri) throws IllegalArgumentException,SecurityException, IllegalStateException, IOException {
            setDataSource(mAudioPlayerActivity,uri);
            prepareAsync();
        }
        boolean isPrepared() {
            return mIsPrepared;
        }
    }
}
