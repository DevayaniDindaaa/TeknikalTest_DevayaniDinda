package platformpbp.uajy.login_form;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogoutActivity extends AppCompatActivity {
    private TextView salam, userlogin, jam;
    private Button logout;
    private String nama_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);

        salam = (TextView) findViewById(R.id.salam);
        userlogin = (TextView) findViewById(R.id.userlogin);
        jam = (TextView) findViewById(R.id.jam);
        logout = (Button) findViewById(R.id.logout);

        setDisplay();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogoutActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void setDisplay(){
        SharedPreferences mSettings = getSharedPreferences("Login", Context.MODE_PRIVATE);
        nama_user = mSettings.getString("nama_user_login", "user_tidak_terdeteksi");
        userlogin.setText(nama_user);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        String format = simpleDateFormat.format(new Date());
        jam.setText(format);

        if(format.contains("PM")){
            salam.setText("Selamat Siang");
        }
        else if(format.contains("AM")){
            salam.setText("Selamat Pagi");
        }
    }

    public void onBackPressed() {
        Toast.makeText(LogoutActivity.this, "You Have Logged On", Toast.LENGTH_SHORT).show();
    }
}
