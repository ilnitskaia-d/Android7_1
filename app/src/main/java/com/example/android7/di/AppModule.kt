package com.example.android7.di

import android.content.Context
import androidx.room.Room
import com.example.android7.data.api.CameraApi
import com.example.android7.data.database.ItemDatabase
import com.example.android7.data.database.dao.ItemDao
import com.example.android7.data.database.repositories.ItemRepositoryImp
import com.example.android7.domain.usecase.GetAllItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ItemDatabase::class.java, "item_db").build()

    @Provides
    fun provideItemRepository(dao: ItemDao, api: CameraApi) =
        ItemRepositoryImp(dao, api)

    @Provides
    fun provideItemDao(database: ItemDatabase) =
        database.getDao()

    @Provides
    fun provideGetAllItemsUseCase(repository: ItemRepositoryImp) =
        GetAllItemsUseCase(repository)


    @Provides
    fun provideCameraApi() : CameraApi = Retrofit.Builder().baseUrl("http://cars.cprogroup.ru/api/rubetek/cameras/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(CameraApi::class.java)


}