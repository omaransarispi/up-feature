package com.up.features.providers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class StaticFeaturesProviderTest {
    @Test
    fun `returns the expected number of features`() {
        val features = StaticFeaturesProvider().getFeatures()
        Assertions.assertEquals(features.count(), 2)
    }
}