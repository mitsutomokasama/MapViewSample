package com.example.android;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
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

		MyOverlay myOverlay = new MyOverlay();
		List<Overlay> overlayList = mapView.getOverlays();
		overlayList.add(myOverlay);

		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private class MyOverlay extends Overlay {
		private GeoPoint mGeoPoint;
		public MyOverlay() {
			mGeoPoint = null;
		}

		@Override
		public boolean onTap(GeoPoint p, MapView mapView) {
			mGeoPoint = p;
			return super.onTap(p, mapView);
		}

		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {
			super.draw(canvas, mapView, shadow);
			if (!shadow) {
				if (mGeoPoint != null) {
					Projection projection = mapView.getProjection();
		            Point point = new Point();
		            projection.toPixels(mGeoPoint, point);

					Paint paint = new Paint();
					paint.setStyle(Paint.Style.FILL);
					paint.setARGB(255, 255, 0, 0);

					canvas.drawCircle(point.x, point.y, 16, paint);
				}
			}
		}
	}
}