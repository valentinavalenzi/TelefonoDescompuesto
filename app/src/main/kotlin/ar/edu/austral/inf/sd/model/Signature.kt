package ar.edu.austral.inf.sd.model

import java.util.Objects
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
 * @param name El nombre del nodo que firmo
 * @param hash El hash de la firma del nodo
 */
data class Signature(

    @get:JsonProperty("name") val name: kotlin.String? = null,

    @get:JsonProperty("hash") val hash: kotlin.String? = null
    ) {

}

