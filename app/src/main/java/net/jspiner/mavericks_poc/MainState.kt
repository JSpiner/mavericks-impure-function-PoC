package net.jspiner.mavericks_poc

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class MainState(
    val count: Int = 0,
    val countRequest: Async<Int> = Uninitialized,
    val errorMessage: String? = null
) : MavericksState
