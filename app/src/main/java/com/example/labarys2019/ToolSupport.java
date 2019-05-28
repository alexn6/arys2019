package com.example.labarys2019;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ToolSupport {

    public ToolSupport(){}

    // vemos si la placa de red esta disponible
    public boolean isNetDisponible(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    // vemos si nos podemos conectar a internet
    public Boolean isOnlineNet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
