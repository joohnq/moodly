package com.joohnq.domain.di

import com.joohnq.domain.use_case.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val userDomainModule = module {
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