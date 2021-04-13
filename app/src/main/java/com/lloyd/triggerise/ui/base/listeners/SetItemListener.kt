package com.lloyd.triggerise.ui.base.listeners

import com.lloyd.triggerise.data.models.sets.Set

interface SetItemListener {
    fun onItemSelected(set : Set)
}