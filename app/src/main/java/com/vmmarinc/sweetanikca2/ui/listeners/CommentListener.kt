package com.vmmarinc.sweetanikca2.ui.listeners

import com.vmmarinc.sweetanikca2.data.models.Comment

interface CommentListener {
    fun onClick(comment: Comment)
}