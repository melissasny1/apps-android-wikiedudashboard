package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import timber.log.Timber

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * Recent activity presenter to implement [requestRecentActivity] and display data in view
 * @constructor primary constructor with properties
 *
 * @property view view component for course detail
 * @property provider api service for course detail
 * ***/
class RecentActivityPresenterImpl(private val view: RecentActivityContract.View,
                                  private val provider: RecentActivityContract.Provider) : RecentActivityContract.Presenter {
    override fun requestRecentActivity(url: String) {
        view.showProgressBar(true)
        provider.requestRecentActivity(url, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any?) {
                view.showProgressBar(false)
                val response = o as RecentActivityResponse
                Timber.d(response.toString())
                view.setData(response)
            }

            override fun onFailure() {
                view.showProgressBar(false)
                view.showMessage("unable to connect to server.")
            }
        })

    }
}
