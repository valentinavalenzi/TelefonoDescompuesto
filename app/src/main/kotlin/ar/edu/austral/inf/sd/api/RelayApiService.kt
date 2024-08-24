package ar.edu.austral.inf.sd.api


interface RelayApiService {

    /**
     * POST /relay
     * Firma un mensaje y lo manda al siguiente
     *
     * @param message  (required)
     * @param signature  (optional)
     * @return mensaje recibido y reenviado. (status code 202)
     * @see RelayApi#relayMessage
     */
    fun relayMessage(message: kotlin.String, signature: kotlin.String?): kotlin.Any
}
