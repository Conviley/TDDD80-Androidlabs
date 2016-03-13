package com.lab2tddd80.tjegu689.lab4;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    TextView mLatitudeText;
    TextView mLongitudeText;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    double latitude,longitude;

    String locality;
    String subLocality;

    List<Address> addressList;
    Address adr;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageView);
        Button snap = (Button) findViewById(R.id.snap);
        mLatitudeText = (TextView) findViewById(R.id.latitude);
        mLongitudeText = (TextView) findViewById(R.id.longitude);
        progressDialog = new ProgressDialog(this);

        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Fetching Location....");
                progressDialog.show();
                getLocation();
                dispatchTakePictureIntent();


            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    public void getLocation() {
        MyListener listener = new MyListener();
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        //getAddress();
    }

    public class MyListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Geocoder gc = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                addressList = gc.getFromLocation(latitude,longitude,1);
                System.out.println("" + addressList.size());
                if (addressList != null && addressList.size() > 0){
                    adr = addressList.get(0);
                    locality = adr.getLocality();
                    subLocality = adr.getSubLocality();
                    System.out.println(locality + subLocality);
                    mLongitudeText.setText(adr.getLocality());
                    mLatitudeText.setText(adr.getAddressLine(0));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
            System.out.println("onLoaction changed");
            System.out.println(locality + subLocality);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
//
//    public void getAddress(){
//        Geocoder gc = new Geocoder(this, Locale.getDefault());
//        try {
//             addressList = gc.getFromLocation(latitude,longitude,1);
//            System.out.println("" + addressList.size());
//            if (addressList != null && addressList.size() > 0){
//                adr = addressList.get(0);
//                locality = adr.getLocality();
//                subLocality = adr.getSubLocality();
//                System.out.println(locality + subLocality);
//                mLongitudeText.setText(adr.getLocality());
//                mLatitudeText.setText(adr.getAddressLine(0));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

