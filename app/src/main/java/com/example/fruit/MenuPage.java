package com.example.fruit;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.fruit.databinding.*;

public class MenuPage extends AppCompatActivity {

    ActivityMenupageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menupage);

        binding = ActivityMenupageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBtnRick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}