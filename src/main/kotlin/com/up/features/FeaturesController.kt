import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/features", headers=["Accept=application/json"])
class FeaturesController {
  @GetMapping
  fun list(){}
}