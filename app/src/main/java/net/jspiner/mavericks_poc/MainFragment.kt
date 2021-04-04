package net.jspiner.mavericks_poc

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState

class MainFragment : Fragment(R.layout.fragment_main), MavericksView {

    private val viewModel: MainViewModel by fragmentViewModel()
    private lateinit var count: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        count = view.findViewById(R.id.textView)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            println("onClicked")
            viewModel.addCount()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun invalidate() {
        // it will blocking after state.count is `5`
        println("invalidated")

        withState(viewModel) { state ->
            println("state changed : $state")
            count.text = "count : ${state.count}"
        }
    }
}
