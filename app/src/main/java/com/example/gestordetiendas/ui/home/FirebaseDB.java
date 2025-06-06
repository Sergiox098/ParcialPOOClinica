package com.example.gestordetiendas.ui.home;
import androidx.appcompat.app.AlertDialog;
import android.content.Context;

import android.util.Log;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDB {

    private DatabaseReference refusers;

    public void initDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refusers = database.getReference("Users");
    }
    public void writeDatabase(Context context, String Store, String Address, String Product, String Cost){
        initDatabase();
        DBTestProduct ProductStore = new DBTestProduct
                (Store,
                 Address,
                 Product,
                 Cost
                        );
        refusers.push().setValue(ProductStore);
        new AlertDialog.Builder(context)
                .setTitle("Create")
                .setMessage("Se ha creado la tienda")
                .setPositiveButton("Aceptar", null)
                .show();
    }
    public void readDatabase(Context context, String x){
        initDatabase();
        refusers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    DBTestProduct user = child.getValue(DBTestProduct.class);
                    if(user != null && x.equals(user.getStoreName())) {
                        new AlertDialog.Builder(context)
                                .setTitle("Read")
                                .setMessage("Nombre: " + user.getStoreName() +
                                        "\nDirección: " + user.getAddress()+
                                        "\nProducto: " + user.getProductName() +
                                        "\nPrecio: " + user.getCost())
                                .setPositiveButton("Aceptar", null)
                                .show();
                    }
                    else{
                        Log.d("USERS","Base de datos vacía");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("USERS", "Error al leer");
            }
        });
    }
    public void updateDatabase(Context context, String store, String product){
        initDatabase();
        refusers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String userId = child.getKey();
                    DBTestProduct user = child.getValue(DBTestProduct.class);
                    if(user != null && store.equals(user.getStoreName())) {
                        refusers.child(userId).child("productName").setValue(product);
                        new AlertDialog.Builder(context)
                                .setTitle("Update")
                                .setMessage("Sus datos han sido actualizados")
                                .setPositiveButton("Aceptar", null)
                                .show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("USERS", "Error al actualizar");
            }
        });
    }
    public void deleteDatabase(Context context, String store){
        initDatabase();
        refusers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String userId = child.getKey();
                    DBTestProduct user = child.getValue(DBTestProduct.class);

                    if (user != null && store.equals(user.getStoreName())) {
                        refusers.child(userId).removeValue();
                        new AlertDialog.Builder(context)
                                .setTitle("Delete")
                                .setMessage("Se ha eliminado la tienda")
                                .setPositiveButton("Aceptar", null)
                                .show();

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("USERS", "Error al eliminar");
            }
        });
    }
}