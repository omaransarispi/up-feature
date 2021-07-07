package com.up.features

import com.up.features.responses.FeatureResponse
import com.up.features.valueObjects.FeatureId
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/features")
@CrossOrigin(origins = ["*"]) // Not safe for a production app
class FeaturesController(val featuresService: FeaturesService) {
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun byId(@PathVariable id: String): ResponseEntity<FeatureResponse> {
        val featureId: FeatureId = FeatureId.getInstance(id) ?: return ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)

        val response = featuresService.getFeature(featureId)
        return if (response == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(response)
        }
    }

    @GetMapping("/{id}/quicklook", produces = [MediaType.IMAGE_PNG_VALUE])
    fun featureQuicklook(@PathVariable id: String): ResponseEntity<ByteArray> {
        val featureId: FeatureId = FeatureId.getInstance(id) ?: return ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)
        val response = featuresService.getQuicklook(featureId)
        return if (response == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(response)
        }
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun list(): List<FeatureResponse> {
        return featuresService.getFeatures()
    }
}