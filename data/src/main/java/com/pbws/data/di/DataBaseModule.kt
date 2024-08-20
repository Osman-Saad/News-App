package com.pbws.data.di

import android.content.Context
import androidx.room.Room
import com.pbws.data.constant.Constant
import com.pbws.data.local.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext  context: Context
    ):NewsDataBase {
        return Room.databaseBuilder(
            context,
            NewsDataBase::class.java,
            Constant.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSourcesDao(dataBase: NewsDataBase) = dataBase.getSourcesDao()

    @Provides
    @Singleton
    fun provideNewsDao(dataBase: NewsDataBase) = dataBase.getNewsDao()

}