package com.climbz.commander;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class BasicCommandWidget extends AppWidgetProvider {

    private static final String UpClick = "up";
    private static final String DownClick = "down";
    private static final String SettingsClick = "settings";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.basic_command_widget);
        views.setTextViewText(R.id.desc, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

            ComponentName thisWidget = new ComponentName(context, BasicCommandWidget.class);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.basic_command_widget);
            thisWidget = new ComponentName(context, BasicCommandWidget.class);
            remoteViews.setOnClickPendingIntent(R.id.up_button, getPendingSelfIntent(context, UpClick));
            remoteViews.setOnClickPendingIntent(R.id.down_button, getPendingSelfIntent(context, DownClick));
            remoteViews.setOnClickPendingIntent(R.id.settings_button, getPendingSelfIntent(context, SettingsClick));
            appWidgetManager.updateAppWidget(thisWidget, remoteViews);
        }


    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (UpClick.equals(intent.getAction())){
            Toast.makeText(context, "up like your computer volume", Toast.LENGTH_SHORT).show();
        } else if (DownClick.equals(intent.getAction())){
            Toast.makeText(context, DownClick, Toast.LENGTH_SHORT).show();
        } else if (SettingsClick.equals(intent.getAction())){
            Toast.makeText(context, SettingsClick, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

