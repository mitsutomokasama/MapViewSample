package com.example.android;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.location.LocationManager;
import android.os.Bundle;

public class MapViewSampleActivity extends MapActivity {

	private MapView mapView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.map);
		mapView.setBuiltInZoomControls(true);

		final MyLocationOverlay overlay = new MyLocationOverlay(
				getApplicationContext(), mapView);
		overlay.onProviderEnabled(LocationManager.GPS_PROVIDER); // GPS を使用する
		overlay.enableMyLocation();
		overlay.runOnFirstFix(new Runnable() {

			public void run() {
				mapView.getController().animateTo(overlay.getMyLocation()); // 現在位置を自動追尾する
			}
		});
		mapView.getOverlays().add(overlay);
		mapView.invalidate();
		mapView.getController().setZoom(17); // 1～21の値をセット
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}