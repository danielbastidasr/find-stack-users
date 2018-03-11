package co.danielbastidas.findstackusers.util.savestate

import android.app.Activity
import android.os.Bundle
import io.reactivex.Maybe


class ReactiveSaveState  {

    private val FRAGMENT_TAG = "ReactiveSaveStateFragment"

    fun getSavedState(activity: Activity): Maybe<Bundle> {

        val prevState = getSavedStateDirect(activity)
        return if (prevState != null) {
            Maybe.just(prevState)
        } else {
            Maybe.empty()
        }
    }


    fun getSavedStateDirect(activity: Activity): Bundle? {
        return getFragment(activity).getState()
    }


    fun updateSaveState(activity: Activity, updateStateAction: BundleAction) {
        getFragment(activity).updateState(updateStateAction)
    }

    private fun getFragment(activity: Activity): SaveStateFragment {
        val fragmentManager = activity.fragmentManager
        val intentFragment: SaveStateFragment
        val fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG)
        if (fragment == null) {
            intentFragment = SaveStateFragment()
            fragmentManager.beginTransaction().add(intentFragment, FRAGMENT_TAG).commitAllowingStateLoss()
        } else {
            intentFragment = fragment as SaveStateFragment
        }
        return intentFragment
    }
}

interface BundleAction {
    fun call(bundle: Bundle)
}