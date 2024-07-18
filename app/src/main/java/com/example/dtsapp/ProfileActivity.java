package com.example.dtsapp;

import static com.example.dtsapp.HomeActivity.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dtsapp.User.AppDbProvider;
import com.example.dtsapp.User.User;
import com.example.dtsapp.User.UserDao;


public class ProfileActivity extends AppCompatActivity
{
    private static final String KEEP_LOGIN_KEY = "key_keep_login";
    private SharedPreferences sharedPrefs;

    private User currentUser;
    // Komponen
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtEmail;
    private EditText edtPhoneNumber;
    private Button btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.loadData();
        this.initComponents();
    }

    private void loadData()
    {
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();
        this.currentUser = daoUser.selectOne();
    }

    private void initComponents()
    {
        this.edtUsername = this.findViewById(R.id.edt_username);
        this.edtPassword = this.findViewById(R.id.edt_password);
        this.edtEmail = this.findViewById(R.id.edt_email);
        this.edtPhoneNumber = this.findViewById(R.id.edt_phone_number);
        this.btnSave = this.findViewById(R.id.btn_save);

        if (this.currentUser == null){
            this.btnSave.setEnabled(false);
            return;
        }

        this.edtUsername.setText(this.currentUser.username);
        this.edtPassword.setText(this.currentUser.password);
        this.edtEmail.setText(this.currentUser.email);
        this.edtPhoneNumber.setText(this.currentUser.phoneNumber);
    }

    public void onBtnSave_Click(View view)
    {
        this.syncData();
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();
        daoUser.update(this.currentUser);
        Toast.makeText(this, "Your data has been updated!", Toast.LENGTH_SHORT).show();
    }

    public void onTxvDeleteAccount_Click(View view)
    {
        this.syncData();
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();

        this.sharedPrefs = this.getSharedPreferences("dtsapp_sharedprefs", Context.MODE_PRIVATE);

        daoUser.delete(this.currentUser);

        SharedPreferences.Editor editor = this.sharedPrefs.edit();
        editor.remove(KEEP_LOGIN_KEY);
        editor.apply();

        Toast.makeText(this, "Your data has been deleted..", Toast.LENGTH_SHORT).show();


        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
        this.startActivity(i);

        this.finish();

    }

    private void syncData(){
        this.currentUser.password = this.edtPassword.getText().toString();
        this.currentUser.email = this.edtEmail.getText().toString();
        this.currentUser.phoneNumber = this.edtPhoneNumber.getText().toString();
    }
}
