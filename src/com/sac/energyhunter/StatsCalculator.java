package com.sac.energyhunter;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StatsCalculator extends Activity{
	
	final static int[] solarEnergyLevels = {1,4,6,8,10,12,14};
	final static int[] solarEnergyDraws = {R.drawable.sol_l1, R.drawable.solar_l1, R.drawable.solar_l2,
		R.drawable.solar_l3, R.drawable.solar_l4, R.drawable.solar_l5, R.drawable.solar_l6};
	
	final static int[] windEnergyLevels = {4,6,8,10,12,14};
	final static int[] windEnergyDraws = {R.drawable.wind_l1, R.drawable.wind_l2,
		R.drawable.wind_l3, R.drawable.wind_l4, R.drawable.wind_l5, R.drawable.wind_l6};
	
	final static int[] geoEnergyLevels = {8,10,12,14,16,18};
	final static int[] geoEnergyDraws = {R.drawable.geo_l1, R.drawable.geo_l2,
		R.drawable.geo_l3, R.drawable.geo_l4, R.drawable.geo_l5, R.drawable.geo_l6};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats_calculator_layout);
		Bundle b = getIntent().getExtras();
		System.out.println(b);
		int sE = b.getInt("SolarEnergy", 12);
		int wE = b.getInt("WindEnergy", 12);
		int cEU = b.getInt("TotalEUsage", 120);
		int cE = b.getInt("CarbonEmissions", 12);
		int gE = b.getInt("GeoEnergy", 18);
		double lat = b.getDouble("Latitude", MainActivity.MELBOURNE.latitude);
		double lng = b.getDouble("Longitude", MainActivity.MELBOURNE.longitude);
		setUpLayout(sE,wE,cEU,cE,gE,lat,lng);
	}
	
	private void setUpLayout(int solarEnergy, int windEnergy, int cityEUsage,
			int carbonPoll, int geoEnergy, double lat, double lng){
		ImageView solarImg = (ImageView)findViewById(R.id.stat_pot_solar_image);
		TextView solarTxt = (TextView)findViewById(R.id.stat_pot_solar);
		ImageView windImg = (ImageView)findViewById(R.id.stat_pot_wind_image);
		TextView windTxt = (TextView)findViewById(R.id.stat_pot_wind);
		ImageView geoImage = (ImageView)findViewById(R.id.stat_geo_image);
		TextView geoTxt = (TextView)findViewById(R.id.stat_geo_txt);
		TextView totEUsage = (TextView)findViewById(R.id.stat_city_usage);
		TextView carbEmis = (TextView)findViewById(R.id.stat_carbon_poll);

	    GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_stat_calc))
	        .getMap();
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));
	    
		for(int i=0; i<solarEnergyLevels.length; i++){
			if(solarEnergy <= solarEnergyLevels[i]){
				solarImg.setBackgroundResource(solarEnergyDraws[0]);
				break;
			}
		}for(int i=0; i<windEnergyLevels.length; i++){
			if(windEnergy <= windEnergyLevels[i]){
				windImg.setBackgroundResource(windEnergyDraws[i]);
				break;
			}
		}for(int i=0; i<geoEnergyLevels.length; i++){
			if(geoEnergy <= geoEnergyLevels[i]){
				geoImage.setBackgroundResource(geoEnergyDraws[i]);
				break;
			}
		}
		
		solarTxt.setText(Integer.toString(solarEnergy)+" kW/h");
		windTxt.setText(Integer.toString(windEnergy)+" kW/h");
		geoTxt.setText(Integer.toString(geoEnergy)+" kW/h");
		//totEUsage.setText("City's Energy Usage: "+Integer.toString(cityEUsage)+" kW/h");
		//carbEmis.setText("Carbon Emissions: "+Integer.toString(carbonPoll)+" tonnes");
	}
}
