package co.danielbastidas.findstackusers.util.savestate

import android.app.Fragment
import android.os.Bundle


class SaveStateFragment : Fragment() {

    private val state = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            state.putAll(savedInstanceState)
        }
    }

    fun getState(): Bundle? {
        return if (state.isEmpty) {
            null
        } else {
            state
        }
    }

    fun updateState(updateStateAction: BundleAction) {
        updateStateAction.call(state)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putAll(state)
    }
}