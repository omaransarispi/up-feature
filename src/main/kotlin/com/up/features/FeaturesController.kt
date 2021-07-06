package com.up.features

import com.up.features.responses.FeatureResponse
import com.up.features.valueObjects.FeatureId
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/features", headers = ["Accept=*/*"])
class FeaturesController(val featuresService: FeaturesService) {
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.ALL_VALUE])
    fun byId(@PathVariable id: String): ResponseEntity<FeatureResponse> {
        val featureId: FeatureId = FeatureId.getInstance(id) ?: return ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)
        return ResponseEntity.ok(featuresService.getFeatureById(featureId))
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.ALL_VALUE])
    fun list(): List<FeatureResponse> {
        return featuresService.getFeatures()
    }
}