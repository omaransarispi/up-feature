package com.up.features

import FeaturesController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@SpringBootTest(classes = [FeaturesController::class])
@ActiveProfiles("test")
@AutoConfigureMockMvc
class FeaturesControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `returns an OK response when list is requested`() {
        mvc.get("/features"){contentType=MediaType.APPLICATION_JSON}.andExpect {
            status { isOk() }
        }
    }
}