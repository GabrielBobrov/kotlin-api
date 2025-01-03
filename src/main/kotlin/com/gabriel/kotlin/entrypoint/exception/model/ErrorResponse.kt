package com.gabriel.kotlin.entrypoint.exception.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String?,
    val timestamp: Long,
    val field: String?
) {
    class Builder {
        private var status: Int = 0
        private var error: String = ""
        private var message: String? = null
        private var timestamp: Long = System.currentTimeMillis()
        private var field: String? = null

        fun status(status: Int) = apply { this.status = status }
        fun error(error: String) = apply { this.error = error }
        fun message(message: String?) = apply { this.message = message }
        fun timestamp(timestamp: Long) = apply { this.timestamp = timestamp }
        fun field(field: String?) = apply { this.field = field }

        fun build(): ErrorResponse {
            return ErrorResponse(
                status = status,
                error = error,
                message = message,
                timestamp = timestamp,
                field = field
            )
        }
    }
}