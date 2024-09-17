package com.vocab10.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vocab10.android.services.IAuthService;
import com.vocab10.android.network.LoginRequest;
import com.vocab10.android.network.LoginResponse;
import com.vocab10.android.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    private IAuthService authService;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView logoImageView = findViewById(R.id.imageView);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        TextView signupText = findViewById(R.id.signupText);

        Retrofit retrofit = RetrofitClient.getClient("https://your-api-url.com/"); // Use your backend base URL
        authService = retrofit.create(IAuthService.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(username, password);
                }
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Redirecting to Signup", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        // Make the API call
        authService.loginUser(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    Toast.makeText(Login.this, "Login successful! Token: " + token, Toast.LENGTH_LONG).show();

                    // Save the token using SharedPreferences (for subsequent API requests)
                    saveToken(token);
                } else {
                    Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Handle error (e.g., network issues)
                Toast.makeText(Login.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveToken(String token) {
        // Save the token in SharedPreferences for future use
        getSharedPreferences("auth_prefs", MODE_PRIVATE)
                .edit()
                .putString("auth_token", token)
                .apply();
    }
}
