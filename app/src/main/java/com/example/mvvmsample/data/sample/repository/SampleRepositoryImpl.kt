package com.example.mvvmsample.data.sample.repository

import com.example.mvvmsample.data.sample.local.SampleLocalDataSource
import com.example.mvvmsample.data.sample.remote.SampleRemoteDataSource
import javax.inject.Inject

/**
 * Sample repository
 *
 * @constructor
 *
 * @param localDataSource
 * @param remoteDataSource
 */
@Suppress("UnusedPrivateProperty")
class SampleRepositoryImpl
@Inject
constructor(
    private val localDataSource: SampleLocalDataSource,
    private val remoteDataSource: SampleRemoteDataSource
) : SampleRepository
