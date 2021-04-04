package net.jspiner.mavericks_poc

import com.airbnb.mvrx.MavericksViewModel
import kotlin.math.min

class MainViewModel(initialState: MainState) : MavericksViewModel<MainState>(initialState) {

    private val countMax = 5

    fun addCount() {
        // it will blocking after state.count is `5`
        setState {
            copy(count = min(count + 1, countMax))
        }
    }
}
