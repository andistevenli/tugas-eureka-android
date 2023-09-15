package com.startup.tugas_5_eureka.di

import android.content.Context
import com.startup.tugas_5_eureka.firebase.FirebaseDataSource
import com.startup.tugas_5_eureka.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRepository(
        firebaseDataSource: FirebaseDataSource,
    ) = Repository(firebaseDataSource)

    @Provides
    @Singleton
    fun provideFirebaseDataSource(
        @ApplicationContext context: Context
    ) = FirebaseDataSource(context)
}