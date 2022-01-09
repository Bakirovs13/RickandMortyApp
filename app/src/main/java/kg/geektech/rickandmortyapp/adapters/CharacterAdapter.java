package kg.geektech.rickandmortyapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.databinding.ItemCharacterBinding;
import kg.geektech.rickandmortyapp.interfaces.OnItemClick;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private List<Character> charactersList = new ArrayList<>();
    private OnItemClick onItemClickListener;


    public void setOnItemClick(OnItemClick onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setCharactersList(List<Character> charactersList) {
        this.charactersList = charactersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()),
        parent,false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(charactersList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onItemClickListener.onCLick(charactersList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;
        public ViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }



        public void onBind(Character character) {
            binding.nameTv.setText(character.getName());
            binding.statusTv.setText(character.getStatus());
            binding.speciesTv.setText( character.getSpecies());
            Glide.with(binding.getRoot())
                    .load(character.getImage())
                    .centerCrop()
                    .into(binding.characterIv);

        }
    }
}
