package br.com.glima.bakingapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Step;
import br.com.glima.bakingapp.databinding.StepItemBinding;

import static android.databinding.DataBindingUtil.bind;
import static android.view.LayoutInflater.from;

/**
 * Created by gustavo.lima on 28/01/18.
 */

class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

	private List<Step> steps = new ArrayList<>();

	public StepsAdapter(List<Step> steps) {
		this.steps.addAll(steps);
	}

	@Override
	public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		StepItemBinding binding = bind(from(parent.getContext()).inflate(R.layout.step_item, parent, false));
		return new StepViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(StepViewHolder holder, int position) {
		holder.setStep(steps.get(position));
	}

	@Override
	public int getItemCount() {
		return steps.size();
	}


	class StepViewHolder extends RecyclerView.ViewHolder {
		private StepItemBinding binding;

		public StepViewHolder(StepItemBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
		}

		public void setStep(Step step) {
			binding.setStep(step);
		}
	}
}
