package kg.geektech.rickandmortyapp.data.remote.pagging;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import javax.inject.Inject;

import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;
import kg.geektech.rickandmortyapp.data.repository.MainRepo;

public class CharacterDataSource  extends PageKeyedDataSource<Integer, Character> {

    private CharactersStorage storage;


    @Inject
    public CharacterDataSource(CharactersStorage storage) {
        this.storage = storage;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Character> callback) {
       storage.getCharactersByPage(0, new OnCharactersReadyCallback() {
           @Override
           public void onReady(RickAndMortyResponse<Character> response) {
               if (response!= null){
                   List<Character>  result = response.getResults();
                   String[] splitedNextPageUrl =
                           response.getInfo().getNext().split("=");
                   Integer nextPage= Integer.parseInt(splitedNextPageUrl[1]);
                   callback.onResult(result,null,nextPage);
               }
           }
       });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Character> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Character> callback) {
        storage.getCharactersByPage(params.key, new OnCharactersReadyCallback() {
            @Override
            public void onReady(RickAndMortyResponse<Character> response) {
                List<Character>  result = response.getResults();
                String[] splitedNextPageUrl =
                        response.getInfo().getNext().split("=");
                Integer nextPage= Integer.parseInt(splitedNextPageUrl[1]);
                callback.onResult(result,nextPage);
            }
        });
    }
}
