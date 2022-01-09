package kg.geektech.rickandmortyapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.databinding.ItemCharacterBinding;
import kg.geektech.rickandmortyapp.interfaces.OnItemClick;

public class CharacterPageListAdapter extends PagedListAdapter<Character,CharacterPageListAdapter.ViewHolder> {

    private OnItemClick onItemClickListener;



    public void setOnItemClick(OnItemClick onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public CharacterPageListAdapter(@NonNull DiffUtil.ItemCallback<Character> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    onItemClickListener.onCLick(holder.getAdapterPosition());
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCharacterBinding binding;
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
