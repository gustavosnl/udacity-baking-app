package br.com.glima.bakingapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Recipe;
import br.com.glima.bakingapp.databinding.FragmentRecipeDetailBinding;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by gustavo.lima on 28/01/18.
 */

public class RecipeDetailFragment extends Fragment {

	private final String ARGUMENT_RECIPE = "recipe_argument";
	private RecyclerView ingredients;
	private RecyclerView steps;
	private FragmentRecipeDetailBinding binding;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		binding = inflate(inflater, R.layout.fragment_recipe_detail, container, false);

		ingredients = binding.ingredientsList;
		steps = binding.stepsList;

		ingredients.setLayoutManager(new LinearLayoutManager(getContext()));
		steps.setLayoutManager(new LinearLayoutManager(getContext()));

		return binding.getRoot();
	}

	public void setRecipe(Recipe recipe) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(ARGUMENT_RECIPE, recipe);
		setArguments(bundle);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Recipe recipe = getArguments().getParcelable(ARGUMENT_RECIPE);
		ingredients.setAdapter(new IngredientsAdapter(recipe.getIngredients()));
		steps.setAdapter(new StepsAdapter(recipe.getSteps(), (StepClickedCallback) getContext()));
		steps.addItemDecoration(new DividerItemDecoration(getContext()));
	}
}
