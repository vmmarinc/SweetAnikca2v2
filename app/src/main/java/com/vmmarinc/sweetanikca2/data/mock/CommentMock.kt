package com.vmmarinc.sweetanikca2.data.mock

import com.vmmarinc.sweetanikca2.data.models.Comment

class CommentMock {
    fun loadComments(): List<Comment>{
        return listOf(
            Comment(1, "", "dfghfjdngffdghtrbvc", "descripcion 1"),
            Comment(2, "", "dfghfjdngffdghtrbvc","descr2"),
            Comment(3, "", "dfghfjdngffdghtrbvc", "descr3")
        )
    }
}