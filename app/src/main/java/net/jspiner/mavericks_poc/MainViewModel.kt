package net.jspiner.mavericks_poc

import android.annotation.SuppressLint
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.Success
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class MainViewModel(initialState: MainState) : MavericksViewModel<MainState>(initialState) {

    @SuppressLint("CheckResult")
    fun addCount() {
        withState { originState ->
            dummyAddCountRequest(originState.count)
                .execute { result ->
                    when (result) {
                        is Success -> copy(count = result(), countRequest = result)
                        is Fail -> {
                            copy(
                                countRequest = result,
                                errorMessage = (result.error as ErrorException)
                                    .errorResponse.readMessage()
                            )
                        }
                        else -> copy(countRequest = result)
                    }
                }
        }
    }

    private fun dummyAddCountRequest(count: Int) = CoroutineScope(Dispatchers.Main).async {
        if (count >= 5) {
            throw ErrorException("NetworkException", ErrorResponse("errorMessage"))
        } else {
            count + 1
        }
    }
}
