package com.example.mysensor;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class SensorTest extends Activity implements SensorListener {
	final String tag = "SensorTest";
	SensorManager sm = null;
	TextView xViewA = null;
	TextView yViewA = null;
	TextView zViewA = null;
	TextView xViewO = null;
	TextView yViewO = null;
	TextView zViewO = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// get reference to SensorManager
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		setContentView(R.layout.activity_sensor_test);
		xViewA = (TextView) findViewById(R.id.xbox);
		yViewA = (TextView) findViewById(R.id.ybox);
		zViewA = (TextView) findViewById(R.id.zbox);
		xViewO = (TextView) findViewById(R.id.xboxo);
		yViewO = (TextView) findViewById(R.id.yboxo);
		zViewO = (TextView) findViewById(R.id.zboxo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_sensor_test, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(int sensor, int accuracy) {
		// TODO Auto-generated method stub
		Log.d(tag, "onAccuracyChanged: " + sensor + ", accuracy: " + accuracy);
	}

	@Override
	public void onSensorChanged(int sensor, float[] values) {
		// TODO Auto-generated method stub
		synchronized (this) {
			Log.d(tag, "onSensorChanged: " + sensor + ", x: " + values[0]
					+ ", y: " + values[1] + ", z: " + values[2]);
			if (sensor == SensorManager.SENSOR_ORIENTATION) {
				xViewO.setText("OrientationX: " + values[0]);
				yViewO.setText("OrientationY: " + values[1]);
				zViewO.setText("OrientationZ: " + values[2]);
			}
			if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
				xViewA.setText("AccelX: " + values[0]);
				yViewA.setText("AccelY: " + values[1]);
				zViewA.setText("AccelZ: " + values[2]);
			}
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensor
		sm.registerListener(this, SensorManager.SENSOR_ORIENTATION
				| SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		// unregister listener
		sm.unregisterListener(this);
		super.onStop();
	}
}
