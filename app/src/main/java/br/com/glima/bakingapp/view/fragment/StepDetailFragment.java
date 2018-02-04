package br.com.glima.bakingapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Step;
import br.com.glima.bakingapp.databinding.FragmentStepDetailBinding;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by gustavo.lima on 28/01/18.
 */

public class StepDetailFragment extends Fragment {

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
		Step step = getArguments().getParcelable(ARGUMENT_STEP);
		binding.setStep(step);
	}
}
