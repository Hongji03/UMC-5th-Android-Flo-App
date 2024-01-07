package com.company.ait.umc_flo.ui.signin

import com.company.ait.umc_flo.data.remote.auth.Result

interface LoginView {
    fun onLoginSuccess(code: Int, result: Result)
    fun onLoginFailure()
}