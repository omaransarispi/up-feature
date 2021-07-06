package com.up.features.models

import java.util.*

data class FeatureWithQuicklook(val properties: FeatureWithQuickLookProperties)
data class FeatureWithQuickLookProperties(val uid: UUID, val quicklook: String)
