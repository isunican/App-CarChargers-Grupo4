package es.unican.carchargers.common;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.Repositories;

/**
 * This class is the provider of IRepository implementations
 * Any time somebody demands an IRepository, Hilt will inject the implementation
 * provided by this module
 *
 * InstalllIn: this tells Hilt that this is available to every Activity (that are annotated with
 * @AndroidEntryPoint of course).
 * Alternatively I could use SingletonComponent.class, and it seems to work too
 */
@Module
@InstallIn(ActivityComponent.class)
public abstract class RepositoriesModule {

    @Provides
    public static IRepository provideRepository() {
        return Repositories.getReal();
    }

}
