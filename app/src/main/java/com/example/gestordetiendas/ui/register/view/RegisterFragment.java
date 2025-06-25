package com.example.gestordetiendas.ui.register.view;

import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.gestordetiendas.MainActivity;
import com.example.gestordetiendas.databinding.FragmentRegisterBinding;
import com.example.gestordetiendas.R;
import com.example.gestordetiendas.ui.register.viewmodel.RegisterAuthVM;
import android.util.Log;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RegisterAuthVM registerAuthVM = new ViewModelProvider(this).get(RegisterAuthVM.class);
        NavController navController = NavHostFragment.findNavController(this);
        binding.registerButtonBack.setOnClickListener(v -> navController.navigate(
                R.id.action_registerFragment_to_splashscreenFragment));
        binding.registerButtonLogin.setOnClickListener(v -> navController.navigate(
                R.id.action_registerFragment_to_loginFragment));

        //Authentication

        registerAuthVM.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                binding.registerLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                binding.registerButtonLogin.setEnabled(!isLoading);
            }
        });

        registerAuthVM.getErrorMessage().observe(getViewLifecycleOwner(), errorMsg -> {
            if (errorMsg != null && !errorMsg.isEmpty()) {
                Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

        registerAuthVM.getRegisterSuccess().observe(getViewLifecycleOwner(), success -> {
            if (Boolean.TRUE.equals(success)) {
                Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                Log.d("RegisterFragment", "Registro exitoso");
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        binding.registerButtonRegister.setOnClickListener(v -> {
            String userName = binding.registerEditTextUserName.getText().toString().trim();
            String phone = binding.registerEditTextPhone.getText().toString().trim();
            String email = binding.registerEditTextEmail.getText().toString().trim();
            String password = binding.registerEditTextPassword.getText().toString();
            String confirmPassword = binding.registerEditTextConfirmPassword.getText().toString();
            registerAuthVM.register(userName,email,phone,password,confirmPassword);

        });
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}