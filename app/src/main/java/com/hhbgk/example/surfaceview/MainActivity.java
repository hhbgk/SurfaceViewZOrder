package com.hhbgk.example.surfaceview;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
	private final String TAG = getClass().getSimpleName();
	private MediaPlayer mMediaPlayerOne;
	private MediaPlayer mMediaPlayerOther;
	private SurfaceView mSurfaceViewOne;
	private SurfaceView mSurfaceViewOther;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSurfaceViewOne = (SurfaceView) findViewById(R.id.surface1);

		mSurfaceViewOther = (SurfaceView) findViewById(R.id.surface2);
		mSurfaceViewOther.setZOrderOnTop(true);

		SurfaceHolder surfaceHolderOne = mSurfaceViewOne.getHolder();
		surfaceHolderOne.addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceCreated(SurfaceHolder surfaceHolder) {
				String path = Environment.getExternalStorageDirectory() + "/" + "/Videos/vlc-player.mp4";
				mMediaPlayerOne = createMediaPlayer(mSurfaceViewOne.getHolder(), path);
				if (mMediaPlayerOne != null){
					mMediaPlayerOne.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mediaPlayer) {
							mMediaPlayerOne.start();
						}
					});
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

			}

			@Override
			public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

			}
		});

		SurfaceHolder surfaceHolderOther = mSurfaceViewOther.getHolder();
		surfaceHolderOther.addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceCreated(SurfaceHolder surfaceHolder) {
				String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "Videos/SampleVideo_360x240_50mb.mp4";

				mMediaPlayerOther = createMediaPlayer(mSurfaceViewOther.getHolder(), path);
				if (mMediaPlayerOther != null){
					mMediaPlayerOther.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mediaPlayer) {
							mMediaPlayerOther.start();
						}
					});
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

			}

			@Override
			public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

			}
		});
	}

	private MediaPlayer createMediaPlayer(SurfaceHolder holder, String path){
		MediaPlayer mediaPlayer = new MediaPlayer();
		mediaPlayer.setDisplay(holder);
		try {
			mediaPlayer.setDataSource(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		mediaPlayer.setDisplay(holder);
		try {
			mediaPlayer.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaPlayer;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mMediaPlayerOne != null){
			if (mMediaPlayerOne.isPlaying())
				mMediaPlayerOne.stop();
			mMediaPlayerOne.reset();
			mMediaPlayerOne.reset();
			mMediaPlayerOne = null;
		}

		if (mMediaPlayerOther != null){
			if (mMediaPlayerOther.isPlaying())
				mMediaPlayerOther.stop();
			mMediaPlayerOther.reset();
			mMediaPlayerOther.reset();
			mMediaPlayerOther = null;
		}
	}
}
