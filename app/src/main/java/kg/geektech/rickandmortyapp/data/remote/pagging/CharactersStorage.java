package kg.geektech.rickandmortyapp.data.remote.pagging;

import javax.inject.Inject;

import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;
import kg.geektech.rickandmortyapp.data.remote.RickandMortyAPi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersStorage {

    private RickandMortyAPi aPi;

    @Inject
    public CharactersStorage(RickandMortyAPi aPi) {
        this.aPi = aPi;
    }


    public  void getCharactersByPage(int page,OnCharactersReadyCallback callback){

        aPi.getCharactersByPage(page).enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                if (response.isSuccessful()&&response.body()!= null){
                    callback.onReady(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {

            }
        });
    }
}
