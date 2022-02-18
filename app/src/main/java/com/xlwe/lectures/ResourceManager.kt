package com.xlwe.lectures

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes stringResId: Int): String
}