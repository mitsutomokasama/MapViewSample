package com.example.android;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.os.Bundle;

public class MapViewSampleActivity extends MapActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		MapView mapView = (MapView) findViewById(R.id.map);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);

		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}