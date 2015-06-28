package com.example.android.startup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.restapi.CreateUser;
import com.example.android.slidingtabscolors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment implements View.OnClickListener {

    private EditText firstNameET;
    private EditText lastNameET;
    private EditText emailET;
    private EditText usernameET;
    private EditText passwordET;
    private EditText confirmPasswordET;
    private Button createButton;

    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        firstNameET = (EditText) view.findViewById(R.id.firstNameEditText);
        lastNameET = (EditText) view.findViewById(R.id.lastNameEditText);
        emailET = (EditText) view.findViewById(R.id.emailEditText);
        usernameET = (EditText) view.findViewById(R.id.usernameEditText);
        passwordET = (EditText) view.findViewById(R.id.passwordEditText);
        confirmPasswordET = (EditText) view.findViewById(R.id.confirmPasswordEditText);
        createButton = (Button) view.findViewById(R.id.createButton);

        createButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String email = emailET.getText().toString();
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();

        final String TAG = "CreateFragment->onClick";
        Log.d(TAG, "firstName: " + firstName);
        Log.d(TAG, "lastName: " + lastName);
        Log.d(TAG, "email: " + email);
        Log.d(TAG, "username: " + username);
        Log.d(TAG, "password: " + password);
        Log.d(TAG, "confirmPassword: " + confirmPassword);

        CreateUser asyncTask = new CreateUser(this);
        asyncTask.execute(firstName, lastName, email, username, password);
    }

    public void receiveCreateUserResult(boolean success, String error)
    {
        final String TAG = "receiveCreateUserResult";

        if (success)
            Log.d(TAG, "Successfully created user! :D");
        else
            Log.d(TAG, "Failed to create user. error: " + error);
    }
}
