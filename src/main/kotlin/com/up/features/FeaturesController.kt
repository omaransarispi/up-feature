package com.up.features

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/features", headers = ["Accept=*/*"])
class FeaturesController {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.ALL_VALUE])
    fun list(): List<FeatureResponse> {
        return listOf(
            FeatureResponse(
                "id",
                Timestamp.valueOf(LocalDate.now().atStartOfDay()),
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atStartOfDay().plusHours(1),
                "Sentinel-1B"
            )
        )
    }
}

data class FeatureResponse(
    val id: String,
    val timestamp: Timestamp,
    val beginViewingDate: LocalDateTime,
    val endViewingDate: LocalDateTime,
    val missionName: String
)
