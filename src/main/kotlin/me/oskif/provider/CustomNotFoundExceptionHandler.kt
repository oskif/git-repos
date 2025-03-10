package me.oskif.provider

import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import me.oskif.model.ErrorResponse

@Provider
class CustomNotFoundExceptionHandler : ExceptionMapper<NotFoundException> {
    override fun toResponse(exception: NotFoundException): Response {
        val errorResponse = ErrorResponse(
            status = exception.response.status,
            message = exception.message ?: "Resource not found"
        )
        return Response.status(Response.Status.NOT_FOUND)
            .entity(errorResponse)
            .build()
    }
}