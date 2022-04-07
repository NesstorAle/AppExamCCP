package com.example.appexamccp.common.util

import com.example.appexamccp.common.constants.Constants

class RequestState(val state: String) {

    companion object {
        val REQ_IN_PROGRESS = Constants.IN_PROGRESS
        val REQ_BAD = Constants.REQ_BAD
        val REQ_OK = Constants.REQ_OK
    }
}