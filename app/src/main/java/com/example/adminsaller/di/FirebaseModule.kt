package com.example.adminsaller.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @[Provides Singleton]
    fun firestoreProvider(): FirebaseFirestore = Firebase.firestore





    @[Provides Singleton]
    fun storageProvider(): StorageReference = Firebase.storage.reference
}
