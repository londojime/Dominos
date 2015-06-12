package mainactivity.santiagolondono.com.dominos;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;


public class Sedes extends ActionBarActivity implements AdapterView.OnItemClickListener{

  //  private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private EditText tv;
    private ImageButton bt;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;


    DataBaseManager manager = MainActivity.getManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedes);

       // manager= new DataBaseManager(this);

       // manager.ingresar("Juan","2222222");






                lista = (ListView) findViewById(R.id.lista);
       // tev = (TextView) findViewById(R.id.texto);

        String[] from = new String[] {manager.CN_NAME,manager.CN_LATITUD,manager.CN_LONGITUD};
        int[] to = new int[] {R.id.sede,R.id.lati,R.id.longi};

        cursor = manager.cargarCursor();
        adapter = new SimpleCursorAdapter(this,R.layout.lista,cursor,from,to,0);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(this);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sedes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Intent i =new Intent(this,activity_about.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

      // String item = ((TextView)view).getText().toString();




            TextView nombre = (TextView) view.findViewById(R.id.sede);
            TextView latit = (TextView) view.findViewById(R.id.lati);
            TextView longi = (TextView) view.findViewById(R.id.longi);

        String sede = nombre.getText().toString();
        String lat = latit.getText().toString();
        String lon = longi.getText().toString();


            Toast.makeText(this,sede, Toast.LENGTH_LONG).show();
                        Intent i =new Intent(this,MapsActivity.class);
            i.putExtra("latitude", lat);
            i.putExtra("longitude", lon);
            i.putExtra("nombre", sede);

            startActivity(i);






    }


    public void buscar(View v){

       EditText tv = (EditText) findViewById(R.id.editText);

                    if (v.getId() == R.id.Bbuscar){

                tv.getText().toString();
                Cursor c = manager.buscarSede(tv.getText().toString());
                adapter.changeCursor(c);
            }
    }

}
