package platformpbp.uajy.login_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText username, password;
    private Button login;
    String EmailPattern = "((.+)@(.+))";
    String PasswordPattern = "((?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,})";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usermasuk, passmasuk;

                usermasuk = username.getText().toString();
                passmasuk = password.getText().toString();

                formValidation(usermasuk, passmasuk);
                loginUser(usermasuk, passmasuk);
            }
        });
    }

    public boolean formValidation(String edtusername, String edtpassword){
        if(TextUtils.isEmpty(edtusername)){
            username.setError("This Field Can't Be Empty!");
            return true;
        }

        if(TextUtils.isEmpty(edtpassword)){
            password.setError("This Field Can't Be Empty!");
            return true;
        }

        if(edtusername.length()<10 || edtusername.length()>15) {
            username.setError("Minimum 10 Maximum 15");
            return true;
        }

        if(!Pattern.matches(EmailPattern, edtusername)){
            username.setError("Invalid Email");
            return true;
        }

        if(!Pattern.matches(PasswordPattern, edtpassword)){
            password.setError("Password Length min 8 max 10, Contains Number, Symbol, Uppercase, Lowercase e.g. Testing193!");
            return true;
        }

        return false;
    }

    public void loginUser(String edtusername, String edtpass){
        if(formValidation(edtusername, edtpass)){
            return;
        }
        else{
            SharedPreferences mSettings = getApplication().getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString("nama_user_login", edtusername);
            editor.apply();
            System.out.println(editor);

            Intent i = new Intent(MainActivity.this, LogoutActivity.class);
            startActivity(i);
        }

    }

    public void onBackPressed() {
        finish();
        exitFromApp();
    }

    private void exitFromApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}