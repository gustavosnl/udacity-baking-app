package br.com.glima.bakingapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Recipe;
import br.com.glima.bakingapp.business.Step;
import br.com.glima.bakingapp.databinding.ActivityRecipeDetailBinding;
import br.com.glima.bakingapp.view.StepClickedCallback;
import br.com.glima.bakingapp.view.fragment.RecipeDetailFragment;
import br.com.glima.bakingapp.view.fragment.StepDetailFragment;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class RecipeDetailActivity extends AppCompatActivity implements StepClickedCallback {

	private static final String TAG = "fragment_recipe_detail";
	private RecipeDetailFragment recipeDetailFragment;
	private ActivityRecipeDetailBinding binding;
	private int mCurrentStepIndex;

	public static final String RECIPE_INTENT_EXTRA = "recipe_intent_extra";

	public static Intent newIntent(Context originContext, Recipe recipe) {
		Intent intent = new Intent(originContext, RecipeDetailActivity.class);
		intent.putExtra(RECIPE_INTENT_EXTRA, recipe);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);

		recipeDetailFragment = new RecipeDetailFragment();
		recipeDetailFragment.setRecipe((Recipe) getIntent().getParcelableExtra(RECIPE_INTENT_EXTRA));

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, recipeDetailFragment, TAG)
				.addToBackStack(TAG)
				.commit();
	}

	private void displayDetailFragment() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, recipeDetailFragment, TAG)
				.addToBackStack(TAG)
				.commit();
	}

	@Override
	public void onStepClicked(Step step) {
		mCurrentStepIndex = Integer.valueOf(step.getId());
		fragmentTransition(step);
	}

	private void fragmentTransition(Step step) {
		StepDetailFragment stepDetailFragment = new StepDetailFragment();
		stepDetailFragment.setStep(step);

		getSupportFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
						android.R.anim.fade_out, android.R.anim.fade_in)
				.addToBackStack(step.getId())
				.replace(R.id.fragment_container, stepDetailFragment)
				.commit();

		showNavigationControls();
	}

	private Recipe getRecipe() {
		if (getIntent().hasExtra(RECIPE_INTENT_EXTRA)) {
			return getIntent().getParcelableExtra(RECIPE_INTENT_EXTRA);
		}
		return null;
	}

	public void previousStepClicked(View view) {
		if (mCurrentStepIndex == 0) {
			hideNavigationControls();
			displayDetailFragment();
		} else {
			Step previous = getRecipe().getSteps().get(--mCurrentStepIndex);
			fragmentTransition(previous);
		}
	}

	public void nextStepClicked(View view) {
		Step previous = getRecipe().getSteps().get(++mCurrentStepIndex);
		fragmentTransition(previous);
	}

	private void showNavigationControls() {
		binding.navContainer.setVisibility(View.VISIBLE);
		binding.nextStep.setVisibility(View.VISIBLE);
		binding.previousStep.setVisibility(View.VISIBLE);
		binding.stepLabel.setVisibility(View.VISIBLE);
	}

	private void hideNavigationControls() {
		binding.navContainer.setVisibility(View.GONE);
		binding.nextStep.setVisibility(View.GONE);
		binding.previousStep.setVisibility(View.GONE);
		binding.stepLabel.setVisibility(View.GONE);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		previousStepClicked(null);
	}
}
