package org.oto.theory.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.oto.theory.model.Manga

class APIClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }

    suspend fun fetchMangaList(): List<Manga> {
        val response: HttpResponse = client.get("${ConstantsURL.BASE_URL}/manga") {
            parameter("availableTranslatedLanguage[]", "vi")
            parameter("page", "1")
            parameter("limit", "10")
        }
        return response.body()
    }

    suspend fun fetchMangaDetail(id: String): Manga {
        return client.get("${ConstantsURL.BASE_URL}/manga/$id").body()
    }
}

