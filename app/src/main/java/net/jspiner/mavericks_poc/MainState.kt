package net.jspiner.mavericks_poc

import com.airbnb.mvrx.MavericksState

data class MainState(
    val count: Int = 0
) : MavericksState
