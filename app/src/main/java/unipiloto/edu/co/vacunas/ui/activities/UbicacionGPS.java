package unipiloto.edu.co.vacunas.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.text.HtmlCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import unipiloto.edu.co.vacunas.R;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class UbicacionGPS extends AppCompatActivity {
    Button btLocation;
    TextView text_View1, text_View2, text_View3, text_View4, text_View5 , location_view;
    FusedLocationProviderClient fusedLocationPorviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nose);
        btLocation = findViewById(R.id.bt_location);
        text_View1 = findViewById(R.id.text_view1);
        text_View2 = findViewById(R.id.text_view2);
        text_View3 = findViewById(R.id.text_view3);
        text_View3 = findViewById(R.id.text_view3);
        text_View4 = findViewById(R.id.text_view4);
        text_View5 = findViewById(R.id.text_view5);
        location_view = findViewById(R.id.location_view);
        fusedLocationPorviderClient = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location_view.setText("Check permission");
                if (ActivityCompat.checkSelfPermission(UbicacionGPS.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    location_view.setText("When permission granted");
                    getLocation();
                } else {
                    location_view.setText("When permission denied");
                    ActivityCompat.requestPermissions(UbicacionGPS.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
                System.out.println(ActivityCompat.checkSelfPermission(UbicacionGPS.this, Manifest.permission.ACCESS_FINE_LOCATION) + " " + PackageManager.PERMISSION_GRANTED);
            }
        });
    }


    public void getLocation(){
        if (ActivityCompat.checkSelfPermission(UbicacionGPS.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationPorviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    System.out.println("location = " + location);
                    if(location != null){
                        Geocoder geocoder = new Geocoder(UbicacionGPS.this, Locale.getDefault());
                        try{
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                            text_View1.setText("latitud: " + (double) addresses.get(0).getLatitude());
                            text_View2.setText("longitud: " + (double) addresses.get(0).getLongitude());
                            text_View3.setText(addresses.get(0).getCountryName());
                            text_View4.setText(addresses.get(0).getLocality());
                            text_View5.setText(addresses.get(0).getAddressLine(0));

                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }else{
                        if (ActivityCompat.checkSelfPermission(UbicacionGPS.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                            getLocation();
                    }

                }
            });
        }
    }
}

