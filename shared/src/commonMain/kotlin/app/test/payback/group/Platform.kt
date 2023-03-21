package app.test.payback.group

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform