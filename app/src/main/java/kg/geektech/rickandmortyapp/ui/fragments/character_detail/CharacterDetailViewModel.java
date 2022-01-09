package kg.geektech.rickandmortyapp.ui.fragments.character_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.rickandmortyapp.common.Resource;
import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.repository.MainRepo;

@HiltViewModel
public class CharacterDetailViewModel extends ViewModel {


    private MainRepo mainRepo;
    public LiveData<Resource<Character>> liveData;

    @Inject
    public CharacterDetailViewModel(MainRepo repo) {
        this.mainRepo = repo;
    }

    public void getCharacterByID(int id){
        liveData = mainRepo.getCharacterByID(id);
    }
}
