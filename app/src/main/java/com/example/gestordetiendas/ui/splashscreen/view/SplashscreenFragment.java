package com.example.gestordetiendas.ui.splashscreen.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gestordetiendas.databinding.FragmentHomeBinding;
import com.example.gestordetiendas.databinding.FragmentSplashscreenBinding;
import com.example.gestordetiendas.R;
import com.example.gestordetiendas.ui.home.HomeViewModel;
import com.example.gestordetiendas.ui.splashscreen.viewmodel.SplashscreenViewModel;

public class SplashscreenFragment extends Fragment {

    private SplashscreenViewModel mViewModel;
    private FragmentSplashscreenBinding binding;

    public static SplashscreenFragment newInstance() {
        return new SplashscreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSplashscreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SplashscreenViewModel viewModel = new ViewModelProvider(this).get(SplashscreenViewModel.class);
        NavController navController = NavHostFragment.findNavController(this);
        binding.splashcreenButtonLogin.setOnClickListener(v -> navController.navigate(
                R.id.action_splashscreenFragment_to_loginFragment));
        binding.splashcreenButtonRegister.setOnClickListener(v -> navController.navigate(
                R.id.action_splashscreenFragment_to_registerFragment));
    }
     public void onDestroyView() {
        super.onDestroyView();
        binding = null;
     }

}