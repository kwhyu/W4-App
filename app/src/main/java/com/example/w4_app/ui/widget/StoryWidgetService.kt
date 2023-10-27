package com.example.w4_app.ui.widget

import android.content.Intent
import android.widget.RemoteViewsService

class StoryWidgetService : RemoteViewsService(){
     override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
         StackRemoteStackFactory(this.applicationContext)
}