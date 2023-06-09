package com.example.testtaskaztekky

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DI {

    @Binds
    fun bindsRate(rateSlot: RateSlot): RateSlotInterface
}