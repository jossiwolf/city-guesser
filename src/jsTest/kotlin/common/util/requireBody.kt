package common.util

import org.w3c.dom.Document

fun Document.requireBody() = requireNotNull(body)