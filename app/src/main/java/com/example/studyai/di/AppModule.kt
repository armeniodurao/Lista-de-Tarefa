package com.example.studyai.di

import android.content.Context
import androidx.room.Room
import com.example.studyai.data.StudyDao
import com.example.studyai.data.StudyDatabase
import com.example.studyai.data.StudyRepository
import com.example.studyai.data.StudyRepositoryImpl
import com.example.studyai.planner.AiPlanner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Provides
	@Singleton
	fun provideDatabase(@ApplicationContext context: Context): StudyDatabase =
		Room.databaseBuilder(context, StudyDatabase::class.java, "study_ai.db").build()

	@Provides
	fun provideDao(db: StudyDatabase): StudyDao = db.studyDao()

	@Provides
	@Singleton
	fun provideRepository(dao: StudyDao): StudyRepository = StudyRepositoryImpl(dao)

	@Provides
	@Singleton
	fun provideAiPlanner(): AiPlanner = AiPlanner()
}