package com.up.features

import com.up.features.responses.FeatureResponse
import com.up.features.valueObjects.FeatureId
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.sql.Timestamp
import java.time.LocalDate


@WebMvcTest
class FeaturesControllerTest(@Autowired val mvc: MockMvc) {
    @MockBean
    lateinit var mockFeaturesService: FeaturesService

    @Test
    fun `returns a collection of features when list is requested`() {
        val id = "id"
        val missionName = "Sentinel-1B"
        val timestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay()).time
        val beginTimestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay().minusHours(1)).time
        val endTimestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay().plusHours(1)).time
        Mockito.`when`(mockFeaturesService.getFeatures())
            .thenReturn(listOf(FeatureResponse(id, missionName, timestamp, beginTimestamp, endTimestamp)))

        mvc.get("/features") { accept = MediaType.APPLICATION_JSON }.andExpect {
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

    @Test
    fun `returns a single feature when requested`() {
        val id = "39c2f29e-c0f8-4a39-a98b-deed547d6aea"
        val missionName = "Sentinel-1B"
        val timestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay()).time
        val beginTimestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay().minusHours(1)).time
        val endTimestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay().plusHours(1)).time
        Mockito.`when`(mockFeaturesService.getFeatureById(FeatureId.getInstance(id)!!))
            .thenReturn(FeatureResponse(id, missionName, timestamp, beginTimestamp, endTimestamp))

        mvc.get("/features/$id") { accept = MediaType.APPLICATION_JSON }.andExpect {
            val expectedResponse = """
                {
                    "id":"$id",
                    "missionName":"Sentinel-1B",
                    "timestamp":$timestamp,
                    "beginViewingDate": $beginTimestamp,
                    "endViewingDate": $endTimestamp
                }
            """.trimIndent()
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(expectedResponse) }
            status { isOk() }
        }
    }

    @Test
    fun `returns a 422 response when a malformed id is sent`() {
        val id = "malformed-id"
        mvc.get("/features/$id") { accept = MediaType.APPLICATION_JSON }.andExpect {
            status { isUnprocessableEntity() }
        }
    }
}
