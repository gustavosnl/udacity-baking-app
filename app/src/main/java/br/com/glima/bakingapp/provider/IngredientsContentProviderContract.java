package br.com.glima.bakingapp.provider;

import android.net.Uri;

/**
 * Created by gustavo.lima on 15/02/18.
 */

public interface IngredientsContentProviderContract {
	String AUTHORITY = "br.com.glima.bakingapp";
	Uri BASE_CONTENT_URI = Uri.parse("content://".concat(AUTHORITY));
	String PATH_RECIPE = "recipe";

	Uri CONTENT_URI =
			BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPE).build();
}
