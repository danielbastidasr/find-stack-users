package co.danielbastidas.findstackusers.util.savestate

import android.app.Activity
import android.os.Bundle
import io.reactivex.Maybe


class ReactiveSaveState  {
    companion object {
        private const val FRAGMENT_BUNDLE = "REACTIVE_SAVED_STATE"
    }

    fun getSavedState(activity: Activity): Maybe<Bundle> {

        val prevState = getSavedStateDirect(activity)
        return if (prevState != null) {
            Maybe.just(prevState)
        } else {
            Maybe.empty()
        }
    }

    fun updateSaveState(activity: Activity, updateStateAction: BundleAction) {
        getFragment(activity).updateState(updateStateAction)
    }

    private fun getFragment(activity: Activity): SaveStateFragment {
        val fragmentManager = activity.fragmentManager
        val intentFragment: SaveStateFragment
        val fragment = fragmentManager.findFragmentByTag(FRAGMENT_BUNDLE)
        if (fragment == null) {
            intentFragment = SaveStateFragment()
            fragmentManager.beginTransaction().add(intentFragment, FRAGMENT_BUNDLE).commitAllowingStateLoss()
        } else {
            intentFragment = fragment as SaveStateFragment
        }
        return intentFragment
    }

    private fun getSavedStateDirect(activity: Activity): Bundle? {
        return getFragment(activity).getState()
    }
}

interface BundleAction {
    fun call(bundle: Bundle)
}