package com.up.features.integration

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.env.Environment
import org.springframework.http.*
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeaturesApplicationTest(
    @Autowired private val restTemplate: TestRestTemplate,
    @Autowired private val environment: Environment
) {
    var port: String = environment.getProperty("local.server.port")!!
    val url = "http://localhost:${port}"

    @Test
    fun `get all features`() {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val entity: HttpEntity<*> = HttpEntity<Any>(headers)
        val result: ResponseEntity<String> =
            restTemplate.exchange<String>(url + "/features", HttpMethod.GET, entity, String::class.java)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
    }

    @Test
    fun `get a features by id`() {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val entity: HttpEntity<*> = HttpEntity<Any>(headers)
        val result: ResponseEntity<String> = restTemplate.exchange<String>(
            url + "/features/39c2f29e-c0f8-4a39-a98b-deed547d6aea",
            HttpMethod.GET,
            entity,
            String::class.java
        )

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
    }

    @Test
    fun `get a features quicklook`() {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.IMAGE_PNG)
        val entity: HttpEntity<*> = HttpEntity<Any>(headers)
        val result: ResponseEntity<String> = restTemplate.exchange<String>(
            url + "/features/39c2f29e-c0f8-4a39-a98b-deed547d6aea/quicklook",
            HttpMethod.GET,
            entity,
            String::class.java
        )

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
    }
}