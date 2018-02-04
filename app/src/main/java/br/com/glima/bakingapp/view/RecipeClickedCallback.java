package br.com.glima.bakingapp.view;

import br.com.glima.bakingapp.business.Recipe;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public interface RecipeClickedCallback {

	void onRecipeClicked(Recipe recipe);
}
