package dev.hertlein.katas.starwarsapi

import com.google.common.io.Resources
import dev.hertlein.katas.starwarsapi.StarWarsApiClient.Character
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets

internal class StarWarsApiClientTest {

    private val httpClient = mockk<HttpClient>()
    private val starWarsApiClient = StarWarsApiClient(httpClient)

    @BeforeEach
    fun beforeEach() {

        mapOf(
            "https://swapi.dev/api/people" to "people_page_1.json",
            "https://swapi.dev/api/people/?page=2" to "people_page_2.json",
        )
            .forEach { (requestUrl, fileContainingResponse) ->
                val request = HttpRequest.newBuilder().uri(URI.create(requestUrl)).build()
                val response = mockk<HttpResponse<String>>()

                every { response.body() } returns Resources.toString(
                    Resources.getResource("starwarsapi/${fileContainingResponse}"), StandardCharsets.UTF_8
                )
                every { httpClient.send(request, any<HttpResponse.BodyHandler<String>>()) } returns response
            }
    }

    @Test
    fun `should fetch characters`() {
        val characters = starWarsApiClient.fetchCharacters()

        assertThat(characters).isEqualTo(
            listOf(
                Character("Luke Skywalker"),
                Character("R2-D2")
            )
        )
    }
}
