package com.example.animelist.domain

import javax.inject.Inject

class CheckUserAuthUseCase @Inject constructor() {
    operator fun invoke(jwt: String): Boolean {
        return true
    }
}