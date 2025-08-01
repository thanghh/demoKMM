package org.oto.theory

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform