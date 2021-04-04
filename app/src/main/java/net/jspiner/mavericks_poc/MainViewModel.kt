package net.jspiner.mavericks_poc

import com.airbnb.mvrx.MavericksViewModel

class MainViewModel(initialState: MainState) : MavericksViewModel<MainState>(initialState) {

    fun addCount() {
        setState { copy(count = count + 1) }
    }
}
