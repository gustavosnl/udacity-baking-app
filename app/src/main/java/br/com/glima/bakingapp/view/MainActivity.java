package br.com.glima.bakingapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Recipe;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class MainActivity extends AppCompatActivity implements RecipeClickedCallback {

	private RecipesFragment recipesFragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		recipesFragment = new RecipesFragment();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, recipesFragment)
				.commit();

	}

	@Override
	public void onRecipeClicked(Recipe recipe) {
		startActivity(RecipeDetailActivity.newIntent(this, recipe));
	}
}
