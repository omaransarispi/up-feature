package com.up.features.providers

import com.up.features.models.Feature
import com.up.features.models.FeatureWithQuicklook
import com.up.features.valueObjects.FeatureId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.sql.Timestamp
import java.util.*

@ExtendWith(MockitoExtension::class)
class StaticFeaturesProviderTest {
    @Test
    fun `returns the expected number of features`() {
        val features = StaticFeaturesProvider().getFeatures()
        Assertions.assertEquals(features.count(), 2)
    }

    @Test
    fun `deserializes feature as expected`() {
        val results = StaticFeaturesProvider().getFeatures()
        val expectedResult = listOf(
            Feature.getInstance(
                uid = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea"),
                missionName = "Sentinel-1B",
                timestamp = Timestamp(1554831167697),
                beginViewingDate = Timestamp(1554831167697),
                endViewingDate = Timestamp(1554831202043)
            ),
            Feature.getInstance(
                uid = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aeb"),
                missionName = "Sentinel-1C",
                timestamp = Timestamp(1554831167698),
                beginViewingDate = Timestamp(1554831167698),
                endViewingDate = Timestamp(1554831202045)
            )
        )
        Assertions.assertEquals(expectedResult, results)
    }

    @Test
    fun `given quick look exists, deserializes it`() {
        val result: FeatureWithQuicklook =
            StaticFeaturesProvider().getQuicklook(FeatureId.getInstance("39c2f29e-c0f8-4a39-a98b-deed547d6aeb")!!)!!
        Assertions.assertEquals("fake_quick_look_2", result.properties.quicklook)
    }

    @Test
    fun `given feature for the requested quicklook does not exist, return null`() {
        val result: FeatureWithQuicklook? =
            StaticFeaturesProvider().getQuicklook(FeatureId.getInstance("49c2f29e-c0f8-4a39-a98b-deed547d6aeb")!!)
        Assertions.assertEquals(null, result)
    }
}