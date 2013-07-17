package com.lakehub.beinafu;

import android.app.Activity;
import android.os.Bundle;

public class MapTest extends Activity {
	//GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_test);
		//map = ((com.google.android.gms.maps.SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	}
	
}
