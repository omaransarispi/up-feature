package com.up.features

import com.up.features.models.Feature
import com.up.features.models.FeatureWithQuicklook
import com.up.features.providers.FeaturesProvider
import com.up.features.responses.FeatureResponse
import com.up.features.valueObjects.FeatureId
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
            Feature.getInstance(
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
                timestamp.time,
                acquisitionBeginViewingDate.time,
                acquisitionEndViewingDate.time
            )
        )
        Assertions.assertEquals(expectedResponse, results)
    }

    @Test
    fun `returns a unique feature identified by its id`() {
        val id = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
        val missionName = "Sentinel-1B"
        val timestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay())
        val acquisitionBeginViewingDate = Timestamp.valueOf(LocalDate.now().atStartOfDay().minusHours(1))
        val acquisitionEndViewingDate = Timestamp.valueOf(LocalDate.now().atStartOfDay().plusHours(1))
        val fakeFeatures = listOf(
            Feature.getInstance(
                id,
                missionName,
                timestamp,
                acquisitionBeginViewingDate,
                acquisitionEndViewingDate,
            ),
            Feature.getInstance(
                UUID.randomUUID(),
                missionName,
                timestamp,
                acquisitionBeginViewingDate,
                acquisitionEndViewingDate,
            )
        )
        Mockito.`when`(mockFeaturesProvider.getFeatures()).thenReturn(fakeFeatures)
        val service = FeaturesService(mockFeaturesProvider)

        val results = service.getFeature(FeatureId.getInstance(id.toString())!!)
        val expectedResponse = FeatureResponse(
            id.toString(),
            missionName,
            timestamp.time,
            acquisitionBeginViewingDate.time,
            acquisitionEndViewingDate.time
        )
        Assertions.assertEquals(expectedResponse, results)
    }

    @Test
    fun `returns null if no feature is found`() {
        val id = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
        val fakeFeatures = listOf(
            Feature.getInstance(
                UUID.randomUUID(),
                "Sentinel-1B",
                Timestamp.valueOf(LocalDate.now().atStartOfDay()),
                Timestamp.valueOf(LocalDate.now().atStartOfDay().minusHours(1)),
                Timestamp.valueOf(LocalDate.now().atStartOfDay().plusHours(1))
            )
        )
        Mockito.`when`(mockFeaturesProvider.getFeatures()).thenReturn(fakeFeatures)
        val service = FeaturesService(mockFeaturesProvider)

        val results = service.getFeature(FeatureId.getInstance(id.toString())!!)
        Assertions.assertEquals(null, results)
    }

    @Test
    fun `returns a decoded string if quicklook is found`() {
        val id = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
        Mockito.`when`(mockFeaturesProvider.getQuicklook(FeatureId.getInstance(id.toString())!!)).thenReturn(
            FeatureWithQuicklook.getInstance(id, "aGVsbG8gd29ybGQ=")
        )
        val service = FeaturesService(mockFeaturesProvider)

        val results = service.getQuicklook(FeatureId.getInstance(id.toString())!!)
        Assertions.assertNotEquals(null, results)
    }

    @Test
    fun `returns null if no quicklook is found`() {
        val id = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
        Mockito.`when`(mockFeaturesProvider.getQuicklook(FeatureId.getInstance(id.toString())!!)).thenReturn(null)
        val service = FeaturesService(mockFeaturesProvider)

        val results = service.getQuicklook(FeatureId.getInstance(id.toString())!!)
        Assertions.assertEquals(null, results)
    }
}