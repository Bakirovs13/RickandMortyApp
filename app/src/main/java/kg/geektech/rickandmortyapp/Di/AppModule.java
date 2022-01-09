package kg.geektech.rickandmortyapp.Di;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.rickandmortyapp.data.remote.RickandMortyAPi;
import kg.geektech.rickandmortyapp.data.remote.pagging.CharacterDataSource;
import kg.geektech.rickandmortyapp.data.remote.pagging.CharactersStorage;
import kg.geektech.rickandmortyapp.data.repository.MainRepo;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public  abstract class AppModule {


    @Provides
    public static RickandMortyAPi provideAPi(Retrofit retrofit){
        return retrofit.create(RickandMortyAPi.class);
    }


    @Provides
    public static MainRepo provideMainRepo(RickandMortyAPi aPi){
        return new MainRepo(aPi);
    }

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


    @Provides

    public static CharactersStorage provideCharactersStorage(RickandMortyAPi aPi){
        return new CharactersStorage(aPi);
    }


    @Provides
    public static CharacterDataSource provideDataSource(CharactersStorage storage){

        return new CharacterDataSource(storage);
    }

    @Provides
    public static OkHttpClient providesOkHttpClient(Interceptor loggingInterceptor){
       return new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

    }

    @Provides
    public static Interceptor provideLoggingInterceptor() {

        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }


}
