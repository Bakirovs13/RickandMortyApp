package kg.geektech.rickandmortyapp.data.remote.pagging;

import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.data.models.RickAndMortyResponse;

public interface OnCharactersReadyCallback {

    void onReady(RickAndMortyResponse<Character> response);
}
