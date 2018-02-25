package br.com.glima.bakingapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Recipe;
import br.com.glima.bakingapp.business.Step;
import br.com.glima.bakingapp.database.RecipeController;
import br.com.glima.bakingapp.databinding.ActivityRecipeDetailBinding;
import br.com.glima.bakingapp.view.StepClickedCallback;
import br.com.glima.bakingapp.view.fragment.RecipeDetailFragment;
import br.com.glima.bakingapp.view.fragment.StepDetailFragment;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class RecipeDetailActivity extends AppCompatActivity implements StepClickedCallback {

	private static final String TAG = "fragment_recipe_detail";
	private RecipeDetailFragment recipeDetailFragment;
	private RecipeController recipeController;
	private ActivityRecipeDetailBinding binding;
	private int mCurrentStepIndex;

	public static final String RECIPE_INTENT_EXTRA = "recipe_intent_extra";
	private boolean mTwoPane;

	public static Intent newIntent(Context originContext, Recipe recipe) {
		Intent intent = new Intent(originContext, RecipeDetailActivity.class);
		intent.putExtra(RECIPE_INTENT_EXTRA, recipe);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		recipeController = new RecipeController(this);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);

		if (findViewById(R.id.recipeDetailFragment) != null) {
			mTwoPane = true;
			recipeDetailFragment = new RecipeDetailFragment();
			recipeDetailFragment.setRecipe((Recipe) getIntent().getParcelableExtra(RECIPE_INTENT_EXTRA));

			getSupportFragmentManager().beginTransaction()
					.add(R.id.recipeDetailFragment, recipeDetailFragment, TAG)
					.addToBackStack(TAG)
					.commit();

			changeStep(getRecipe().getSteps().get(0));

		} else {
			binding.nextStep.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					nextStepClicked();
				}
			});

			binding.previousStep.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					previousStepClicked();
				}
			});

			recipeDetailFragment = new RecipeDetailFragment();
			recipeDetailFragment.setRecipe((Recipe) getIntent().getParcelableExtra(RECIPE_INTENT_EXTRA));

			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_container, recipeDetailFragment, TAG)
					.addToBackStack(TAG)
					.commit();
		}
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
		if (!mTwoPane) {
			showNavigationControls();
		}
		changeStep(step);
	}

	private void changeStep(Step step) {
		StepDetailFragment newStepFragment = new StepDetailFragment();
		newStepFragment.setStep(step);

		getSupportFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
						android.R.anim.fade_out, android.R.anim.fade_in)
				.addToBackStack(step.getId())
				.replace(R.id.fragment_container, newStepFragment)
				.commit();
	}

	private Recipe getRecipe() {
		if (getIntent().hasExtra(RECIPE_INTENT_EXTRA)) {
			return getIntent().getParcelableExtra(RECIPE_INTENT_EXTRA);
		}
		return null;
	}

	public void previousStepClicked() {
		if (mCurrentStepIndex == 0) {
			hideNavigationControls();
			displayDetailFragment();
		} else {
			Step previous = getRecipe().getSteps().get(--mCurrentStepIndex);
			changeStep(previous);
		}
	}

	public void nextStepClicked() {
		if (mCurrentStepIndex != getRecipe().getSteps().size() - 1) {
			binding.nextStep.setVisibility(VISIBLE);
			Step next = getRecipe().getSteps().get(++mCurrentStepIndex);
			changeStep(next);
		} else {
			binding.nextStep.setVisibility(INVISIBLE);
		}
	}

	private void showNavigationControls() {
		if (!mTwoPane) {
			binding.navContainer.setVisibility(VISIBLE);
			binding.nextStep.setVisibility(VISIBLE);
			binding.previousStep.setVisibility(VISIBLE);
			binding.stepLabel.setVisibility(VISIBLE);
		}
	}

	private void hideNavigationControls() {
		binding.navContainer.setVisibility(GONE);
		binding.nextStep.setVisibility(GONE);
		binding.previousStep.setVisibility(GONE);
		binding.stepLabel.setVisibility(GONE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.recipe_detail_menu, menu);
		setUpFavoriteMenuItem(menu.findItem(R.id.favorite));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.favorite:
				UpdateFavoriteMenuItem(item);
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setUpFavoriteMenuItem(MenuItem favoriteItem) {
		if (isFavorite()) {
			favoriteItem.setIcon(R.drawable.ic_favorite_filled);
		} else {
			favoriteItem.setIcon(R.drawable.ic_favorite_stroke);
		}
	}
	private boolean isFavorite() {
		return recipeController.isFavorite(getRecipe().getId());
	}

	private void UpdateFavoriteMenuItem(MenuItem favoriteItem) {
		if (isFavorite()) {
			favoriteItem.setIcon(R.drawable.ic_favorite_stroke);
			recipeController.removeRecipe(getRecipe().getId());
		} else {
			favoriteItem.setIcon(R.drawable.ic_favorite_filled);
			recipeController.addRecipe(getRecipe());
		}
	}
}

