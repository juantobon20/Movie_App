package com.appinc.movieapp.di

import com.appinc.movieapp.URL
import com.appinc.movieapp.data.network.IApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Proveer Retrofit
    @Singleton
    @Provides
    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerQuoteApiClient(retrofit: Retrofit): IApiServices {
        return retrofit.create(IApiServices::class.java)
    }
}