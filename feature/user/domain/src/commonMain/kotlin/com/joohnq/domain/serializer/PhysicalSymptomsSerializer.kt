package com.joohnq.domain.serializer

import com.joohnq.domain.entity.PhysicalSymptoms
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PhysicalSymptomsSerializer : KSerializer<PhysicalSymptoms> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("PhysicalSymptoms", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: PhysicalSymptoms) {
        encoder.encodeInt(value.id)
    }

    override fun deserialize(decoder: Decoder): PhysicalSymptoms {
        return PhysicalSymptoms.from(decoder.decodeInt())
    }
}