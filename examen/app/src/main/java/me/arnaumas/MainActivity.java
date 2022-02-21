package me.arnaumas;



import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btnTorjenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTorjenos = (Button) findViewById(R.id.btnTornejos);

    }

    public void onConsultarTornejos(View view) {
        Intent carregarDades = new Intent(view.getContext(), CarregarDades.class);
        view.getContext().startActivity(carregarDades);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Forma 1: utilitzant el main.xml
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /*// Forma 2: definim el menú amb sentències Java
        SubMenu submenu;

        // L'Objecte "menu" no permet mostrar icones,
        // per decisició de la guia d'estils d'Android.
        menu.add(1, MENU_OP1, Menu.NONE, "Opció 1 sense internacionalització").setIcon(R.drawable.ic_launcher);
        menu.add(2, MENU_OP2, Menu.NONE, R.string.option2);
        submenu = menu.addSubMenu(3, MENU_OP3, Menu.NONE, R.string.option3);
        // L'Objecte "submenu" sí permet mostrar icones.
        submenu.add(31, MENU_OP31, Menu.NONE, R.string.option3).setIcon(R.drawable.ic_launcher);
        submenu.add(32, MENU_OP32, Menu.NONE, R.string.option32).setIcon(R.drawable.abc_btn_check_material);
        */


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //switch Forma1
        switch (id) {
            case R.id.mOpcio1:
                Intent intent = new Intent(this, OpcionsApp.class);
                startActivity(intent);
                return true;
            default:
                //Toast.makeText(this, "Nothing happens", Toast.LENGTH_LONG).show();
        }


        /*//switch Forma2
        switch (id) {
            case 1:
                Toast.makeText(this,"id: "+ String.valueOf(id), Toast.LENGTH_LONG).show();
                return true;
            case 2:
                Toast.makeText(this,"id: "+ String.valueOf(id), Toast.LENGTH_LONG).show();
                return true;
            case 3:
                Toast.makeText(this,"id: "+ String.valueOf(id), Toast.LENGTH_LONG).show();
                return true;
            case 31:
                Toast.makeText(this,"id: "+ String.valueOf(id), Toast.LENGTH_LONG).show();
                return true;
            case 32:
                Toast.makeText(this,"id: "+ String.valueOf(id), Toast.LENGTH_LONG).show();
                return true;
            default:
                Toast.makeText(this, "Nothing happens", Toast.LENGTH_LONG).show();

        }*/

        return super.onOptionsItemSelected(item);
    }
}