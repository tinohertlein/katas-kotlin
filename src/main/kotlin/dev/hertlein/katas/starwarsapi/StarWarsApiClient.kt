package dev.hertlein.katas.starwarsapi

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun main() {
    println("Fetched characters: ${StarWarsApiClient().fetchCharacters()}")
}

class StarWarsApiClient(
    private val client: HttpClient = HttpClient.newBuilder().build(),
    private val jsonParser: Json = Json { ignoreUnknownKeys = true }
) {

    companion object {
        private const val START_URL = "https://swapi.dev/api/people"
    }

    @Serializable
    private data class Response(val next: String?, val results: List<Result>)

    @Serializable
    private data class Result(val name: String)

    data class Character(val name: String)

    fun fetchCharacters(url: String? = START_URL, characters: List<Character> = emptyList()): List<Character> {
        if (url == null) {
            return characters
        }

        println("Fetching characters from url $url...")

        val request = HttpRequest.newBuilder().uri(URI.create(url)).build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val json = jsonParser.decodeFromString<Response>(response.body())

        return fetchCharacters(json.next, characters + json.results.map { Character(it.name) })
    }
}
