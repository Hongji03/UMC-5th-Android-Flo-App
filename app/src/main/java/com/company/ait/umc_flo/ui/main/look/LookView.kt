package com.company.ait.umc_flo.ui.main.look

import com.company.ait.umc_flo.ui.song.FloChartResult

interface LookView {
    fun onGetSongLoading()
    fun onGetSongSuccess(code: Int, result: FloChartResult)
    fun onGetSongFailure(code: Int, message: String)
}