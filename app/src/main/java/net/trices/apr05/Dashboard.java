package net.trices.apr05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        AppData appData = new AppData(this);
        String[] user = new String[2];
        user = appData.getToken();
        if(user[1].equalsIgnoreCase("")){
            Intent i = new Intent(Dashboard.this, MainActivity.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,
                    user[1], Toast.LENGTH_SHORT).show();
        }
    }

    public void logout(View v){
        AppData appData = new AppData(this);
        appData.logout();
        Intent i = new Intent(Dashboard.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
