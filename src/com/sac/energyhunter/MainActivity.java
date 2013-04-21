package com.sac.energyhunter;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.internal.af.b;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MainActivity extends Activity implements OnMarkerClickListener{
  
  static final LatLng MELBOURNE = new LatLng(-37.814107, 144.96328);
  private GoogleMap map;
  private Button infoBtn;
  
  private Marker curLocMark;
  int posSolarEnergy;
  int posWindEnergy;
  int posEUsage;
  int posCarbEmis;
  int posGeoEnergy;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
        .getMap();
    infoBtn = (Button)findViewById(R.id.map_more_info_btn);
    infoBtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			onMarkerClick(curLocMark);
		}
	});
    queryBackend();
    zoomToCoords(MELBOURNE, "Melbourne");
  }
  
  private void queryBackend(){
	  // code to get the information from the backend
	  // just placeholders:
	  posSolarEnergy = 10;
	  posWindEnergy = 12;
	  posGeoEnergy = 18;
	  posEUsage = 100;
	  posCarbEmis = 6;
  }
  
  //use to move the camera to specified LatLng
  public void zoomToCoords(LatLng coords, String cityName){
		curLocMark = map.addMarker(new MarkerOptions().position(coords)
				.title(cityName));
	    // Move the camera instantly to coords with a zoom of 15.
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 15));

	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }
  
  /* *
   * For bundle put in following info:
   * - latitude of location (double, "Latitude")
   * - longitude of location (double, "Longitude")
   * - solar energy (mW/h (or kW/h, sorry im clueless)) (int, "SolarEnergy")
   * - wind energy (mW/h (or kW/h, again, im clueless)) (int, "WindEnergy")
   * - total current city energy usage (kW/h or whatever unit) (int, "TotalEUsage")
   * - carbon emissions from city energy usage (tonnes) (int, "CarbonEmissions")
   * - geothermal energy (kW/h) (int, "GeoEnergy")
   */
  public void goToStatCalculator(Bundle bundle){
	  Intent statCalcIntent = new Intent(this, StatsCalculator.class);
	  statCalcIntent.putExtras(bundle);
	  System.out.println(bundle);
	  startActivity(statCalcIntent);
  }

	@Override
	public boolean onMarkerClick(Marker marker) {
		Bundle b = new Bundle();
		b.putDouble("Latitude", marker.getPosition().latitude);
		b.putDouble("Longitude", marker.getPosition().longitude);
		b.putInt("SolarEnergy", posSolarEnergy);
		b.putInt("WindEnergy", posWindEnergy);
		b.putInt("TotalEUsage", posEUsage);
		b.putInt("CarbonEmissions", posCarbEmis);
		b.putInt("GeoEnergy", posGeoEnergy);
		System.out.println(b);
		goToStatCalculator(b);
		return false;
	}

} 