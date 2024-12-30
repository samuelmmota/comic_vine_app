package s.m.mota.comicvineandroidnativeapp.di

import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.ApiService
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.characters.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.issues.IssuesRepository
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.volumes.VolumesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideCharacterRepository(
        apiService: ApiService,
    ): CharactersRepository {
        return CharactersRepository(
            apiService
        )
    }

    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideIssuesRepository(
        apiService: ApiService,
    ): IssuesRepository {
        return IssuesRepository(
            apiService
        )
    }

    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideVolumesRepository(
        apiService: ApiService,
    ): VolumesRepository {
        return VolumesRepository(
            apiService
        )
    }
}