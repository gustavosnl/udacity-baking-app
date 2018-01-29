package br.com.glima.bakingapp.view;

import android.support.v4.app.Fragment;

import br.com.glima.bakingapp.business.Step;

/**
 * Created by gustavo.lima on 28/01/18.
 */

public class StepDetailFragment extends Fragment {
	private Step step;
	public void setStep(Step step) {
		this.step = step;
	}
}
