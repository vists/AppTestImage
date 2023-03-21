package app.test.payback.group.di.provider

import app.test.payback.group.BuildKonfig
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

internal class KtorProvider {

    @OptIn(ExperimentalSerializationApi::class)
    val httpClient = HttpClient {
        defaultRequest {
            url("${BuildKonfig.BASE_URL}&key=${BuildKonfig.CLIENT_KEY}&")
        }
        install(ContentNegotiation) {
            json(Json {
                coerceInputValues = true
                ignoreUnknownKeys = true
                explicitNulls = false
                encodeDefaults = true
            })
        }
    }
}