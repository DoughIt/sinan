package com.doughit.sinan.model

import java.sql.Timestamp

data class User(
    val username: String,
    val nickname: String,
    val email: String,
    val phoneNumber: String,
    val avatar: String,
    val description: String,
    val registerTime: Timestamp,
    val lastLoginTime: Timestamp,
    val state: String,
) {
    data class Tag(
        val userId: Long,
        val tagName: String,
        val generatedTime: Timestamp
    )

    data class UserWebsite(
        val userId: Long,
        val websiteAddress: String
    )

    data class UserWebsiteTag(
        val userWebsiteId: Long,
        val tagName: String,
        val bindingTime: Timestamp
    )
}
