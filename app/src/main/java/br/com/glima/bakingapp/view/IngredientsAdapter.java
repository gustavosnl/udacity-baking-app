package br.com.glima.bakingapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Ingredient;
import br.com.glima.bakingapp.databinding.IngredientItemBinding;

import static android.databinding.DataBindingUtil.inflate;
import static android.view.LayoutInflater.from;

/**
 * Created by gustavo.lima on 28/01/18.
 */

class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

	private List<Ingredient> ingredients = new ArrayList<>();

	public IngredientsAdapter(List<Ingredient> ingredients) {
		this.ingredients.addAll(ingredients);
	}

	@Override
	public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		IngredientItemBinding binding = inflate(from(parent.getContext()), R.layout.ingredient_item, parent, false);
		return new IngredientViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(IngredientViewHolder holder, int position) {
		holder.bindIngredient(ingredients.get(position));
	}

	@Override
	public int getItemCount() {
		return ingredients.size();
	}

	class IngredientViewHolder extends RecyclerView.ViewHolder {

		IngredientItemBinding binding;

		IngredientViewHolder(IngredientItemBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
		}

		void bindIngredient(Ingredient ingredient) {
			binding.setIngredient(ingredient);
		}
	}
}
