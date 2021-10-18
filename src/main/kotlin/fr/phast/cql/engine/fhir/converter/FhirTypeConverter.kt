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

package fr.phast.cql.engine.fhir.converter

import org.hl7.fhir.r4.model.*
import org.opencds.cqf.cql.engine.runtime.*
import org.opencds.cqf.cql.engine.runtime.Quantity
import org.opencds.cqf.cql.engine.runtime.Ratio
import java.math.BigDecimal
import java.util.*
import java.util.Date


/**
 * Provides functions for converting from CQL-to-FHIR and vice versa. The return
 * types on the functions represent the most derived common type or interface
 * across all FHIR versions. The implementations of this interface are expected
 * to return the appropriate version specific structure for a given conversion
 * (e.g. the interface for toFhirBoolean is defined as
 * IPrimitiveType&lt;Boolean&gt; but it will return an dstu3.model.BooleanType
 * or r4.model.BooleanType and so on).
 */
interface FhirTypeConverter {
    // CQL-to-FHIR conversions
    /**
     * Tests if an Object is a FHIR structure
     *
     * @param value the value to test
     * @return true if value is a FHIR structure, false otherwise
     * @throws NullPointerException if value is null
     */
    fun isFhirType(value: Any?): Boolean?

    /**
     * Converts an Object to a FHIR structure.
     *
     * @param value the value to convert
     * @return a FHIR structure
     * @throws IllegalArgumentException is value is an Iterable
     */
    fun toFhirType(value: Any?): Resource?

    /**
     * Converts an iterable of Objects to FHIR structures. Preserves ordering,
     * nulls, and sublist hierarchy
     *
     * @param values an Iterable containing CQL structures, nulls, or sublists
     * @return an Iterable containing FHIR types, nulls, and sublists
     */
    fun toFhirTypes(values: Iterable<*>?): Iterable<Any?>?

    /**
     * Converts a String to a FHIR Id
     *
     * @param value the value to convert
     * @return a FHIR Id
     */
    fun toFhirId(value: String?): IdType?

    /**
     * Converts a Boolean to a FHIR Boolean
     *
     * @param value the value to convert
     * @return a FHIR Boolean
     */
    fun toFhirBoolean(value: Boolean?): BooleanType?

    /**
     * Converts an Integer to a FHIR Integer
     *
     * @param value the value to convert
     * @return a FHIR Integer
     */
    fun toFhirInteger(value: Int?): IntegerType?

    /**
     * Converts a BigDecimal to a FHIR Decimal
     *
     * @param value the value to convert
     * @return a FHIR Decimal
     */
    fun toFhirDecimal(value: BigDecimal?): DecimalType?

    /**
     * Converts a CQL Date to a FHIR Date
     *
     * @param value the value to convert
     * @return a FHIR Date
     */
    fun toFhirDate(value: Date?): DateType?

    /**
     * Converts a CQL DateTime to a FHIR DateTime
     *
     * @param value the value to convert
     * @return a FHIR DateTime
     */
    fun toFhirDateTime(value: DateTime?): DateTimeType?

    /**
     * Converts a CQL Time to a FHIR Time
     *
     * @param value the value to convert
     * @return a FHIR Time
     */
    fun toFhirTime(value: Time?): TimeType?

    /**
     * Converts a String to a FHIR String
     *
     * @param value the value to convert
     * @return a FHIR String
     */
    fun toFhirString(value: String?): StringType?

    /**
     * Converts a CQL Quantity to a FHIR Quantity
     *
     * @param value the value to convert
     * @return a FHIR Quantity
     */
    fun toFhirQuantity(value: Quantity?): org.hl7.fhir.r4.model.Quantity?

    /**
     * Converts a CQL Ratio to a FHIR Ratio
     *
     * @param value the value to convert
     * @return a FHIR Ratio
     */
    fun toFhirRatio(value: Ratio?): org.hl7.fhir.r4.model.Ratio?

    /**
     * Converts a CQL Any to a FHIR Any
     *
     * @param value the value to convert
     * @return a FHIR Any
     */
    fun toFhirAny(value: Any?): Resource?

    /**
     * Converts a CQL Code to a FHIR Coding
     *
     * @param value the value to convert
     * @return a FHIR Coding
     */
    fun toFhirCoding(value: Code?): Coding?

    /**
     * Converts a CQL Concept to a FHIR CodeableConcept
     *
     * @param value the value to convert
     * @return a FHIR CodeableConcept
     */
    fun toFhirCodeableConcept(value: Concept?): CodeableConcept?

    /**
     * Converts a CQL Interval to a FHIR Period
     *
     * @param value a Date or DateTime Interval
     * @return a FHIR Period
     */
    fun toFhirPeriod(value: Interval?): Period?

    /**
     * Converts a CQL Interval to a FHIR Range
     *
     * @param value a Quantity Interval
     * @return a FHIR Range
     */
    fun toFhirRange(value: Interval?): Range?

