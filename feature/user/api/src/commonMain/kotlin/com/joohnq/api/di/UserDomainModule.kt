package com.joohnq.api.di

import com.joohnq.api.use_case.AddUserUseCase
import com.joohnq.api.use_case.GetUserUseCase
import com.joohnq.api.use_case.UpdateMedicationsSupplementsUseCase
import com.joohnq.api.use_case.UpdatePhysicalSymptomsUseCase
import com.joohnq.api.use_case.UpdateSoughtHelpUseCase
import com.joohnq.api.use_case.UpdateUserImageBitmapUseCase
import com.joohnq.api.use_case.UpdateUserImageDrawableUseCase
import com.joohnq.api.use_case.UpdateUserNameUseCase
import com.joohnq.api.use_case.UpdateUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val userDomainModule =
    module {
        factoryOf(::AddUserUseCase)
        factoryOf(::GetUserUseCase)
        factoryOf(::UpdateMedicationsSupplementsUseCase)
        factoryOf(::UpdatePhysicalSymptomsUseCase)
        factoryOf(::UpdateSoughtHelpUseCase)
        factoryOf(::UpdateUserNameUseCase)
        factoryOf(::UpdateUserUseCase)
        factoryOf(::UpdateUserImageBitmapUseCase)
        factoryOf(::UpdateUserImageDrawableUseCase)
    }
