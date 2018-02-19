package br.com.glima.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import br.com.glima.bakingapp.database.RecipeControler;

/**
 * Created by gustavo.lima on 19/02/18.
 */

public class ListWidgetService extends RemoteViewsService {


	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return null;
	}

	class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
		RecipeControler recipeControler;

		Context mContext;
		Cursor mCursor;

		public ListRemoteViewsFactory(Context context) {
			this.mContext = context;
			recipeControler = new RecipeControler(mContext);
		}

		@Override
		public void onCreate() {

		}

		@Override
		public void onDataSetChanged() {
			recipeControler.getIngredientsByRecipeId()

		}
		@Override
		public void onDestroy() {

		}
		@Override
		public int getCount() {
			return 0;
		}
		@Override
		public RemoteViews getViewAt(int i) {
			return null;
		}
		@Override
		public RemoteViews getLoadingView() {
			return null;
		}
		@Override
		public int getViewTypeCount() {
			return 1;
		}
		@Override
		public long getItemId(int i) {
			return i;
		}
		@Override
		public boolean hasStableIds() {
			return true;
		}
	}
}

