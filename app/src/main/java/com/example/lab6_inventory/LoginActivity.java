package com.example.lab6_inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth fba;

    private String TAG = "GERADEGUB";
    private EditText usrMail;
    private EditText usrPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fba = FirebaseAuth.getInstance();

        usrMail = findViewById(R.id.username);
        usrPassword = findViewById(R.id.password);

    }

    public void loginOnClick(View view){
        String usr = usrMail.getText().toString();
        String password = usrPassword.getText().toString();
        if((usr != null && !usr.equals("")) & (password != null && !password.equals(""))) {
            fba.signInWithEmailAndPassword(usrMail.getText().toString(), usrPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signIngWithEmail: Success");
                                FirebaseUser user = fba.getCurrentUser();
                                Log.d(TAG, "signIngWithEmail: User was " + user.toString());
                                Toast.makeText(LoginActivity.this, "Autenticación exitosa",
                                        Toast.LENGTH_LONG).show();
                                gotoMainActivity();
                                //updateUI(user);
                            } else {
                                Log.w(TAG, "signInWithEmail: failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Falló la autenticación",
                                        Toast.LENGTH_LONG).show();
                                //updateUI(null);
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Ha faltado llenar alguno de los espacios", Toast.LENGTH_LONG).show();
        }
    }

    public void registerNewUserOnClick(View view) {
        fba.createUserWithEmailAndPassword(usrMail.getText().toString(), usrPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = fba.getCurrentUser();
                            Log.d(TAG, "createUserWithEmail: The user registered as " + user.toString());
                            Toast.makeText(LoginActivity.this, "Se ha creado el usuario", Toast.LENGTH_LONG);
                            gotoMainActivity();
                        } else {
                            //if sign in fails
                            Log.w(TAG, "createUserWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "fallo el registro", Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = fba.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(this, "Logueado como: " + currentUser.getEmail(), Toast.LENGTH_LONG);
            gotoMainActivity();
        }
    }

    private void gotoMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
