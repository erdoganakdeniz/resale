package com.freework.user.dto

import javax.persistence.Entity


data class CreateUserRequest(
    val mail:String,
    val firstName:String,
    val lastName:String,
    val middleName:String


)