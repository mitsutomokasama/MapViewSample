package com.example.android;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

public class MapViewSampleActivity extends MapActivity {
	private static final String TAG = "MapViewSampleActivity";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		MapView mapView = (MapView) findViewById(R.id.map);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);

		MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(
				getResources().getDrawable(R.drawable.ic_launcher));
		List<Overlay> overlayList = mapView.getOverlays();
		overlayList.add(myItemizedOverlay);

		GeoPoint point;
		OverlayItem item;

		// 東京駅
		point = new GeoPoint(35681382, 139766084);
		item = new OverlayItem(point, "", "");
		myItemizedOverlay.addPoint(item);

		// 京都駅
		point = new GeoPoint(34985458, 135757755);
		item = new OverlayItem(point, "", "");
		myItemizedOverlay.addPoint(item);

		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
		private List<OverlayItem> items = new ArrayList<OverlayItem>();

		public MyItemizedOverlay(Drawable defaultMarker) {
			super(boundCenterBottom(defaultMarker));

			populate();
		}

		@Override
		protected OverlayItem createItem(int arg0) {
			return items.get(arg0);
		}

		@Override
		public int size() {
			return items.size();
		}

		public void addPoint(OverlayItem item) {
			items.add(item);
			populate();
		}

		public void clear() {
			items.clear();
			populate();
		}

		@Override
		public boolean onSnapToItem(int x, int y, Point snapPoint,
				MapView mapView) {
			Log.d(TAG, "onSnapToItem");
			return super.onSnapToItem(x, y, snapPoint, mapView);
		}
	}
}