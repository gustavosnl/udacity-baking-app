package br.com.glima.bakingapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static br.com.glima.bakingapp.database.IngredientsContract.COLUMN_INGREDIENT_NAME;
import static br.com.glima.bakingapp.database.IngredientsContract.COLUMN_RECIPE_ID;
import static br.com.glima.bakingapp.database.IngredientsContract.TABLE_NAME;

/**
 * Created by gustavo.lima on 15/02/18.
 */

public class IngredientsDbHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "ingredients.db";
	private static final int DATABASE_VERSION = 1;

	public IngredientsDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		final String SQL_CREATE_FAVORITE_MOVIES_TABLE = " CREATE TABLE " +
				TABLE_NAME + " (" +
				_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				COLUMN_RECIPE_ID + " TEXT NOT NULL, " +
				COLUMN_INGREDIENT_NAME + " TEXT NOT NULL );";

		sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_MOVIES_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(sqLiteDatabase);
	}
}
