package com.example.gestordetiendas.ui.login.view;

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
import com.example.gestordetiendas.databinding.FragmentLoginBinding;
import com.example.gestordetiendas.R;
import com.example.gestordetiendas.ui.login.viewmodel.LoginViewModel;
import android.widget.Toast;
import android.content.Intent;
import com.example.gestordetiendas.MainActivity;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = NavHostFragment.findNavController(this);

        //NAVIGATION

        binding.loginButtonRegister.setOnClickListener(v -> navController.navigate(
                R.id.action_loginFragment_to_registerFragment));
        binding.loginButtonBack.setOnClickListener(v -> navController.navigate(
                R.id.action_loginFragment_to_splashscreenFragment));

        //AUTHENTICATION

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                binding.loginLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                binding.loginButtonLogin.setEnabled(!isLoading);
            }
        });

        loginViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMsg -> {
            if (errorMsg != null && !errorMsg.isEmpty()) {
                Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

        loginViewModel.getLoginSuccess().observe(getViewLifecycleOwner(), success -> {
            if (Boolean.TRUE.equals(success)) {
                Toast.makeText(getContext(), "Login exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        binding.loginButtonLogin.setOnClickListener(v -> {
            String email = binding.loginEditTextEmail.getText().toString().trim();
            String password = binding.loginEditTextPassword.getText().toString();
            loginViewModel.login(email, password);
        });


    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}