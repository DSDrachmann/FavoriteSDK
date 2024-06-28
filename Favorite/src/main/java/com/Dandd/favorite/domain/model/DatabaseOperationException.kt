package com.Dandd.favorite.domain.model
/**
 * Exception thrown when an operation on the database fails.
 */
class DatabaseOperationException(message: String, cause: Throwable? = null) : Exception(message, cause)