package kg.geektech.rickandmortyapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.rickandmortyapp.R;
import kg.geektech.rickandmortyapp.databinding.ActivityMainBinding;
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}