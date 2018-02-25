package br.com.glima.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.database.RecipeController;
import br.com.glima.bakingapp.view.activity.RecipeDetailActivity;

import static br.com.glima.bakingapp.provider.IngredientsContentProviderContract.CONTENT_URI;

/**
 * Created by gustavo.lima on 19/02/18.
 */

public class ListWidgetService extends RemoteViewsService {

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new ListRemoteViewsFactory(this.getApplicationContext());
	}


	class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
		RecipeController recipeController;

		Context mContext;
		Cursor mCursor;

		ListRemoteViewsFactory(Context context) {
			this.mContext = context;
		}

		@Override
		public void onCreate() {

		}

		@Override
		public void onDataSetChanged() {

			if (mCursor != null) mCursor.close();

			mCursor = mContext.getContentResolver().query(
					CONTENT_URI,
					null,
					null,
					null,
					null);
		}

		@Override
		public void onDestroy() {
			mCursor.close();
		}

		@Override
		public int getCount() {
			if (mCursor == null) return 0;
			return mCursor.getCount();
		}

		@Override
		public RemoteViews getViewAt(int i) {

			if (mCursor == null || mCursor.getCount() == 0) return null;
			mCursor.moveToPosition(i);






			RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.baking_app_widget);

			Bundle extras = new Bundle();
//			extras.putParcelable(RecipeDetailActivity.RECIPE_INTENT_EXTRA, recipeController.getFavoriteRecipe());
			Intent fillInIntent = new Intent();
			fillInIntent.putExtras(extras);
			views.setOnClickFillInIntent(R.id.appwidget_root, fillInIntent);

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

