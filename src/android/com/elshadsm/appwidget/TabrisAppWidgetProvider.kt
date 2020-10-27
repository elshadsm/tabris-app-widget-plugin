package com.elshadsm.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

const val ACTION_INCREASE = "ACTION_INCREASE"
const val ACTION_DECREASE = "ACTION_DECREASE"
const val ACTION_OPEN_APP = "ACTION_OPEN_APP"

class TabrisAppWidgetProvider : AppWidgetProvider() {

  override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
    for (appWidgetId in appWidgetIds) {
      updateAppWidget(context, appWidgetManager, appWidgetId)
    }
  }

  override fun onEnabled(context: Context) {
    // Enter relevant functionality for when the first widget is created
  }

  override fun onDisabled(context: Context) {
    // Enter relevant functionality for when the last widget is disabled
  }

  override fun onReceive(context: Context, intent: Intent?) {
    super.onReceive(context, intent)
    when (intent?.action) {
      ACTION_INCREASE -> AppWidgetUtil.incrementCounter(context)
      ACTION_DECREASE -> AppWidgetUtil.decrementCounter(context)
      ACTION_OPEN_APP -> AppWidgetUtil.openApp(context)
    }
  }

}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
  val resources = context.resources
  val packageName = context.packageName
  val widgetLayout = resources.getIdentifier("app_widget", "layout", packageName)
  val remoteViews = RemoteViews(context.packageName, widgetLayout)
  updateActions(context, remoteViews)
  appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}

internal fun updateActions(context: Context, remoteViews: RemoteViews) {
  val resources = context.resources
  val packageName = context.packageName
  val incrementId = resources.getIdentifier("increment", "id", packageName)
  val decrementId = resources.getIdentifier("decrement", "id", packageName)
  val openAppId = resources.getIdentifier("open_app", "id", packageName)
  updateAction(context, remoteViews, ACTION_INCREASE, incrementId)
  updateAction(context, remoteViews, ACTION_DECREASE, decrementId)
  updateAction(context, remoteViews, ACTION_OPEN_APP, openAppId)
}

internal fun updateAction(context: Context,
                          remoteViews: RemoteViews,
                          action: String,
                          viewId: Int) {
  val intent = Intent(context, TabrisAppWidgetProvider::class.java).apply {
    this.action = action
  }
  val pendingIntent = PendingIntent.getBroadcast(context,
      0,
      intent,
      PendingIntent.FLAG_UPDATE_CURRENT)
  remoteViews.setOnClickPendingIntent(viewId, pendingIntent)
}
