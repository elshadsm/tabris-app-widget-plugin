package com.elshadsm.appwidget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews

class AppWidgetUtil {

  companion object {

    private const val PREFERENCE_NAME = "app_widget_name"
    private const val PREFERENCE_COUNTER_KEY = "counter"
    private const val APP_SCHEME = "com.elshadsm.appwidget"

    fun openApp(context: Context) {
      val uri = Uri.parse("$APP_SCHEME://?counter=${getCounter(context)}")
      Intent(Intent.ACTION_VIEW, uri).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(this)
      }
    }

    fun getCounter(context: Context) =
        context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).getInt(PREFERENCE_COUNTER_KEY, 0)

    fun incrementCounter(context: Context) {
      var counter = getCounter(context)
      updateCounter(context, ++counter)
    }

    fun decrementCounter(context: Context) {
      var counter = getCounter(context)
      updateCounter(context, --counter)
    }

    fun updateCounter(context: Context, value: Int) {
      context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
          .edit()
          .putInt(PREFERENCE_COUNTER_KEY, value)
          .apply()
      updateRemoteViews(context)
    }

    private fun updateRemoteViews(context: Context) {
      val resources = context.resources
      val packageName = context.packageName
      val widgetLayout = resources.getIdentifier("app_widget", "layout", packageName)
      val counterId = resources.getIdentifier("counter", "id", packageName)
      val remoteViews = RemoteViews(context.packageName, widgetLayout)
      remoteViews.setTextViewText(counterId, getCounter(context).toString())
      val appWidget = ComponentName(context, TabrisAppWidgetProvider::class.java)
      val appWidgetManager = AppWidgetManager.getInstance(context)
      appWidgetManager.updateAppWidget(appWidget, remoteViews)
    }

  }

}
