package me.arnaumas;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarregarDades extends AppCompatActivity {
    private static final List<Video> elements = new ArrayList<>();
    public static RequestQueue queue = null;
    private String lliga = "";
    private String url;

    public static List<Video> getElements() {
        return elements;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = "https://www.googleapis.com/youtube/v3/playlistItems?playlistId=PLGUqK_J9ZbQ2UTlt6uGAQHkae7iytCBRc&key=AIzaSyCW-hP-GRQPDpvWjbktxYu6eOiyas5Fwx4&part=snippet&maxResults=50&nextPage=EAAaBlBUOkNESQ";

        setContentView(R.layout.carregar_dades);

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(elements);
        RecyclerView viewLlista = findViewById(R.id.viewLlista);

        viewLlista.setAdapter(adapter);
        Intent intent = getIntent();


        if (hiHaConnexio())
            CarregarDades.this.loadData(viewLlista, url);
        else
            Toast.makeText(getApplicationContext(), R.string.noInternet, Toast.LENGTH_SHORT).show();
    }

    /**
     * Comprova si el dispositiu té connexió
     * @return cert si en té i fals si no en té
     */
    private boolean hiHaConnexio() {
        boolean resultat = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        // Comprovem la versió del dispositiu Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    resultat = true;
                }
            }
        } else {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                resultat = true;
            } else {
                resultat = false;
            }
        }

        return resultat;
    }

    /**
     * Carega les dades que es troben a la url indicada
     * @param viewLlista el recyclerview que s'ha d'actualitzar
     * @param url Una url on hi ha un objecte JSON
     */
    private void loadData(RecyclerView viewLlista, String url) {
        if ( queue == null )
            queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            elements.clear();
                            JSONArray jsonArray = response.getJSONArray("items");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject snippet = jsonArray.getJSONObject(i).getJSONObject("snippet");
                                Video video = new Video(
                                        snippet.getString("title"),
                                        snippet.getJSONObject("thumbnails").getJSONObject("default").getString("url"),
                                        snippet.getJSONObject("resourceId").getString("videoId")
                                );
                                System.out.println(video.toString());
                                elements.add(video);

                            }
                            viewLlista.getAdapter().notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CarregarDades.this, "Error en obtenir dades", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);


    }

}