    /**
     * Converts a CQL Interval to FHIR Range or Period
     *
     * @param value a Quantity, Date, or DateTime interval
     * @return A FHIR Range or Period
     */
    fun toFhirInterval(value: Interval?): Range?

    /**
     * Converts a CQL Tuple to a FHIR Structure
     *
     * @param value the value to convert
     * @return a FHIR Structure
     */
    fun toFhirTuple(value: Tuple?): Resource?
    // FHIR-to-CQL conversions
    /**
     * Tests if an Object is a CQL type
     *
     * @param value the value to convert
     * @return true if value is a CQL type, false otherwise
     * @throws NullPointerException if value is null
     */
    fun isCqlType(value: Any?): Boolean?

    /**
     * Converts an Object to a CQL type.
     *
     * @param value the value to convert a FHIR structure
     * @return a CQL type
     * @throws IllegalArgumentException is value is an Iterable
     */
    fun toCqlType(value: Any?): Any?

    /**
     * Converts an iterable of Objects to CQL types. Preserves ordering, nulls, and
     * sublist hierarchy
     *
     * @param values the values to convert an Iterable containing FHIR structures,
     * nulls, or sublists
     * @return an Iterable containing CQL types, nulls, and sublists
     */
    fun toCqlTypes(values: Iterable<*>?): Iterable<Any?>?

    /**
     * Converts a FHIR Id to a CQL String
     *
     * @param value the value to convert
     * @return a String
     */
    fun toCqlId(value: IdType?): String?

    /**
     * Converts a FHIR Boolean to a CQL Boolean
     *
     * @param value the value to convert
     * @return a Boolean
     */
    fun toCqlBoolean(value: BooleanType?): Boolean?

    /**
     * Converts a FHIR Integer to a CQL Integer
     *
     * @param value the value to convert
     * @return an Integer
     */
    fun toCqlInteger(value: IntegerType?): Int?

    /**
     * Converts a FHIR Decimal to a CQL Decimal
     *
     * @param value the value to convert
     * @return a BigDecimal
     */
    fun toCqlDecimal(value: DecimalType?): BigDecimal?

    /**
     * Converts a FHIR Date to a CQL Date
     *
     * @param value the value to convert
     * @return a CQL Date
     * @throws IllegalArgumentException if value is not a Date
     */
    fun toCqlDate(value: DateType?): Date?

    /**
     * Converts a FHIR DateTime to a CQL DateTime
     *
     * @param value the value to convert
     * @return a CQL DateTime
     * @throws IllegalArgumentException if value is not a DateTime
     */
    fun toCqlDateTime(value: DateTimeType?): DateTime?

    /**
     * Converts a FHIR DateTime, Date, or Instance to a CQL BaseTemporal
     *
     * @param value the value to convert
     * @return a CQL BaseTemporal
     * @throws IllegalArgumentException if value is not a DateTime, Date, or Instant
     */
    //fun toCqlTemporal(value: IPrimitiveType<Date?>?): BaseTemporal?

    /**
     * Converts a FHIR Time to a CQL Time
     *
     * @param value the value to convert
     * @return a CQL Time
     * @throws IllegalArgumentException if value is not a Time
     */
    fun toCqlTime(value: TimeType?): Time?

    /**
     * Converts a FHIR String to a CQL String
     *
     * @param value the value to convert
     * @return a String
     */
    fun toCqlString(value: StringType?): String?

    /**
     * Converts a FHIR Quantity to a CQL Quantity
     *
     * @param value the value to convert
     * @return a CQL Quantity
     * @throws IllegalArgumentException if value is not a Quantity
     */
    fun toCqlQuantity(value: org.hl7.fhir.r4.model.Quantity?): Quantity?

    /**
     * Converts a FHIR Ratio to a CQL Ratio
     *
     * @param value the value to convert
     * @return a CQL Ratio
     * @throws IllegalArgumentException if value is not a Ratio
     */
    fun toCqlRatio(value: org.hl7.fhir.r4.model.Ratio?): Ratio?

    /**
     * Converts a FHIR Any to a CQL Any
     *
     * @param value the value to convert
     * @return a CQL Any
     */
    fun toCqlAny(value: Resource?): Any?

    /**
     * Converts a FHIR Coding to a CQL Code
     *
     * @param value the value to convert
     * @return a CQL Code
     */
    fun toCqlCode(value: Coding?): Code?

    /**
     * Converts a FHIR CodeableConcept to a CQL Concept
     *
     * @param value the value to convert
     * @return a CQL Concept
     * @throws IllegalArgumentException if value is not a CodeableConcept
     */
    fun toCqlConcept(value: CodeableConcept?): Concept?

    /**
     * Converts a FHIR Range or Period to a CQL Interval
     *
     * @param value the value to convert
     * @return a CQL Interval
     * @throws IllegalArgumentException if value is not a Range or Period
     */
    fun toCqlInterval(value: Range?): Interval?

    /**
     * Converts a FHIR Structure to a CQL Tuple
     *
     * @param value the value to convert
     * @return a CQL Tuple
     */
    fun toCqlTuple(value: Resource?): Tuple?
}
