package br.com.glima.bakingapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Recipe;
import br.com.glima.bakingapp.business.Step;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class RecipeDetailActivity extends AppCompatActivity implements StepClickedCallback {

	private RecipeDetailFragment recipeDetailFragment;
	private StepDetailFragment stepDetailFragment;

	public static final String RECIPE_INTENT_EXTRA = "recipe_intent_extra";

	public static Intent newIntent(Context originContext, Recipe recipe) {
		Intent intent = new Intent(originContext, RecipeDetailActivity.class);
		intent.putExtra(RECIPE_INTENT_EXTRA, recipe);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_recipe_detail);

		recipeDetailFragment = new RecipeDetailFragment();
		stepDetailFragment = new StepDetailFragment();

		recipeDetailFragment.setRecipe((Recipe) getIntent().getParcelableExtra(RECIPE_INTENT_EXTRA));


		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, recipeDetailFragment)
				.commit();

	}

	@Override
	public void onStepClicked(Step step) {
		stepDetailFragment.setStep(step);

		getSupportFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.replace(R.id.fragment_container, recipeDetailFragment)
				.commit();
	}
}
