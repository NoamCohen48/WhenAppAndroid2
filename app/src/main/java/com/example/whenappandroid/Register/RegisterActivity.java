package com.example.whenappandroid.Register;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.whenappandroid.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private final int GALLERY_REQ_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.registerBtn.setOnClickListener(v -> {
            checkDataEntered();
        });

        ActivityResultLauncher<String[]> imagePicker =
                registerForActivityResult(new ActivityResultContracts.OpenDocument(), uri -> {
                    //contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    binding.image.setImageURI(uri);
                });

        binding.image.setOnClickListener(v -> {
            imagePicker.launch(new String[]{"image/*"});
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
        if(isEmpty(binding.usernameInputBox)) {
            binding.usernameInputBox.setError("you must enter user name");
        }
        if(isEmpty(binding.nicknameInputBox)) {
            binding.nicknameInputBox.setError("you must enter nick name!");
        }
        if(isEmpty(binding.passwordInputBox)) {
            binding.passwordInputBox.setError("you must enter password!");
        }
        if(isEmpty(binding.confirmPasswordInputBox)) {
            binding.confirmPasswordInputBox.setError("you must confirm your password!");
        }
        if(!isPassword(binding.passwordInputBox)) {
            binding.passwordInputBox.setError("Must contain at least one number and one uppercase" +
                    " and lowercase letter, and at least 8 or more characters");
        }
        if(!isSame(binding.passwordInputBox,binding.confirmPasswordInputBox)) {
            binding.confirmPasswordInputBox.setError("the password is not the same");
        }
    }
}