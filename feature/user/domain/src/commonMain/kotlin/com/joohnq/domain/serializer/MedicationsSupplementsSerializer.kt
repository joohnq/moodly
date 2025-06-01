package com.joohnq.domain.serializer

import com.joohnq.domain.entity.MedicationsSupplements
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object MedicationsSupplementsSerializer : KSerializer<MedicationsSupplements> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("MedicationsSupplements", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: MedicationsSupplements) {
        encoder.encodeInt(value.id)
    }

    override fun deserialize(decoder: Decoder): MedicationsSupplements {
        return MedicationsSupplements.from(decoder.decodeInt())
    }
}