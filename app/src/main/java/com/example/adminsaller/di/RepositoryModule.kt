package com.example.adminsaller.di

import com.example.adminsaller.data.repository.AppRepositoryImpl
import com.example.adminsaller.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @[Binds Singleton]
    fun bindRepo(impl: AppRepositoryImpl): AppRepository
}