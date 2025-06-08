package com.example.gestordetiendas.ui.register.viewmodel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.util.Patterns;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import java.util.Map;
import java.util.HashMap;

public class RegisterAuthVM extends ViewModel {

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public LiveData<Boolean> getIsLoading() { return isLoading; }
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getRegisterSuccess() { return registerSuccess; }

    public void resetLoginSuccess() {
        registerSuccess.setValue(false);
    }


    public void register(String userName, String email, String phone, String password, String confirmPassword) {
        if (!isValidEmail(email)) {
            errorMessage.setValue("Email inválido");
            return;
        }
        if (!isValidPassword(password)) {
            errorMessage.setValue("La contraseña debe tener al menos 8 caracteres");
            return;
        }
        if (!password.equals(confirmPassword)) {
            errorMessage.setValue("Las contraseñas no coinciden");
            return;
        }

        isLoading.setValue(true);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(true);
                    if (task.isSuccessful()) {
                        String uid = task.getResult().getUser().getUid();
                        DatabaseReference userRef = firebaseDatabase.getReference("Users").child(uid);
                        Map<String, Object> userData = new HashMap<>();
                        userData.put("Nombre de usuario", userName);
                        userData.put("Teléfono", phone);
                        userData.put("Correo electrónico", email);
                        userRef.setValue(userData).addOnCompleteListener(dbTask -> {
                            isLoading.setValue(false);
                            if (dbTask.isSuccessful()) {
                                registerSuccess.setValue(true);
                            } else {
                                errorMessage.setValue("Error al guardar los datos en la base de datos"+
                                        dbTask.getException().getMessage());
                            }
                        });
                    } else {
                        errorMessage.setValue("Error al registrar el usuario"+task.getException().getMessage());
                    }
                });
    }

    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password != null && password.trim().length() >= 8;
    }
}
