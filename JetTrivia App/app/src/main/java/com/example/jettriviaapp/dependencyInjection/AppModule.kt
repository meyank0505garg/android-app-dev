package com.example.jettriviaapp.dependencyInjection

import androidx.compose.ui.unit.Constraints
import com.example.jettriviaapp.network.QuestionApi
import com.example.jettriviaapp.repository.QuestionRepository
import com.example.jettriviaapp.util.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideQuestionApi() : QuestionApi {
        return Retrofit.Builder()
            .baseUrl( Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)



    }

    @Singleton
    @Provides
    fun provideQuestionRepository(api:QuestionApi) : QuestionRepository = QuestionRepository(api)
}