package com.example.mvvm_sample.di

import com.example.mvvm_sample.data.sample.repository.SampleRepository
import com.example.mvvm_sample.data.sample.repository.SampleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(sampleRepositoryImpl: SampleRepositoryImpl): SampleRepository
}