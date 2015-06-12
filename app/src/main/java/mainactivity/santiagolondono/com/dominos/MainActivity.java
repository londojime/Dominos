package mainactivity.santiagolondono.com.dominos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    static private DataBaseManager manager;




    EditText edit_nombre,edit_latitud,edit_longitud;
    String nombre, coordenadas, latitud,longitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager= new DataBaseManager(this);






        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){

            Presentacion fragment = new Presentacion();
            fragmentTransaction.add(R.id.fcambiarport, fragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static DataBaseManager getManager() {
        return manager;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.fsedes) {

            Intent i =new Intent(this,Sedes.class);
            startActivity(i);
        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Intent i =new Intent(this,activity_about.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void ir_sedes(View view) {

        if (view.getId() == R.id.Bsedes){


          //  Intent i =new Intent(this,Sedes.class);
           // startActivity(i);

            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                 Opciones fragment = new Opciones();
                fragmentTransaction.replace(R.id.fcambiarport, fragment).commit();
        }

    }

    public void agregar(View view) {

        if (view.getId() == R.id.Bagregar){

           // manager= new DataBaseManager(this);

            edit_nombre = (EditText) findViewById(R.id.nombre);
            edit_latitud = (EditText) findViewById(R.id.latitud);
            edit_longitud = (EditText) findViewById(R.id.longitud);


            nombre=edit_nombre.getText().toString();
           latitud=edit_latitud.getText().toString();
            longitud=edit_longitud.getText().toString();

             manager.ingresar(nombre,latitud,longitud);

           Toast.makeText(this, nombre + " "+getResources().getString(R.string.agregado) , Toast.LENGTH_LONG).show();
        }



    }

    public void eliminar_sede(View view) {

        if (view.getId() == R.id.Beliminar){

           edit_nombre = (EditText) findViewById(R.id.nombre);
            nombre=edit_nombre.getText().toString();

            //manager.ingresar(nombre,latitud,longitud);

            manager.eliminar_nombre(nombre);

            Toast.makeText(this,nombre+ " "+getResources().getString(R.string.eliminada), Toast.LENGTH_LONG).show();
        }



    }

    public void actualizar_sede(View view) {

        if (view.getId() == R.id.Bactualizar){

            edit_nombre = (EditText) findViewById(R.id.nombre);
            edit_latitud = (EditText) findViewById(R.id.latitud);
            edit_longitud = (EditText) findViewById(R.id.longitud);


            nombre=edit_nombre.getText().toString();
            latitud=edit_latitud.getText().toString();
            longitud=edit_longitud.getText().toString();

            manager.actualizar(nombre, latitud, longitud);

            Toast.makeText(this,nombre+ " "+getResources().getString(R.string.actualizada), Toast.LENGTH_LONG).show();
        }
    }


    public void ver_sedes(View view) {

        if (view.getId() == R.id.Bver) {

            Intent i = new Intent(this, Sedes.class);
            startActivity(i);

        }


    }
}
