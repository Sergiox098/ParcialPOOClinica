package com.example.gestordetiendas.ui.login.viewmodel;

import android.util.Patterns;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public LiveData<Boolean> getIsLoading() { return isLoading; }
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getLoginSuccess() { return loginSuccess; }

    public void login(String email, String password) {
        if (!isValidEmail(email)) {
            errorMessage.setValue("Email inválido");
            return;
        }
        if (!isValidPassword(password)) {
            errorMessage.setValue("La contraseña debe tener al menos 8 caracteres");
            return;
        }
        isLoading.setValue(true);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        loginSuccess.setValue(true);
                    } else {
                        errorMessage.setValue(task.getException().getMessage());
                    }
                });
    }
    // Validacion de datos del usuario
    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPassword(String password) {
        return password != null && password.trim().length() >= 8;
    }
}