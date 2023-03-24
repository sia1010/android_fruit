package com.example.fruit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.fruit.databinding.ActivityLoginpageBinding;

public class LoginPage extends AppCompatActivity {

    ActivityLoginpageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        binding = ActivityLoginpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // this will be run when login button
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean correct_username = false;
                boolean correct_password = false;

                // open the database
                SQLiteDatabase mydatabase = openOrCreateDatabase("users", MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS users(username VARCHAR,password VARCHAR,gender VARCHAR,hobbyChess BOOLEAN,hobbyFootball BOOLEAN);");

                // select the data from the table 'users'
                Cursor selectSet = mydatabase.rawQuery("SELECT * FROM users", null);

                // loop through the table to find the username
                selectSet.moveToFirst();
                while(selectSet.moveToNext()){
                    if(binding.inpUsername.getText().toString().equals(selectSet.getString(0))){
                        correct_username = true;
                        // after finding the username, check the password
                        if(binding.inpPassword.getText().toString().equals(selectSet.getString(1))){
                            correct_password = true;
                        }
                        break;
                    }
                }

                if (correct_username && correct_password){
                    // if the username and password are correct, launch the mainpage
                    try {
                        Intent intent = new Intent(getApplicationContext(), MenuPage.class);
                        startActivity(intent);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    // if it is wrong, display an error message
                    if(!correct_password && !correct_username){
                        binding.txtMessage.setText(R.string.wrong_username_and_password_message);
                    }else if (!correct_username){
                        binding.txtMessage.setText(R.string.wrong_username_message);
                    }else if (!correct_password){
                        binding.txtMessage.setText(R.string.wrong_password_message);
                    }
                }
            }
        });

        // go to register page
        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), RegisterPage.class);
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}