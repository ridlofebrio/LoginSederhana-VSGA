package com.example.dtsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class WelcomeBackActivity extends AppCompatActivity {
    private SharedPreferences sharedPrefs;

    private static final String DUMMY_USERNAME = "dtskominfo";
    private static final String DUMMY_PASSWORD = "poliwangi";

    private static final String USERNAME_KEY = "key_username";
    private static final String KEEP_LOGIN_KEY = "key_keep_login";

    // Komponen
    private EditText edtUsername;
    private EditText edtPassword;
    private CheckBox chkRememberUsername;
    private CheckBox chkKeepLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_back);

        this.sharedPrefs = this.getSharedPreferences
                ("dtsapp_sharedprefs", Context.MODE_PRIVATE);

        this.initComponents();
        this.autoLogin();
        this.saveUsername();
    }

    private void initComponents()
    {
        this.edtUsername = this.findViewById(R.id.edt_username);
        this.edtPassword = this.findViewById(R.id.edt_password);
        this.chkRememberUsername = this.findViewById(R.id.chk_remember_username);
        this.chkKeepLogin = this.findViewById(R.id.chk_keep_login);
    }

    public void onTxvForgotPassword_Click(View view)
    {
        Intent i = new Intent(WelcomeBackActivity.this, ForgotPasswordActivity.class);
        startActivity(i);
    }

    public void onBtnLogin_Click(View view)
    {
        boolean valid = this.validateCredential();

        if (valid) {
            Intent i = new Intent(WelcomeBackActivity.this, HomeActivity.class);
            startActivity(i);

            this.saveUsername();
            this.makeAutoLogin();
        } else {
            Toast.makeText(this,"Invalid username or password",Toast.LENGTH_SHORT).show();
        }

    }

    public void onBtnRegister_Click(View view)
    {
        Intent i = new Intent(WelcomeBackActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    private void saveUsername()
    {
        SharedPreferences.Editor editor = this.sharedPrefs.edit();

        if(this.chkRememberUsername.isChecked())
            editor.putString(USERNAME_KEY, this.edtUsername.getText().toString());
        else
            editor.remove(USERNAME_KEY);

        editor.apply();
    }

    private void loadSavedUsername()
    {
        String savedUsername = this.sharedPrefs.getString(USERNAME_KEY, null);

        if(savedUsername != null)
        {
            this.edtUsername.setText(savedUsername);

            this.chkRememberUsername.setChecked(true);
        }
    }


    private void makeAutoLogin()
    {
        SharedPreferences.Editor editor = this.sharedPrefs.edit();
        if(this.chkKeepLogin.isChecked())
            editor.putBoolean(KEEP_LOGIN_KEY, true);
        else
            editor.remove(KEEP_LOGIN_KEY);

        editor.apply();
    }

    private void autoLogin() {
        boolean keepLoggedIn = this.sharedPrefs.getBoolean(KEEP_LOGIN_KEY, false);

        if (keepLoggedIn) {
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateCredential()
    {
        String currentUsername = this.edtUsername.getText().toString();
        String currentPassword = this.edtPassword.getText().toString();

        return (Objects.equals(currentUsername, DUMMY_USERNAME)
                && Objects.equals(currentPassword, DUMMY_PASSWORD));

}
}
