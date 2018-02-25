package br.com.glima.bakingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import br.com.glima.bakingapp.business.Ingredient;
import br.com.glima.bakingapp.business.Recipe;

import static br.com.glima.bakingapp.database.IngredientsContract.COLUMN_INGREDIENT_NAME;
import static br.com.glima.bakingapp.database.IngredientsContract.COLUMN_RECIPE_ID;
import static br.com.glima.bakingapp.provider.IngredientsContentProviderContract.CONTENT_URI;

/**
 * Created by gustavo.lima on 15/02/18.
 */

public class RecipeController {

	private Context context;

	public RecipeController(Context context) {
		this.context = context;
	}

	public void addRecipe(Recipe recipe) {
		ContentValues values = new ContentValues();

		for (Ingredient ingredient : recipe.getIngredients()) {
			values.put(COLUMN_RECIPE_ID, recipe.getId());
			values.put(COLUMN_INGREDIENT_NAME, ingredient.getIngredient());
		}

		context.getContentResolver().insert(CONTENT_URI, values);
	}

	public List<String> getFavoriteRecipeIngredients(String recipeId) {

		List<String> ingredients = new ArrayList<>();
		Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();

			while (!cursor.isAfterLast()) {
				ingredients.add(cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)));
				cursor.moveToNext();
			}
			cursor.close();
		}
		return ingredients;
	}

	public void removeRecipe(String recipeId) {
		Uri DELETE_URI = CONTENT_URI.buildUpon().appendPath(recipeId).build();

		context.getContentResolver().delete(DELETE_URI, null, null);
	}
	public List<String> getFavoriteRecipeIngredients() {
		List<String> ingredients = new ArrayList<>();
		Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();

			while (!cursor.isAfterLast()) {
				ingredients.add(cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)));
				cursor.moveToNext();
			}
			cursor.close();
		}
		return ingredients;
	}

	public boolean isFavorite(String recipeId) {
		Uri IS_FAVORITE_URI = CONTENT_URI.buildUpon().appendPath(recipeId).build();

		Cursor cursor = context.getContentResolver().query(IS_FAVORITE_URI, null, null, null, null);
		return cursor.moveToNext();
	}
}
