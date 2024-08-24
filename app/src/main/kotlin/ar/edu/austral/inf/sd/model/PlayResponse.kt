package ar.edu.austral.inf.sd.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid

/**
 *
 * @param originalContentType el tipo de contenido que enviaste
 * @param originalLength la longitud original que enviaste
 * @param originalHash el hash del mensaje original que enviaste
 * @param receivedContentType el tipo de contenido que volvió
 * @param receivedLength la longitud que volvió
 * @param receivedHash el hash del mensaje que volvió
 * @param signatures 
 */
data class PlayResponse(

    @get:JsonProperty("originalContentType", required = true) val originalContentType: kotlin.String,

    @get:JsonProperty("originalLength", required = true) val originalLength: kotlin.Int,

    @get:JsonProperty("originalHash", required = true) val originalHash: kotlin.String,

    @get:JsonProperty("receivedContentType", required = true) val receivedContentType: kotlin.String,

    @get:JsonProperty("receivedLength", required = true) val receivedLength: kotlin.Int,

    @get:JsonProperty("receivedHash", required = true) val receivedHash: kotlin.String,

    @field:Valid
    @get:JsonProperty("signatures") val signatures: kotlin.collections.List<Signature>? = null
    ) {

}

