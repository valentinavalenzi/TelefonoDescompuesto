package ar.edu.austral.inf.sd.api

import ar.edu.austral.inf.sd.model.Signature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class RelayApiController(@Autowired(required = true) val service: RelayApiService) {


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/relay"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    fun relayMessage(
        @RequestPart(value = "message", required = true) message: kotlin.String,
        @RequestParam(
            value = "signatures",
            required = false
        ) signatures: kotlin.collections.List<Signature>?
    ): ResponseEntity<Signature> {
        return ResponseEntity(service.relayMessage(message, signatures), HttpStatus.valueOf(202))
    }
}
