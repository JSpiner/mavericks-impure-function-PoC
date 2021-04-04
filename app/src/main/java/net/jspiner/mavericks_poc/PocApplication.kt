package net.jspiner.mavericks_poc

import android.app.Application
import com.airbnb.mvrx.Mavericks

class PocApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}
