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
            //if the state in empty then return a null value,
            // this matches the way the bundle passed to onCreate
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