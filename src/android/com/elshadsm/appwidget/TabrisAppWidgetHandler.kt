package com.elshadsm.appwidget

import com.eclipsesource.tabris.android.ActivityScope
import com.eclipsesource.tabris.android.ObjectHandler
import com.eclipsesource.tabris.android.Property
import com.eclipsesource.v8.V8Object

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
open class TabrisAppWidgetHandler(private val scope: ActivityScope) : ObjectHandler<TabrisAppWidget> {

  override val type = "com.elshadsm.appwidget.TabrisAppWidget"

  override val properties: List<Property<*, *>> by lazy {
    listOf(CounterProperty)
  }

  override fun create(id: String, properties: V8Object) = TabrisAppWidget(scope)

}
