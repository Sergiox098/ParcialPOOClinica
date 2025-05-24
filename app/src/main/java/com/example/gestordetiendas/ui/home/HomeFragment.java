package com.example.gestordetiendas.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestordetiendas.FirebaseDB;
import com.example.gestordetiendas.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //FIREBASE

        FirebaseDB firebaseDB = new FirebaseDB();
        firebaseDB.initDatabase();

        binding.buttonWrite.setOnClickListener(v -> firebaseDB.writeDatabase());
        binding.buttonRead.setOnClickListener(v -> firebaseDB.readDatabase());
        binding.buttonUpdate.setOnClickListener(v -> firebaseDB.updateDatabase());
        binding.buttonDelete.setOnClickListener(v -> firebaseDB.deleteDatabase());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}