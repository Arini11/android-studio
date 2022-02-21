package me.arnaumas;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MostraTorneig extends AppCompatActivity {
    List<Video> elements;
    ImageView im1, imgEstrella;
    TextView tv1, tv2, tv3, tv4;
    private Video video = new Video();
    String tipusURL;

    public MostraTorneig(){}
    public MostraTorneig(List<Video> elements) {
        this.elements = elements;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_personatge);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        im1 = (ImageView) findViewById(R.id.imageView);
        imgEstrella = (ImageView) findViewById(R.id.imgEstrella);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);

        elements = CarregarDades.getElements();

        //Recuperem l'extra de les dades anteriors
        Intent intent = getIntent();
        final String key = intent.getStringExtra("key");

        for(Video v : elements){
            if(v.getId().equalsIgnoreCase(key)){
                video = v;
                break;
            }
        }

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        String torneig_fav = sharedPreferences.getString("torneig_fav", "");
        if(torneig_fav.equalsIgnoreCase(video.getId()))
            imgEstrella.setVisibility(View.VISIBLE);
        else
            imgEstrella.setVisibility(View.INVISIBLE);

        String url = "https://www.vidalibarraquer.net/android/EXAMEN/TENNIS/"+key+".json";
        System.out.println(url);
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Obtenim l'array que té per nom data
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("data");
                            //video.setVenue(jsonArray.getJSONObject(0).getString("venue"));
                            //video.setSurface(jsonArray.getJSONObject(0).getString("surface"));
                            //video.setPrice(jsonArray.getJSONObject(0).getString("prize"));
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText("Error en obtenir dades", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(MostraTorneig.this);
        requestQueue.add(request);

        //Creem URL
        /*String URL = "https://www.vidalibarraquer.net/android/EXAMEN/TENNIS/"+torneig.getLogo();
        ImageRequest imageRequest = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(final Bitmap response) {
                im1.setImageBitmap(response);
                //tv1.setText("Nom: " + torneig.getTourney_name());
                //tv2.setText("Venue: " + torneig.getVenue());
                //tv3.setText("Surface: " + torneig.getSurface());
                //tv4.setText("Prize: " + torneig.getPrice());
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MostraTorneig.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue2 = Volley.newRequestQueue(MostraTorneig.this);
        requestQueue2.add(imageRequest);
*/
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
