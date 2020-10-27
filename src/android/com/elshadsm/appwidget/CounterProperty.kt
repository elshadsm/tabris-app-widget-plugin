package com.elshadsm.appwidget

import com.eclipsesource.tabris.android.IntProperty

object CounterProperty : IntProperty<TabrisAppWidget>("counter", {
  it?.let { counter = it }
})
