package com.example.adminsaller.di

import com.example.adminsaller.presentor.AddScreen.SellerAddDirection
import com.example.adminsaller.presentor.AddScreen.SellerAddDirectionImpl
import com.example.adminsaller.presentor.homeScreen.HomeDirection
import com.example.adminsaller.presentor.homeScreen.HomeDirectionImpl
import com.example.adminsaller.presentor.splash.SplashDirection
import com.example.adminsaller.presentor.splash.SplashDirectionImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {

    @[Binds Singleton]
    fun bindSplashDirection(impl: SplashDirectionImpl): SplashDirection

    @[Binds Singleton]
    fun bindHomeDirection(impl: HomeDirectionImpl): HomeDirection

    @[Binds Singleton]
    fun bindAddSellerDirection(impl: SellerAddDirectionImpl): SellerAddDirection


}