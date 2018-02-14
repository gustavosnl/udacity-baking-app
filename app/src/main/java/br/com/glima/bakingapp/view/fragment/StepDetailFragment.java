package br.com.glima.bakingapp.view.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Step;
import br.com.glima.bakingapp.databinding.FragmentStepDetailBinding;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by gustavo.lima on 28/01/18.
 */

public class StepDetailFragment extends Fragment implements ExoPlayer.EventListener {

	private SimpleExoPlayer mExoPlayer;

	private static final String ARGUMENT_STEP = "step_argument";
	private FragmentStepDetailBinding binding;

	public StepDetailFragment() {
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		binding = inflate(inflater, R.layout.fragment_step_detail, container, false);
		return binding.getRoot();
	}

	public void setStep(Step step) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(ARGUMENT_STEP, step);
		setArguments(bundle);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (getArguments() != null) {
			Step step = getArguments().getParcelable(ARGUMENT_STEP);
			binding.setStep(step);
			initializePlayer(step.getMediaUri());
		}
	}

	private void initializePlayer(Uri mediaUri) {
		if (mExoPlayer == null) {
			TrackSelector trackSelector = new DefaultTrackSelector();
			LoadControl loadControl = new DefaultLoadControl();
			mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
			binding.videoView.setPlayer(mExoPlayer);

			String userAgent = Util.getUserAgent(getContext(), "udacity-baking-app");

			MediaSource mediaSource = new ExtractorMediaSource(
					mediaUri,
					new DefaultDataSourceFactory(getContext(), userAgent),
					new DefaultExtractorsFactory(), null, null);

			mExoPlayer.prepare(mediaSource);
			mExoPlayer.setPlayWhenReady(true);
			mExoPlayer.addListener(this);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mExoPlayer.release();
	}

	@Override
	public void onTimelineChanged(Timeline timeline, Object manifest) {

	}

	@Override
	public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

	}

	@Override
	public void onLoadingChanged(boolean isLoading) {

	}

	@Override
	public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

	}

	@Override
	public void onRepeatModeChanged(int repeatMode) {

	}

	@Override
	public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

	}

	@Override
	public void onPlayerError(ExoPlaybackException error) {

	}

	@Override
	public void onPositionDiscontinuity(int reason) {

	}
	@Override
	public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

	}

	@Override
	public void onSeekProcessed() {

	}
}
