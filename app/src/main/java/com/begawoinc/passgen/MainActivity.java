package com.begawoinc.passgen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.interfaces.ECPrivateKey;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText text_lengthOfPassword;
    TextView showPassword;
    Button generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_lengthOfPassword = findViewById(R.id.input_lengthOfPassword);
        showPassword = findViewById(R.id.text_showPassword);
        generate = findViewById(R.id.btn_generatePassword);


        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String length_text = text_lengthOfPassword.getText().toString();
                final int length = Integer.parseInt(length_text);

                generatePassword(length);
            }
        });

    }

//  method to generate random password
    private void generatePassword(int length){
        
        Random random = new Random();
//		we are defining characters which we required
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String password = "";

//	    combining all characters
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;

//	    creating array to store the random characters
        char[] passwordarray = new char[length];

        if (length >= 4) {
//		    storing first 4 values externally because a strong password need combination of all characters
            passwordarray[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
            passwordarray[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
            passwordarray[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
            passwordarray[3] = numbers.charAt(random.nextInt(numbers.length()));

//		    storing rest of the characters
            for(int i = 4; i< length ; i++) {
                passwordarray[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
            }
            for (int i = 0; i < length; i++) {
                password += passwordarray[i];
            }

        } else if (length > 0 && length < 4) {
//		    storing rest of the characters
            for(int i = 0; i< length ; i++) {
                passwordarray[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
            }
            for (int i = 0; i < length; i++) {
                password += passwordarray[i];
            }

        } else {
            Toast.makeText(this, "Please enter positive integer which is more than 0", Toast.LENGTH_SHORT).show();
        }

        showPassword.setText(password);

    }

//  onClickListner for Image View
    public void copytext(View view) {
        copytextMethod();
    }

//    method to copy generated password
    public  void copytextMethod(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String passwordText = showPassword.getText().toString();
        ClipData clipData = ClipData.newPlainText("password", passwordText);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }
}