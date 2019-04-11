package net.trices.apr05;

import android.content.Context;
import android.content.SharedPreferences;

public class AppData {

    SharedPreferences p;

    public AppData(Context context) {
        p = context.getSharedPreferences("app_login", Context.MODE_PRIVATE);
    }

    public void saveToken(String id, String token){
        SharedPreferences.Editor e = p.edit();
        e.putString("id", id);
        e.putString("token", token);
        e.commit();
    }

    public String[] getToken(){
        String[] data = new String[2];
        data[0] = p.getString("id", "");
        data[1] = p.getString("token", "");
        return data;
    }

    public void logout(){
        saveToken("", "");
    }
}
