package kg.geektech.rickandmortyapp.ui.fragments.characters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.rickandmortyapp.adapters.CharacterPageListAdapter;
import kg.geektech.rickandmortyapp.common.Resource;
import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;
import kg.geektech.rickandmortyapp.data.remote.pagging.CharactersDataSourceFactory;
import kg.geektech.rickandmortyapp.data.remote.pagging.CharactersStorage;
import kg.geektech.rickandmortyapp.data.repository.MainRepo;

@HiltViewModel
public class CharacterViewModel extends ViewModel {
    private MainRepo repo;
    private CharacterPageListAdapter pageAdapter;
    public LiveData<PagedList<Character>> pagedLiveData;
    public LiveData<Resource<RickAndMortyResponse<Character>>> liveData;
    private CharactersDataSourceFactory sourceFactory;


    PagedList.Config config = (new PagedList.Config.Builder())
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .build();



    @Inject
    public CharacterViewModel(MainRepo repo, CharactersStorage storage) {
        sourceFactory = new CharactersDataSourceFactory(storage);
        this.repo = repo;
        pagedLiveData = new LivePagedListBuilder<>(sourceFactory,config).build();
    }



    public void getCharacters(){
        liveData = repo.getCharacters();
    }

}
