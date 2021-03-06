package br.com.glima.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.view.activity.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

	static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
								int appWidgetId) {

		CharSequence widgetText = context.getString(R.string.appwidget_description);
		// Construct the RemoteViews object
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
		views.setTextViewText(R.id.appwidget_image, widgetText);

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		views.setOnClickPendingIntent(R.id.appwidget_root, pendingIntent);

		// Instruct the br.com.glima.bakingapp.widget manager to update the br.com.glima.bakingapp.widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		for (int appWidgetId : appWidgetIds) {
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}
	@Override
	public void onEnabled(Context context) {
		// Enter relevant functionality for when the first br.com.glima.bakingapp.widget is created
	}
	@Override
	public void onDisabled(Context context) {
		// Enter relevant functionality for when the last br.com.glima.bakingapp.widget is disabled
	}


}

