package net.trices.apr05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etID, etPassword;
    //static String urlLogin = "http://sims.trices.net/mad/login_user.php";
    static String urlRegister = "http://sims.trices.net/mad/register_user.php?";

    static String urlLogin = "http://192.168.1.108/mad/login_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        AppData appData = new AppData(this);
        String[] user = new String[2];
        user = appData.getToken();
        if(!user[1].equalsIgnoreCase("")){
            Intent i = new Intent(MainActivity.this, Dashboard.class);
            startActivity(i);
            finish();
        }
    }


    public void userLogin(View v){
        final String id = etID.getText().toString();
        final String password = etPassword.getText().toString();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = urlLogin;
        Toast.makeText(MainActivity.this,
                "sending request...", Toast.LENGTH_SHORT).show();


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    //textView.setText("Token : "+ response.substring(0,500));
                    if(!response.equalsIgnoreCase("fail")){
                        Toast.makeText(MainActivity.this,
                                response, Toast.LENGTH_LONG).show();
                        AppData appData = new AppData(MainActivity.this);
                        appData.saveToken(id, response);
                        Intent i = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(i);
                        finish();
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Toast.makeText(MainActivity.this,
                        "No Response from Server", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", id);
                params.put("user_password", password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }





    public void userRegister(View v){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = urlRegister;
        url += "user_name=ATIAA&user_id=absdc@abc.com&user_password=asdf";


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Token : "+ response.substring(0,500));
                        Toast.makeText(MainActivity.this,
                                response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Toast.makeText(MainActivity.this,
                        "No Response", Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
