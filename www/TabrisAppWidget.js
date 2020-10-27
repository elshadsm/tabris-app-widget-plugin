
class TabrisAppWidget extends tabris.Widget {

  get _nativeType() {
    return 'com.elshadsm.appwidget.TabrisAppWidget';
  }

}

tabris.NativeObject.defineProperties(TabrisAppWidget.prototype, {
  counter: { type: 'number', nocache: true }
});

module.exports = TabrisAppWidget;
