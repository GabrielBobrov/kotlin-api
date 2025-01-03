package com.gabriel.kotlin.entrypoint.exception

import com.gabriel.kotlin.entrypoint.exception.model.ErrorResponse
import com.gabriel.kotlin.core.exception.PersonNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)



    @ExceptionHandler(PersonNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlePersonNotFoundException(ex: PersonNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.Builder()
            .status(HttpStatus.NOT_FOUND.value())
            .error(HttpStatus.NOT_FOUND.reasonPhrase)
            .message(ex.message)
            .timestamp(System.currentTimeMillis())
            .build()
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("An unexpected error occurred: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val errors = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        logger.error("HttpMessageNotReadableException occurred: ${ex.message}")

        val errorMessage = try {
            val message = ex.message ?: "JSON parse error"
            val field = message.substringAfter("parameter ", "unknown").substringBefore(" ")
            val problem = message.substringAfter("problem: ", "Invalid input")

            ErrorResponse.Builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.reasonPhrase)
                .message(problem)
                .timestamp(System.currentTimeMillis())
                .field(field)
                .build()

        } catch (e: Exception) {
            logger.error("Error while parsing exception message: ${e.message}", e)
            ErrorResponse.Builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.reasonPhrase)
                .message("JSON parse error")
                .timestamp(System.currentTimeMillis())
                .field("unknown")
                .build()
        }

        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}