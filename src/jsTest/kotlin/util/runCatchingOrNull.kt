package util

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

inline fun <reified E : Exception> runCatchingOrNull(block: () -> Unit): E? = try {
    block()
    null
} catch (e: Exception) {
    require(e is E)
    e
}

class RunCatchingOrNullTest {

    @Test
    fun runCatchingOrNull_blockDoesntThrow_returnsNull() {
        val result = runCatchingOrNull<Exception> {
            // No-op since we don't throw
        }
        assertEquals(expected = null, result)
    }

    @Test
    fun runCatchingOrNull_blockThrows_returnsException() {
        val thrownException = Exception("Test exception!")
        val result = runCatchingOrNull<Exception> {
            throw thrownException
        }
        assertEquals(expected = thrownException, actual = result)
    }

    @Test
    fun runCatchingOrNull_blockThrows_unknownExceptionType_throws() {
        val thrownException = RuntimeException("Test exception!")
        var caughtException: IllegalArgumentException? = null
        try {
            runCatchingOrNull<IllegalArgumentException> {
                throw thrownException
            }
        } catch (e: IllegalArgumentException) {
            caughtException = e
        }
        assertNotNull(caughtException)
    }

}