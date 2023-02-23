package com.luisguzman.pokemondm.di.network

import com.luisguzman.pokemondm.data.network.api.HomeApiService
import com.luisguzman.pokemondm.utils.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHomeApi(retrofit: Retrofit) : HomeApiService = retrofit.create(HomeApiService::class.java)

}