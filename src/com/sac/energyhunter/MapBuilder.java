package com.sac.energyhunter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapBuilder {
	
	final static int[] solarEnergyDraws = {R.drawable.sol_l1, R.drawable.solar_l1, R.drawable.solar_l2,
		R.drawable.solar_l3, R.drawable.solar_l4, R.drawable.solar_l5, R.drawable.solar_l6};
	
	final static int[] windEnergyDraws = {R.drawable.wind_l1, R.drawable.wind_l2,
		R.drawable.wind_l3, R.drawable.wind_l4, R.drawable.wind_l5, R.drawable.wind_l6};

	private GoogleMap map;
	MapBuilder( GoogleMap map){
		this.map = map;
	}
	
	public void addNode(NodeData node){
		
		LatLng nodePos = new LatLng(node.latitude, node.longitude);
		int windLevel = (int) (node.uWindSpeed/2.5);
		int solarLevel = (int) (node.uSolarEnergy/0.33);
		int geoLevel = (int) (node.uGeothermalDegree/200);
		
        Marker wind = map.addMarker(new MarkerOptions()
            .position(nodePos)
            .title("WindSpeed:"+node.uWindSpeed + "\nWindEnergy:"+node.uWindEnergy)
            .snippet("Wind")
            .icon(BitmapDescriptorFactory
                .fromResource(windEnergyDraws[windLevel])));
        
        LatLng solarPos = new LatLng(nodePos.latitude - 0.02, nodePos.longitude - 0.02);
        Marker solar = map.addMarker(new MarkerOptions()
        .position(solarPos)
        .title("SolarEnergy Now:"+node.uSolarEnergy)
        .snippet("Solar Energy")
        .icon(BitmapDescriptorFactory
            .fromResource(solarEnergyDraws[solarLevel])));
    
        LatLng geoPos = new LatLng(nodePos.latitude + 0.04, nodePos.longitude - 0.03);
        Marker geo = map.addMarker(new MarkerOptions()
        .position(geoPos)
        .title("Geothermal Energy Now:"+node.uSolarEnergy)
        .snippet("Geothermal Energy")
        .icon(BitmapDescriptorFactory
            .fromResource(R.drawable.geothermal)));
        // Move the camera instantly to CurrentPosition with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(nodePos, 15));
        
        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

}
