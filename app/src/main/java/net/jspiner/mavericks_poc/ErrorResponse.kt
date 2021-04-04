package net.jspiner.mavericks_poc

class ErrorResponse(private var errorMessage: String) {
    // just example for one-time read and mutable data
    fun readMessage(): String {
        val copied = errorMessage
        errorMessage = ""
        return copied
    }
}

class ErrorException(message: String, val errorResponse: ErrorResponse) : RuntimeException(message)
