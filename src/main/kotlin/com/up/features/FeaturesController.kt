package com.up.features

import com.up.features.responses.FeatureResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/features", headers = ["Accept=*/*"])
class FeaturesController(val featuresService: FeaturesService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.ALL_VALUE])
    fun list(): List<FeatureResponse> {
        return featuresService.getFeatures()
    }
}