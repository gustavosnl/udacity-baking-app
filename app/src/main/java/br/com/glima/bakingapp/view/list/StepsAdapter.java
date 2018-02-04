package br.com.glima.bakingapp.view.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Step;
import br.com.glima.bakingapp.databinding.StepItemBinding;
import br.com.glima.bakingapp.view.StepClickedCallback;

import static android.databinding.DataBindingUtil.bind;
import static android.view.LayoutInflater.from;

/**
 * Created by gustavo.lima on 28/01/18.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

	private List<Step> steps = new ArrayList<>();
	private final StepClickedCallback callback;

	public StepsAdapter(List<Step> steps, StepClickedCallback callback) {
		this.callback = callback;
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


	class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private StepItemBinding binding;

		public StepViewHolder(StepItemBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
			itemView.setOnClickListener(this);
		}

		public void setStep(Step step) {
			binding.setStep(step);
		}
		@Override
		public void onClick(View view) {
			callback.onStepClicked(binding.getStep());
		}
	}
}
