package com.example.mamange;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {
    TextView email;
    TextView pass;
    TextView passconf;
    Button signup;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        passconf = root.findViewById(R.id.passconf);
        signup = root.findViewById(R.id.registerBtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        return root;
    }

    public void registerUser(){

        String emailStringa = email.getText().toString().trim();
        String passStringa = pass.getText().toString().trim();
        String passControlStringa = passconf.getText().toString().trim();

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
        if(passControlStringa != passStringa){
            passconf.setError("Enter the same password");
            passconf.requestFocus();
            return;
        }
       /* if(passStringa.contains()){

        }*/

        mAuth.createUserWithEmailAndPassword(emailStringa, passStringa)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(emailStringa);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"User has been registered successfully!",Toast.LENGTH_SHORT).show();
                                    } else
                                    {
                                        Toast.makeText(getActivity(),"Failed to register",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getActivity(),"Failed to register",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
