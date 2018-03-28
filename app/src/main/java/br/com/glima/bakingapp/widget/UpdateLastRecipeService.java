package br.com.glima.bakingapp.widget;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;

import br.com.glima.bakingapp.business.Recipe;
import br.com.glima.bakingapp.database.IngredientsContract;

import static br.com.glima.bakingapp.provider.IngredientsContentProviderContract.BASE_CONTENT_URI;
import static br.com.glima.bakingapp.provider.IngredientsContentProviderContract.PATH_RECIPE;

/**
 * Created by gustavo on 25/02/18.
 */

public class UpdateLastSeenRecipeService extends IntentService {

	public static final String ACTION_UPDATE_RECIPE = "br.com.glima.bakingapp.action.update_recipe";
	private static final String RECIPE_INTENT_KEY = "key_intent_recipe";
	/**
	 * Creates an IntentService.  Invoked by your subclass's constructor.
	 *
	 * @param name Used to name the worker thread, important only for debugging.
	 */
	public UpdateLastSeenRecipeService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		if (intent != null) {
			final String action = intent.getAction();
			if (ACTION_UPDATE_RECIPE.equals(action)) {
				updateLastSeenRecipe((Recipe) intent.getParcelableExtra(RECIPE_INTENT_KEY));
			}
		}
	}
	private void updateLastSeenRecipe(Recipe recipe) {
		Uri RECIPE_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPE).build();
		ContentValues contentValues = new ContentValues();
		// Update only plants that are still alive
		getContentResolver().update(
				RECIPE_URI,
				contentValues,
				null,
				null);
	}

	public static void startActionUpdateRecipe(Context context, Recipe recipe) {
		Intent intent = new Intent(context, UpdateLastSeenRecipeService.class);
		intent.setAction(ACTION_UPDATE_RECIPE);
		intent.putExtra(RECIPE_INTENT_KEY, recipe);
		context.startService(intent);
	}
}
