package commute.com.shazawdidi.commute;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommuteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button save;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_commute );
        String[] cities =getResources().getStringArray( R.array.tracker_point );
        ArrayAdapter <String> adapter = new ArrayAdapter <>( this, android.R.layout.simple_list_item_1, cities );
        String[] hourFrom = getResources().getStringArray( R.array.hour_from );
        ArrayAdapter <String> adapter2 = new ArrayAdapter <>( this, android.R.layout.simple_list_item_1, hourFrom );
        String[] hourTo = getResources().getStringArray( R.array.hour_to );
        ArrayAdapter <String> adapter3 = new ArrayAdapter <>( this, android.R.layout.simple_list_item_1, hourTo );
        String[] seetNum = getResources().getStringArray( R.array.seet_number);
        ArrayAdapter <String> adapter4 = new ArrayAdapter <>( this, android.R.layout.simple_list_item_1, seetNum );
        final AutoCompleteTextView distnationfrom = findViewById( R.id.distnationfrom );
        final AutoCompleteTextView distnationto = findViewById( R.id.distnationto );
        final Spinner spinnerFrom = findViewById( R.id.spinner_from );
        final Spinner spinnerTo = findViewById( R.id.spinner_to );
        Spinner spinnerNum = findViewById( R.id.spinner_num );
        save=findViewById( R.id.save );
        spinnerFrom.setAdapter( adapter2 );
        spinnerTo.setAdapter( adapter3 );
        spinnerNum.setAdapter( adapter4 );
        distnationfrom.setAdapter( adapter );
        distnationto.setAdapter( adapter );
        distnationfrom.setThreshold( 1 );
        distnationto.setThreshold( 2 );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

//        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
//        fab.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
//                        .setAction( "Action", null ).show();
//            }
//        } );

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.43.126")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                ApiService service = retrofit.create(ApiService.class);
                Commute commute = new Commute();
                commute.setFirstPoint(distnationfrom.getText().toString());
                commute.setLastPoint(distnationto.getText().toString());
//                commute.setFirstTime(spinnerFrom.getAutofillValue().toString());
//                    commute.setLastTime(spinnerTo.getAutofillValue().toString());
                Call<Commute> call = service.insertData(commute.getFirstPoint(),commute.getLastPoint());

                call.enqueue(new Callback<Commute>() {
                    @Override
                    public void onResponse(Call<Commute> call, Response<Commute> response) {

                        Toast.makeText(CommuteActivity.this, "response"+response, Toast.LENGTH_LONG).show();

                        distnationfrom.setText("");
                        distnationto.setText("");

                    }

                    @Override
                    public void onFailure(Call<Commute> call, Throwable t) {


                        Log.i("Hello",""+t);
                        Toast.makeText(CommuteActivity.this, "Throwable"+t, Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate( R.menu.commute, menu );
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected( item );
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
