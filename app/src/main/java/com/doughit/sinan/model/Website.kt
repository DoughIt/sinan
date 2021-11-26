package com.doughit.sinan.model

import java.sql.Timestamp

data class Website(
    val address: String,
    val title: String,
    val icon: String,
    val state: String,
    val updated: Timestamp,
    val tagList: List<Tag>
) {

    data class Tag(
        var websiteAddress: String,
        var tagName: String
    )
}
