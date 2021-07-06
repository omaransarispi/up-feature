package com.up.features

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.sql.Timestamp
import java.time.LocalDate


@WebMvcTest
class FeaturesControllerTest(@Autowired val mvc: MockMvc) {
    @Test
    fun `returns a collection of features when list is requested`() {
        mvc.get("/features") { accept = MediaType.APPLICATION_JSON }.andExpect {
            val timestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay()).time
            val beginTimestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay().minusHours(1)).time
            val endTimestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay().plusHours(1)).time
            val expectedResponse = """
                [{
                    "id":"id",
                    "missionName":"Sentinel-1B",
                    "timestamp":$timestamp,
                    "beginViewingDate": $beginTimestamp,
                    "endViewingDate": $endTimestamp
                }]
            """.trimIndent()
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(expectedResponse) }
            status { isOk() }
        }
    }
}
