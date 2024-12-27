package com.joohnq.mood.data.converters

import com.joohnq.mood.domain.MedicationsSupplements
import com.joohnq.mood.domain.PhysicalSymptoms
import com.joohnq.mood.domain.ProfessionalHelp
import com.varabyte.truthish.assertThat
import kotlin.test.Test
import kotlin.test.assertFails

class UserConverterTest {
    @Test
    fun `test fromMedicationsSupplements`() {
        val res =
            UserConverter().fromMedicationsSupplements(MedicationsSupplements.PrescribedMedications)

        assertThat(res).isEqualTo(0)
        assertThat(res).isNotEqualTo(-1)
    }

    @Test
    fun `test toMedicationsSupplements`() {
        val res = UserConverter().toMedicationsSupplements(2)

        assertThat(res).isEqualTo(MedicationsSupplements.ImNotTakingAny)
    }

    @Test
    fun `test toMedicationsSupplements with error`() {
        val item = 10

        assertFails {
            UserConverter().toMedicationsSupplements(item)
        }
    }

    @Test
    fun `test toPhysicalSymptoms`() {
        val res = UserConverter().toPhysicalSymptoms(2)

        assertThat(res).isEqualTo(PhysicalSymptoms.YesJustABit)
    }

    @Test
    fun `test toPhysicalSymptoms with error`() {
        assertFails {
            UserConverter().toPhysicalSymptoms(10)
        }
    }

    @Test
    fun `test fromProfessionalHelp`() {
        val res = UserConverter().fromProfessionalHelp(ProfessionalHelp.Yes)

        assertThat(res).isEqualTo(1)
    }

    @Test
    fun `test toProfessionalHelp`() {
        val res = UserConverter().toProfessionalHelp(0)

        assertThat(res).isEqualTo(ProfessionalHelp.No)
    }

    @Test
    fun `test toProfessionalHelp with error`() {
        assertFails {
            UserConverter().toProfessionalHelp(10)
        }
    }
}