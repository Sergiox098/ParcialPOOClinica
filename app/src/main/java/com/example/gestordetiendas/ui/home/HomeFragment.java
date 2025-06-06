package com.example.gestordetiendas.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestordetiendas.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.TextInDatos;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //FIREBASE

        FirebaseDB firebaseDB = new FirebaseDB();
        firebaseDB.initDatabase();



        binding.buttonWrite.setOnClickListener(v -> {
                String NombreTienda = binding.editTextNombreTienda.getText().toString().trim();
                String Direccion = binding.editTextDireccion.getText().toString().trim();
                String NombreProducto = binding.editTextNombreProducto.getText().toString().trim();
                String Precio = binding.editTextPrecio.getText().toString().trim();
                firebaseDB.writeDatabase(requireContext(), NombreTienda,Direccion,NombreProducto,Precio);});
        binding.buttonRead.setOnClickListener(v -> {
                String NombreTienda = binding.editTextNombreTienda.getText().toString().trim();
                String Direccion = binding.editTextDireccion.getText().toString().trim();
                String NombreProducto = binding.editTextNombreProducto.getText().toString().trim();
                String Precio = binding.editTextPrecio.getText().toString().trim();
                firebaseDB.readDatabase(requireContext(),NombreTienda);});
        binding.buttonUpdate.setOnClickListener(v -> {
                String NombreTienda = binding.editTextNombreTienda.getText().toString().trim();
                String Direccion = binding.editTextDireccion.getText().toString().trim();
                String NombreProducto = binding.editTextNombreProducto.getText().toString().trim();
                String Precio = binding.editTextPrecio.getText().toString().trim();
                firebaseDB.updateDatabase(requireContext(),NombreTienda,NombreProducto);});
        binding.buttonDelete.setOnClickListener(v -> {
            String NombreTienda = binding.editTextNombreTienda.getText().toString().trim();
            String Direccion = binding.editTextDireccion.getText().toString().trim();
            String NombreProducto = binding.editTextNombreProducto.getText().toString().trim();
            String Precio = binding.editTextPrecio.getText().toString().trim();
            firebaseDB.deleteDatabase(requireContext(),NombreTienda);});
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

