package com.sac.energyhunter;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;


public class JSONparser {

		public NodeData parse(String object, double longitude, double latitude){
						
			NodeData node = new NodeData();
			node.longitude = longitude;
			node.latitude = latitude;
		    try {
		      JSONArray jsonArray = new JSONArray(object);
		      Log.i("Downloader",
		          "Number of entries " + jsonArray.length());
		      for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jsonObject = jsonArray.getJSONObject(i);
		        
		        if(jsonObject.getString("LastFetch").equals("LastFetch")){
		        	if(jsonObject.has("WindSpeed"))
		        	node.uWindSpeed = Double.parseDouble(jsonObject.getString("WindSpeed"));
		        	if(jsonObject.has("WindSpeedEnergy"))
		        	node.uWindEnergy = Double.parseDouble(jsonObject.getString("WindSpeedEnergy"));
		        	if(jsonObject.has("SolarEnergy"))
		        	node.uSolarEnergy = Double.parseDouble(jsonObject.getString("SolarEnergy"));
		        	if(jsonObject.has("GeothermalDegree"))
		        	node.uGeothermalDegree = Double.parseDouble(jsonObject.getString("GeothermalDegree"));
		        	
		        }else if(jsonObject.getString("LastFetch").equals("LastDay")){
		        	if(jsonObject.has("WindSpeed"))
		        	node.dWindSpeed = Double.parseDouble(jsonObject.getString("WindSpeed"));
		        	if(jsonObject.has("WindSpeedEnergy"))
		        	node.dWindEnergy = Double.parseDouble(jsonObject.getString("WindSpeedEnergy"));
		        	if(jsonObject.has("SolarEnergy"))
		        	node.dSolarEnergy = Double.parseDouble(jsonObject.getString("SolarEnergy"));
		        	if(jsonObject.has("GeothermalDegree"))
		        	node.dGeothermalDegree = Double.parseDouble(jsonObject.getString("GeothermalDegree"));
		        	
		        }else if(jsonObject.getString("LastFetch").equals("LastMonth")){
		        	if(jsonObject.has("WindSpeed"))
		        	node.mWindSpeed = Double.parseDouble(jsonObject.getString("WindSpeed"));
		        	if(jsonObject.has("WindSpeedEnergy"))
		        	node.mWindEnergy = Double.parseDouble(jsonObject.getString("WindSpeedEnergy"));
		        	if(jsonObject.has("SolarEnergy"))
		        	node.mSolarEnergy = Double.parseDouble(jsonObject.getString("SolarEnergy"));
		        	if(jsonObject.has("GeothermalDegree"))
		        	node.mGeothermalDegree = Double.parseDouble(jsonObject.getString("GeothermalDegree"));
		        
		        }else if(jsonObject.getString("LastFetch").equals("LastYear")){
		        	if(jsonObject.has("WindSpeed"))
		        	node.yWindSpeed = Double.parseDouble(jsonObject.getString("WindSpeed"));
		        	if(jsonObject.has("WindSpeedEnergy"))
		        	node.yWindEnergy = Double.parseDouble(jsonObject.getString("WindSpeedEnergy"));
		        	if(jsonObject.has("SolarEnergy"))
		        	node.ySolarEnergy = Double.parseDouble(jsonObject.getString("SolarEnergy"));
		        	if(jsonObject.has("GeothermalDegree"))
		        	node.yGeothermalDegree = Double.parseDouble(jsonObject.getString("GeothermalDegree"));
		        }
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    
		    System.out.println(node.uWindSpeed +"||"+ node.uWindEnergy +"||"+ node.uSolarEnergy +"||"+ node.uGeothermalDegree
		    		+ "\n"+ node.dWindSpeed +"||"+ node.dWindEnergy +"||"+ node.dSolarEnergy +"||"+ node.dGeothermalDegree
		    		+ "\n"+ node.mWindSpeed +"||"+ node.mWindEnergy +"||"+ node.mSolarEnergy +"||"+ node.mGeothermalDegree
		    		+ "\n"+ node.yWindSpeed +"||"+ node.yWindEnergy +"||"+ node.ySolarEnergy +"||"+ node.yGeothermalDegree);
		    return node;
		}			
}
