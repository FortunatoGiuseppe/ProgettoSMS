package com.example.mamange;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginTabFragment extends Fragment {
    TextView email;
    TextView pass;
    TextView forgetPass;
    Button login;
    private FirebaseAuth fAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetPass = root.findViewById(R.id.forget_pass);
        login = root.findViewById(R.id.loginBtn);
        fAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
        return root;
    }

    public void loginUser(){
        String emailStringa = email.getText().toString().trim();
        String passStringa = pass.getText().toString().trim();

        if(emailStringa.isEmpty()){
            email.setError("email is required");
            email.requestFocus();
            return;
        }
        if(passStringa.isEmpty()){
            pass.setError("password is required");
            pass.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailStringa).matches()){
            email.setError("Please provide valid email!");
            email.requestFocus();
            return;
        }
        if(passStringa.length() < 6){
            pass.setError("Enter at least six character");
            pass.requestFocus();
            return;
        }

        fAuth.signInWithEmailAndPassword(emailStringa,passStringa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(),"Logged in successfully!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),MainActivity.class));
                } else {
                    Toast.makeText(getActivity(),"Failed to login",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void resetPassword(){
        EditText resetEmail = new EditText(getView().getContext());
        AlertDialog.Builder passResetDialog = new AlertDialog.Builder(getView().getContext());
        passResetDialog.setTitle("Reset Password?");
        passResetDialog.setMessage("Enter your email to recived reset link");
        passResetDialog.setView(resetEmail);

        passResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String mail = resetEmail.getText().toString();
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(),"Reset Link Sent To Your Email",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Error! Reset Link is Not Sent"+ e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        passResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        passResetDialog.create().show();
    }
}
