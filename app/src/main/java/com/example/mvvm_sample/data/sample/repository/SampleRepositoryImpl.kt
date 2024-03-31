package com.example.mvvm_sample.data.sample.repository

import com.example.mvvm_sample.data.sample.local.SampleLocalDataSource
import com.example.mvvm_sample.data.sample.remote.SampleRemoteDataSource
import javax.inject.Inject

/**
 * Sample repository
 *
 * @constructor
 *
 * @param localDataSource
 * @param remoteDataSource
 */
class SampleRepositoryImpl @Inject constructor(
    localDataSource: SampleLocalDataSource,
    remoteDataSource: SampleRemoteDataSource
) : SampleRepository