package com.example.motobratstvo.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.motobratstvo.MainActivity;
import com.example.motobratstvo.R;
import com.example.motobratstvo.ui.RegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment {

    private TextView emailText, passwordText, nameText, textRules;
    private ImageView avatar;
    private Button loginButton, logoutButton, registerButton;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /** INFLATE **/

    @Override public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        MainActivity mainActivity = (MainActivity)getActivity();
        assert mainActivity != null;
        if(mainActivity.isAuth) {
            view = inflater.inflate(R.layout.fragment_profile_auth, container, false);
            emailText = view.findViewById(R.id.textEmailAuth);
            textRules = view.findViewById(R.id.textRules);
            nameText = view.findViewById(R.id.textNameAuth);
            avatar = view.findViewById(R.id.imageAvatar);
            logoutButton = view.findViewById(R.id.logOut);

            avatar.setImageResource(R.drawable.icon);

        }
        else {
            view = inflater.inflate(R.layout.fragment_profile_not_auth, container, false);
            emailText = view.findViewById(R.id.editTextEmailAddress);
            passwordText = view.findViewById(R.id.editTextPassword);
            loginButton = view.findViewById(R.id.buttonLogin);
            registerButton = view.findViewById(R.id.buttonRegister);
        }
        return view;
    }

    @Override public void onStart() {
        super.onStart();
        MainActivity mainActivity = (MainActivity)getActivity();

        assert mainActivity != null;
        if(!mainActivity.isAuth) {

            registerButton.setOnClickListener(view -> {
                Intent intent = new Intent(mainActivity, RegistrationActivity.class);
                startActivity(intent);
            });

            loginButton.setOnClickListener(v -> {
                Log.d("BUTTOM TEST", emailText.getText().toString());
                Log.d("BUTTOM TEST", passwordText.getText().toString());

                mainActivity.email = emailText.getText().toString();
                mainActivity.password = passwordText.getText().toString();
                mainActivity.signIn(mainActivity.email, mainActivity.password);

                mainActivity.saveConf();

                Navigation.findNavController(v).navigate(R.id.navigation_feed);

            });
        }
        else {
            logoutButton.setOnClickListener(v -> {

                mainActivity.email = "null";
                mainActivity.password = "null";
                mainActivity.saveConf();

                FirebaseAuth.getInstance().signOut();
                mainActivity.isAuth = false;

                Navigation.findNavController(v).navigate(R.id.navigation_feed);
            });
        }
    }

    @Override public void onStop() {
        super.onStop();
    }

}
