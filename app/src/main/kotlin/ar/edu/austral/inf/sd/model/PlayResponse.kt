package ar.edu.austral.inf.sd.model

import java.util.Objects
import ar.edu.austral.inf.sd.model.Signature
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import jakarta.validation.Valid

/**
 * 
 * @param hash el hash del mensaje original que enviaste
 * @param signatures 
 */
data class PlayResponse(

    @get:JsonProperty("hash") val hash: kotlin.String? = null,

    @field:Valid
    @get:JsonProperty("signatures") val signatures: kotlin.collections.List<Signature>? = null
    ) {

}

