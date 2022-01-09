package kg.geektech.rickandmortyapp.data.models;

import java.util.List;

public class RickAndMortyResponse<T> {
    Info info;
    List<T> results;


    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
