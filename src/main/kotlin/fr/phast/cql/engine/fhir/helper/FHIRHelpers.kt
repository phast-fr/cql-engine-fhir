/*
 * MIT License
 *
 * Copyright (c) 2021 PHAST
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.phast.cql.engine.fhir.helper

import org.hl7.fhir.r4.model.*
import org.opencds.cqf.cql.engine.data.ExternalFunctionProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FHIRHelpers: ExternalFunctionProvider {
    override fun evaluate(staticFunctionName: String, arguments: List<Any>): Any? {
        return when (staticFunctionName) {
            "extension" -> {
                if (arguments.size == 2 && arguments[0] is DomainResource && arguments[1] is String) {
                    return this.extension(arguments[0] as DomainResource, arguments[1] as String)
                }
                throw IllegalArgumentException("the arguments of extension function are illegal")
            }
            else -> {
                logger.error("staticFunctionName: $staticFunctionName is not supported ($arguments)")
                null
            }
        }
    }

    private fun extension(resource: DomainResource, url: String): List<Extension> {
        val extensions = mutableListOf<Extension>()
        resource.extension?.forEach { extension ->
            if (extension.url == url) {
                extensions.add(extension)
            }
        }
        return extensions
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(FHIRHelpers::class.java)
    }
}
