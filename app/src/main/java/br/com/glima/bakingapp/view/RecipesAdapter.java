package br.com.glima.bakingapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Recipe;
import br.com.glima.bakingapp.databinding.RecipeItemBinding;

import static android.databinding.DataBindingUtil.inflate;
import static android.view.LayoutInflater.from;

/**
 * Created by gustavo.lima on 27/01/18.
 */

class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

	private List<Recipe> recipes = new ArrayList<>();
	private RecipeClickedCallback callback;

	public RecipesAdapter(List<Recipe> recipes, RecipeClickedCallback callback) {
		this.recipes = recipes;
		this.callback = callback;
	}

	@Override
	public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RecipeItemBinding binding =
				inflate(from(parent.getContext()), R.layout.recipe_item, parent, false);
		return new RecipeViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(RecipeViewHolder holder, int position) {
		holder.attachRecipe(recipes.get(position));
	}

	@Override
	public int getItemCount() {
		return recipes.size();
	}

	class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		RecipeItemBinding binding;

		RecipeViewHolder(RecipeItemBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
			itemView.setOnClickListener(this);
		}

		void attachRecipe(Recipe recipe) {
			binding.setRecipe(recipe);
		}

		@Override
		public void onClick(View view) {
			callback.onRecipeClicked(binding.getRecipe());
		}
	}


}
