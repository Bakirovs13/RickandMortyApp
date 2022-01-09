package kg.geektech.rickandmortyapp.data.remote;

import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RickandMortyAPi {

    @GET("character")
    Call<RickAndMortyResponse<Character>> getCharacters();


    @GET("character/{id}")
    Call<Character> getCharacter(
            @Path("id")int id
    );

    @GET("character")
    Call<RickAndMortyResponse<Character>> getCharactersByPage(

            @Query("page") int page
    );

}
