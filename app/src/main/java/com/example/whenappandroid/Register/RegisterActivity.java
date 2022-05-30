package com.example.whenappandroid.Register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.whenappandroid.R;

public class RegisterActivity extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;
    EditText userName;
    EditText nickName;
    EditText password;
    EditText confirmPassword;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.editTextTextPersonName);
        nickName = findViewById(R.id.editTextTextPersonNickName);
        password = findViewById(R.id.editTextTextPassword);
        confirmPassword = findViewById(R.id.editTextTextConfirmPassword);

        register = findViewById(R.id.RegisterBtn);

        register.setOnClickListener(v -> {
            checkDataEntered();
        });

        imgGallery = findViewById(R.id.IdProf);
        Button btnGallery = findViewById(R.id.UploadBtn);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //noinspection deprecation
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isPassword(EditText text){
        String t = text.getText().toString();
        //String regex = "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$";
        //return t.matches(regex);
        return true;
    }
    boolean isSame(EditText text1, EditText text2){
        String p = text1.getText().toString();
        String c = text2.getText().toString();
        return (p.equals(c));
    }
    void checkDataEntered() {
        if(isEmpty(userName)) {
            userName.setError("you must enter user name");
        }
        if(isEmpty(nickName)) {
            nickName.setError("you must enter nick name!");
        }
        if(isEmpty(password)) {
            password.setError("you must enter password!");
        }
        if(isEmpty(confirmPassword)) {
            confirmPassword.setError("you must confirm your password!");
        }
        if(!isPassword(password)) {
            password.setError("Must contain at least one number and one uppercase" +
                    " and lowercase letter, and at least 8 or more characters");
        }
        if(!isSame(password,confirmPassword)) {
            confirmPassword.setError("the password is not the same");
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK){
            if(requestCode == GALLERY_REQ_CODE){
                imgGallery.setImageURI(data.getData());
            }
        }
    }



}