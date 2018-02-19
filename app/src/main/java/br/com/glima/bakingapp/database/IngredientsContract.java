package br.com.glima.bakingapp.database;

import android.provider.BaseColumns;

/**
 * Created by gustavo.lima on 15/02/18.
 */

public interface IngredientsContract extends BaseColumns {
	String TABLE_NAME = "ingredients";
	String COLUMN_RECIPE_ID = "recipe_id";
	String COLUMN_INGREDIENT_NAME = "name";
}
