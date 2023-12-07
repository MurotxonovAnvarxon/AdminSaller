package com.example.adminsaller.data.remote.request


data class SellerRequest(
    val userName: String,
    val password: String,
) {
//    fun fromRequestToEntity(userId: String=""): UserEntity =
//        UserEntity(userId, this.userName, this.password)
}