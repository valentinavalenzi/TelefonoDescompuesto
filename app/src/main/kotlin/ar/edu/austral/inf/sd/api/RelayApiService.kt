package ar.edu.austral.inf.sd.api

import ar.edu.austral.inf.sd.model.Signature

interface RelayApiService {

    /**
     * POST /relay
     * Firma un mensaje y lo manda al siguiente
     *
     * @param message  (required)
     * @param signatures  (optional)
     * @return mensaje recibido y reenviado. (status code 202)
     * @see RelayApi#relayMessage
     */
    fun relayMessage(message: kotlin.String, signatures: kotlin.collections.List<Signature>?): Signature
}
