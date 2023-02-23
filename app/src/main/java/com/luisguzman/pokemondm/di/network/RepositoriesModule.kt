package com.luisguzman.pokemondm.di.network

import com.google.firebase.auth.FirebaseAuth
import com.luisguzman.pokemondm.data.network.api.HomeApiService
import com.luisguzman.pokemondm.data.network.source.AuthDataSource
import com.luisguzman.pokemondm.domain.auth.AuthRepository
import com.luisguzman.pokemondm.domain.auth.AuthRepositoryImpl
import com.luisguzman.pokemondm.domain.main.HomeRepository
import com.luisguzman.pokemondm.domain.main.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

 @Provides
 fun provideAuthRepo(dataSource: AuthDataSource) : AuthRepository = AuthRepositoryImpl(dataSource)

 @Provides
 fun provideHomeRepository(api: HomeApiService) : HomeRepository = HomeRepositoryImpl(api)

}