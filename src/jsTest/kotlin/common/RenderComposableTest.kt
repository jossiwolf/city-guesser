package common

import util.requireBody
import util.runCatchingOrNull
import kotlinx.browser.document
import kotlinx.dom.appendElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class RenderComposableTest {

    @Test
    fun requireRootElementHasId_rootWithCorrectId_noException() {
        // Given a DOM with a root element with id "root"
        val rootName = "root"
        document.requireBody().appendElement(rootName) {
            id = rootName
        }
        // When
        val exception = runCatchingOrNull<IllegalStateException> {
            requireRootElementHasId(rootName)
        }

        // Then
        assertEquals(expected = null, actual = exception)
    }

    @Test
    fun requireRootElementHasId_rootWithWrongId_throwsException() {
        // Given a DOM with a root element with id "root"
        val rootName = "root"
        document.requireBody().appendElement(rootName) {
            id = rootName
        }
        // When
        val exception = runCatchingOrNull<IllegalStateException> {
            requireRootElementHasId("wrongid")
        }

        // Then
        assertIs<IllegalStateException>(exception)
    }

}