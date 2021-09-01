package com.w4ereT1ckRtB1tch.moviefan.utils

class Event {

    var isHandled: Boolean = false
        get() {
            field = !field
            return field
        }
}