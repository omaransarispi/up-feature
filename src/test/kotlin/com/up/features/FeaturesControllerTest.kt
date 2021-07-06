package com.up.features

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@WebMvcTest
class FeaturesControllerTest(@Autowired val mvc: MockMvc) {
    @Test
    fun `returns a collection of features when list is requested`() {
        mvc.get("/features") { accept = MediaType.APPLICATION_JSON }.andExpect {
            val expectedResponse = """
                [{
                    "id":"id",
                    "timestamp":"2021-07-05T22:00:00.000+00:00",
                    "beginViewingDate":"2021-07-06T00:00:00",
                    "endViewingDate":"2021-07-06T01:00:00",
                    "missionName":"Sentinel-1B"
                }]
            """.trimIndent()
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(expectedResponse) }
            status { isOk() }
        }
    }
}
