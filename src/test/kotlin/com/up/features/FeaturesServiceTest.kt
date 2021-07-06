package com.up.features

import com.up.features.responses.FeatureResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.sql.Timestamp
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
class FeaturesServiceTest {
    @Mock
    lateinit var mockFeaturesProvider: FeaturesProvider

    @Test
    fun `returns a collection of feature responses`() {
        val id = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
        val missionName = "Sentinel-1B"
        val timestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay())
        val acquisitionBeginViewingDate = Timestamp.valueOf(LocalDate.now().atStartOfDay().minusHours(1))
        val acquisitionEndViewingDate = Timestamp.valueOf(LocalDate.now().atStartOfDay().plusHours(1))
        val fakeFeatures = listOf(
            Features(
                id,
                missionName,
                timestamp,
                acquisitionBeginViewingDate,
                acquisitionEndViewingDate,
            )
        )
        Mockito.`when`(mockFeaturesProvider.getFeatures()).thenReturn(fakeFeatures)
        val service = FeaturesService(mockFeaturesProvider)

        val results = service.getFeatures()
        val expectedResponse = listOf(
            FeatureResponse(
                id.toString(),
                missionName,
                timestamp,
                acquisitionBeginViewingDate,
                acquisitionEndViewingDate
            )
        )
        Assertions.assertEquals(expectedResponse, results)
    }
}