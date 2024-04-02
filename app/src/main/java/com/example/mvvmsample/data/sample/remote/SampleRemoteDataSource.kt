package com.example.mvvmsample.data.sample.remote

import javax.inject.Inject

@Suppress("UnusedPrivateProperty")
class SampleRemoteDataSource
@Inject
constructor(
    private val sampleService: SampleService
)
