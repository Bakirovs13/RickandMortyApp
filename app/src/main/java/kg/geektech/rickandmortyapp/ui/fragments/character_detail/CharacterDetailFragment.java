package kg.geektech.rickandmortyapp.ui.fragments.character_detail;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.rickandmortyapp.common.Resource;
import kg.geektech.rickandmortyapp.data.models.Character;
import kg.geektech.rickandmortyapp.databinding.FragmentCharacterDetailBinding;


@AndroidEntryPoint
public class CharacterDetailFragment extends Fragment {

    FragmentCharacterDetailBinding binding;
    private CharacterDetailFragmentArgs args;
    private CharacterDetailViewModel viewModel;


    public CharacterDetailFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity())
                .get(CharacterDetailViewModel.class);
        args = CharacterDetailFragmentArgs.fromBundle(getArguments());  //init args
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater,container,false);
        viewModel.getCharacterByID(args.getCharacterID());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<Character>>() {
            @Override
            public void onChanged(Resource<Character> characterResource) {
               switch (characterResource.status){
                   case SUCCESS:{
                       Character character = characterResource.data;
                       binding.characterId.setText(character.getName());
                       Glide.with(binding.getRoot())
                               .load(character.getImage())
                               .centerCrop()
                               .into(binding.chIv);
                       onSuccess();
                       generateQR(args.getCharacterID());
                      break;
                   }
                   case ERROR:{
                       Log.e("TAG", "onChanged: "+characterResource.message );
                       onSuccess();
                       break;
                   }
                   case LOADING:{
                       onLoadingState();
                       break;
                   }
               }
            }
        });
    }

    private void onLoadingState(){
        binding.characterId.setVisibility(View.GONE);
        binding.chIv.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void onSuccess(){
        binding.characterId.setVisibility(View.VISIBLE);
        binding.chIv.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void generateQR(int id){
        try {
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap qrBitmap = encoder.encodeBitmap(String.valueOf(id)
                    , BarcodeFormat.QR_CODE,
                    200,
                    200);

            binding.qrIv.setImageBitmap(qrBitmap);

        }catch (Exception e){
            Log.e("TAG", "generateQR: "+e.getLocalizedMessage() );
        }
    }


}