package com.word10.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Starter extends AppCompatActivity {

    private ImageView logoImageView;
    private TextView descriptionText;
    private TextView subDescriptionText;
    private Button registerButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        logoImageView = findViewById(R.id.logoImage);
        descriptionText = findViewById(R.id.descriptionText);
        subDescriptionText = findViewById(R.id.subDescriptionText);
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Starter.this, "Redirecting to Register screen", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Starter.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
