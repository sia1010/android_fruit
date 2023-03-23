package com.example.fruit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.*;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.fruit.databinding.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean correct_username = false;
                boolean correct_password = false;

                SQLiteDatabase mydatabase = openOrCreateDatabase("users", MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS users(username VARCHAR,password VARCHAR,gender VARCHAR,hobbyChess BOOLEAN,hobbyFootball BOOLEAN);");

                Cursor selectSet = mydatabase.rawQuery("SELECT * FROM users", null);
                selectSet.moveToFirst();

                while(selectSet.moveToNext()){
                    if(binding.inpUsername.getText().toString().equals(selectSet.getString(0))){
                        correct_username = true;
                        if(binding.inpPassword.getText().toString().equals(selectSet.getString(1))){
                            correct_password = true;
                        }
                        break;
                    }
                }

                if (correct_username && correct_password){
                    try {
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
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

        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}