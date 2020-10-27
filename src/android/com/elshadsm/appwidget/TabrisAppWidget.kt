package com.elshadsm.appwidget

import com.eclipsesource.tabris.android.ActivityScope

class TabrisAppWidget(private val scope: ActivityScope) {

  var counter: Int
    set(value) = AppWidgetUtil.updateCounter(scope.context, value)
    get() = AppWidgetUtil.getCounter(scope.context)

}
