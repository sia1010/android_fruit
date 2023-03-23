package com.example.fruit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.fruit.databinding.ActivityMain2Binding;
import com.example.fruit.databinding.ActivityMain3Binding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> usernameList = new ArrayList<>();
                boolean hasDuplicateUsername = false;

                SQLiteDatabase mydatabase = openOrCreateDatabase("users", MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS users(username VARCHAR,password VARCHAR,gender VARCHAR,hobbyChess BOOLEAN,hobbyFootball BOOLEAN);");

                Cursor selectSet = mydatabase.rawQuery("SELECT * FROM users", null);
                selectSet.moveToFirst();

                while(selectSet.moveToNext()){
                    usernameList.add(selectSet.getString(0));
                }

                for (String username : usernameList){
                    if (binding.inpUsername.getText().toString().equals(username)){
                        hasDuplicateUsername = true;
                    }
                }

                if(binding.inpUsername.getText().toString().equals("")
                        || binding.inpPassword.getText().toString().equals("")
                        || binding.inpConfirmPassword.getText().toString().equals("")
                        || !(binding.rdbFemale.isChecked() || binding.rdbMale.isChecked())
                        || !(binding.chkChess.isChecked() || binding.chkFootball.isChecked())) {
                    binding.txtError.setText(R.string.empty_info);
                    binding.txtError.setVisibility(View.VISIBLE);
                }else if(!binding.inpPassword.getText().toString().equals(binding.inpConfirmPassword.getText().toString())){
                    binding.txtError.setText(R.string.password_different);
                    binding.txtError.setVisibility(View.VISIBLE);
                }else if(hasDuplicateUsername){
                    binding.txtError.setText(R.string.username_exists);
                    binding.txtError.setVisibility(View.VISIBLE);
                }else{
                    String gender = "";
                    if (binding.rdbMale.isChecked()){
                        gender = "Male";
                    } else if (binding.rdbFemale.isChecked()){
                        gender = "Female";
                    }
                    mydatabase.execSQL("INSERT INTO users VALUES('"
                            + binding.inpUsername.getText().toString() + "','"
                            + binding.inpPassword.getText().toString() + "','"
                            + gender + "','"
                            + binding.chkChess.isChecked() + "','"
                            + binding.chkFootball.isChecked() + "')");

                    binding.txtError.setText(R.string.successfully_registered);
                    binding.txtError.setTextColor(getResources().getColor(R.color.teal_700));
                    binding.txtError.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}