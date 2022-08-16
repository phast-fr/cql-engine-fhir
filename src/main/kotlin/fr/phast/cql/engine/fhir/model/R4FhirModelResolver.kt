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

package fr.phast.cql.engine.fhir.model

import fr.phast.cql.engine.fhir.exception.UnknownTypeException
import org.opencds.cqf.cql.engine.runtime.Date
import org.hl7.fhir.r4.model.*
import org.opencds.cqf.cql.engine.model.ModelResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class R4FhirModelResolver: ModelResolver {

    private var packageName: String

    init {
        this.packageName = "org.hl7.fhir.r4.model"
    }

    override fun getPackageName(): String {
        return this.packageName
    }

    override fun setPackageName(packageName: String) {
        this.packageName = packageName
    }

    override fun resolvePath(target: Any, path: String?): Any? {
        when (target) {
            is Patient -> {
                return when (path) {
                    "gender" -> target.gender
                    "active" -> target.active
                    "birthDate" -> target.birthDate
                    "deceased" -> target.deceasedBoolean
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is MedicationRequest -> {
                return when (path) {
                    "extension" -> target.extension
                    "contained" -> target.contained
                    "authoredOn" -> target.authoredOn
                    "medication" -> target.medicationReferenceTarget
                    "medication.code" -> target.medicationReferenceTarget
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is MedicationStatement -> {
                return when (path) {
                    "extension" -> target.extension
                    "contained" -> target.contained
                    "medication" -> target.medicationReferenceTarget
                    "medication.code" -> target.medicationReferenceTarget
                    "dosage" -> target.dosage
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is Condition -> {
                return when (path) {
                    "verificationStatus" -> target.verificationStatus
                    "clinicalStatus" -> target.clinicalStatus
                    "abatement" -> {
                        return if (target.abatementString != null) {
                            target.abatementString
                        }
                        else if (target.abatementAge != null) {
                            target.abatementAge
                        }
                        else if (target.abatementPeriod != null) {
                            target.abatementPeriod
                        }
                        else if (target.abatementRange != null) {
                            target.abatementRange
                        }
                        else if (target.abatementDateTime != null) {
                            target.abatementDateTime
                        }
                        else {
                            null
                        }
                    }
                    "code" -> target.code
                    "severity" -> target.severity
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is Observation -> {
                return when (path) {
                    "value" -> {
                        if (target.valueQuantity != null) {
                            target.valueQuantity
                        }
                        else if (target.valueString != null) {
                            target.valueString
                        }
                        else if (target.valueBoolean != null) {
                            target.valueBoolean
                        }
                        else if (target.valueInteger != null) {
                            target.valueInteger
                        }
                        else if (target.valuePeriod != null) {
                            target.valuePeriod
                        }
                        else if (target.valueCodeableConcept != null) {
                            target.valueCodeableConcept
                        }
                        else if (target.valueDateTime != null) {
                            target.valueDateTime
                        }
                        else if (target.valueRange != null) {
                            target.valueRange
                        }
                        else if (target.valueRatio != null) {
                            target.valueRatio
                        }
                        else if (target.valueSampledData != null) {
                            target.valueSampledData
                        }
                        else if (target.valueTime != null) {
                            target.valueTime
                        }
                        else {
                            null
                        }
                    }
                    "status" -> target.status
                    "interpretation" -> target.interpretation
                    "code" -> target.code
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is DiagnosticReport -> {
                return when (path) {
                    "code" -> target.code
                    "status" -> target.status
                    "conclusionCode" -> target.conclusionCode
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is Medication -> {
                return when (path) {
                    "code" -> target.code
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is Extension -> {
                return when (path)  {
                    "url" -> target.url
                    "value" -> {
                        if (target.valueBoolean != null) {
                            target.valueBoolean
                        }
                        else if (target.valueCodeableConcept != null) {
                            target.valueCodeableConcept
                        }
                        else if (target.valueInteger != null) {
                            target.valueInteger
                        }
                        else if (target.valueQuantity != null) {
                            target.valueQuantity
                        }
                        else if (target.valueCode != null) {
                            target.valueCode
                        }
                        else if (target.valueAddress != null) {
                            target.valueAddress
                        }
                        else if (target.valueAge != null) {
                            target.valueAge
                        }
                        else if (target.valueAnnotation != null) {
                            target.valueAnnotation
                        }
                        else if (target.valueAttachment != null) {
                            target.valueAttachment
                        }
                        else if (target.valueBase64Binary != null) {
                            target.valueBase64Binary
                        }
                        else if (target.valueCanonical != null) {
                            target.valueCanonical
                        }
                        else if (target.valueString != null) {
                            target.valueString
                        }
                        else {
                            logger.error("target: $target, path: $path")
                            null
                        }
                    }
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is CodeableConcept -> {
                return when (path) {
                    "coding" -> target.coding
                    "text" -> target.text
                    "display" -> target.text
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is Coding -> {
                return when (path) {
                    "code" -> target.code
                    "system" -> target.system
                    "value" -> target.code
                    "version" -> target.version
                    "display" -> target.display
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is Quantity -> {
                return when (path) {
                    "value" -> target.value
                    "comparator" -> target.comparator
                    "system" -> target.system
                    "unit" -> target.unit
                    "code" -> target.code
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is BooleanType -> {
                return when (path) {
                    "value" -> target.value
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is StringType -> {
                return when (path) {
                    "value" -> target.value
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is CodeType -> {
                return when (path) {
                    "value" -> target.value
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is DateType -> {
                return when (path) {
                    "value" -> Date(target.value)
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is UriType -> {
                return when (path) {
                    "value" -> target.value
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is DecimalType -> {
                return when (path) {
                    "value" -> target.value
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is ObservationStatus -> {
                return when (path) {
                    "value" -> target.text
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is DiagnosticReportStatus -> {
                return when (path) {
                    "value" -> target.text
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
            is AdministrativeGender -> {
                return when (path) {
                    "value" -> target.text
                    else -> {
                        logger.error("target: $target, path: $path")
                        null
                    }
                }
            }
        }

        logger.error("target: $target, path: $path")
        return null
    }

    override fun getContextPath(contextType: String?, targetType: String?): Any? {
        if (targetType == null || contextType == null) {
            return null
        }

        if (!(contextType == "Unspecified" || contextType == "Population")) {
            if (contextType == "Patient" && targetType == "MedicationStatement") {
                return "subject"
            }
            else if (contextType == targetType) {
                return "id"
            }
        }
        return "subject"
    }

    override fun resolveType(typeName: String?): Class<*> {
        val localTypeName = when (typeName) {
            "ActivityDefinitionKind" -> "RequestResourceType"
            "ActivityParticipantType" -> "ActionParticipantType"
            "CarePlanStatus" -> "RequestStatus"
            "ChargeItemDefinitionPriceComponentType" -> "InvoicePriceComponentType"
            "ClaimResponseStatus" -> "FinancialResourceStatusCodes"
            "ClaimStatus" -> "FinancialResourceStatusCodes"
            "CommunicationPriority" -> "RequestPriority"
            "CommunicationRequestStatus" -> "RequestStatus"
            "CommunicationStatus" -> "EventStatus"
            "CompartmentCode" -> "CompartmentType"
            "ContractPublicationStatus" -> "ContractResourcePublicationStatusCodes"
            "ContractStatus" -> "ContractResourceStatusCodes"
            "CoverageStatus" -> "FinancialResourceStatusCodes"
            "DayOfWeek" -> "DaysOfWeek"
            "DetectedIssueStatus" -> "ObservationStatus"
            "DeviceRequestStatus" -> "RequestStatus"
            "DocumentConfidentiality" -> "VConfidentialityClassification"
            "EligibilityRequestStatus" -> "FinancialResourceStatusCodes"
            "EligibilityResponseStatus" -> "FinancialResourceStatusCodes"
            "EnrollmentRequestStatus" -> "FinancialResourceStatusCodes"
            "EnrollmentResponseStatus" -> "FinancialResourceStatusCodes"
            "FHIRAllTypes" -> "ResourceType"
            "FHIRDefinedType" -> "ResourceType"
            "FHIRResourceType" -> "ResourceType"
            "ImmunizationEvaluationStatus" -> "ImmunizationEvaluationStatusCodes"
            "ImmunizationStatus" -> "ImmunizationStatusCodes"
            "MediaStatus" -> "EventStatus"
            "MedicationKnowledgeStatus" -> "MedicationStatusCodes"
            "MedicationRequestPriority" -> "RequestPriority"
            "MedicationStatementStatus" -> "MedicationStatusCodes"
            "MedicationStatus" -> "MedicationStatusCodes"
            "Messageheader_Response_Request" -> "MessageheaderResponseRequest"
            "NutritiionOrderIntent" -> "RequestIntent"
            "NutritionOrderStatus" -> "RequestStatus"
            "ParameterUse" -> "OperationParameterUse"
            "ParticipantStatus" -> "ParticipationStatus"
            "PaymentNoticeStatus" -> "FinancialResourceStatusCodes"
            "PaymentReconciliationStatus" -> "FinancialResourceStatusCodes"
            "ProcedureStatus" -> "EventStatus"
            "ReferredDocumentStatus" -> "CompositionStatus"
            "RiskAssessmentStatus" -> "ObservationStatus"
            "SectionMode" -> "ListMode"
            "ServiceRequestIntent" -> "RequestIntent"
            "ServiceRequestPriority" -> "RequestPriority"
            "ServiceRequestStatus" -> "RequestStatus"
            "TaskPriority" -> "RequestPriority"
            "VisionStatus" -> "FinancialResourceStatusCodes"
            "base64Binary" -> "Base64BinaryType"
            "boolean" -> "BooleanType"
            "date" -> "DateType"
            "string" -> "StringType"
            "uri" -> "UriType"
            "xhtml" -> "java.lang.String"
            else -> typeName
        }
        try {
            // Other Types in package.
            return Class.forName(String.format("%s.%s", packageName, localTypeName))
        }
        catch (e: ClassNotFoundException) {
        }

        try {
            // Just give me SOMETHING.
            return Class.forName(localTypeName)
        }
        catch (e: ClassNotFoundException) {
            throw UnknownTypeException(
                String.format(
                    "Could not resolve type %s. Primary package for this resolver is %s",
                    typeName,
                    packageName
                )
            )
        }
    }

    override fun resolveType(value: Any): Class<*> {
        return value.javaClass
    }

    override fun `is`(value: Any, type: Class<*>): Boolean {
        return value.javaClass.canonicalName == type.canonicalName
    }

    override fun `as`(value: Any, type: Class<*>, isStrict: Boolean): Any? {
        return when (value) {
            is CodeableConcept -> {
                return when (type.name) {
                    "org.hl7.fhir.r4.model.Coding" -> {
                        value.coding?.get(0)
                    }
                    else -> {
                        logger.error("value: $value, type: $type, isStrict: $isStrict")
                        null
                    }
                }
            }
            else -> {
                logger.error("value: $value, type: $type, isStrict: $isStrict")
                null
            }
        }
    }

    override fun createInstance(typeName: String?): Any {
        TODO("Not yet implemented")
    }

    override fun setValue(target: Any?, path: String?, value: Any?) {
        TODO("Not yet implemented")
    }

    override fun objectEqual(left: Any?, right: Any?): Boolean {
        return if (left?.javaClass?.name == right?.javaClass?.name) {
            when (left) {
                is Medication -> {
                    left.code?.text == (right as Medication).code?.text
                }
                is MedicationRequest -> {
                    left.authoredOn?.value == (right as MedicationRequest).authoredOn?.value
                }
                is DateTimeType -> {
                    left.value == (right as DateTimeType).value
                }
                else -> {
                    logger.error("case not supported ! $left objectEqual $right")
                    false
                }
            }
        }
        else {
            logger.error("case not supported ! $left objectEqual $right")
            return false
        }
    }

    override fun objectEquivalent(left: Any?, right: Any?): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(R4FhirModelResolver::class.java)
    }
}
