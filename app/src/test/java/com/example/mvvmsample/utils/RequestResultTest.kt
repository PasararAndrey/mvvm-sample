package com.example.mvvmsample.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class RequestResultTest {
    @Test
    fun `Success class should store data correctly`() {
        val data = "Test Data"
        val result = RequestResult.Success(data)

        assertEquals(data, result.data)
    }

    @Test
    fun `Error class should store data and error correctly`() {
        val data = "Test Data"
        val error = Throwable("Test Error")
        val result = RequestResult.Error(data, error)

        assertEquals(data, result.data)
        assertEquals(error, result.error)
    }

    @Test
    fun `Error class should store null data correctly`() {
        val error = Throwable("Test Error")
        val result = RequestResult.Error<String>(error = error)

        assertNull(result.data)
        assertEquals(error, result.error)
    }

    @Test
    fun `InProgress class should store data correctly`() {
        val data = "Test Data"
        val result = RequestResult.InProgress(data)

        assertEquals(data, result.data)
    }

    @Test
    fun `InProgress class should store null data correctly`() {
        val result = RequestResult.InProgress<String>()

        assertNull(result.data)
    }
}
