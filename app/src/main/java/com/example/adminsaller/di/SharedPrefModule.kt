package com.example.adminsaller.di

import android.content.Context
import android.content.SharedPreferences
import com.example.adminsaller.data.myPref.MyPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefModule {
    @[Provides Singleton]
    fun preferenceProvider(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("myPref", Context.MODE_PRIVATE)


    @[Provides Singleton]
    fun sharedProvider(sharedPreferences: SharedPreferences): MyPref = MyPref(sharedPreferences)
}