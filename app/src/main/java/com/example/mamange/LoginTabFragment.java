package com.example.mamange;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginTabFragment extends Fragment {
    TextView email;
    TextView pass;
    TextView forgetPass;
    Button login;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetPass = root.findViewById(R.id.forget_pass);
        login = root.findViewById(R.id.button);
        return root;
    }

    public void LoginUser(){
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
    }
}
