package br.com.glima.bakingapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Recipe;
import br.com.glima.bakingapp.network.RecipesClient;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class MainActivity extends AppCompatActivity implements Observer<List<Recipe>>, RecipeClickedCallback {

	private RecipesClient client;
	private RecipesFragment recipesFragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		client = new RecipesClient();

		recipesFragment = new RecipesFragment();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, recipesFragment)
				.commit();

		client.fetchRecipes().subscribe(this);

	}
	@Override
	public void onSubscribe(Disposable d) {

	}
	@Override
	public void onNext(List<Recipe> recipes) {
		recipesFragment.setRecipes(recipes);
	}
	@Override
	public void onError(Throwable e) {

	}
	@Override
	public void onComplete() {

	}

	@Override
	public void onRecipeClicked(Recipe recipe) {
		Toast.makeText(this, recipe.getName(), Toast.LENGTH_SHORT).show();

	}
}
