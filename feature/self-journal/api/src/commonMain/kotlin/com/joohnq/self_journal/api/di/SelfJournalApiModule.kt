package com.joohnq.self_journal.api.di

import com.joohnq.self_journal.api.use_case.AddSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalByIdUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.UpdateSelfJournalsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val selfJournalApiModule =
    module {
        factoryOf(::AddSelfJournalsUseCase)
        factoryOf(::DeleteSelfJournalsUseCase)
        factoryOf(::GetSelfJournalsUseCase)
        factoryOf(::GetSelfJournalByIdUseCase)
        factoryOf(::UpdateSelfJournalsUseCase)
    }
