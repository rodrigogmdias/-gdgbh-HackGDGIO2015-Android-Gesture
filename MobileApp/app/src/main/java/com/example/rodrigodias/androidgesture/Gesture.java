package com.example.rodrigodias.androidgesture;

import android.app.Activity;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.content.Context;
import android.hardware.SensorEvent;
import java.util.Arrays;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;

import java.net.URI;
import java.net.URISyntaxException;

public class Gesture extends Activity implements SensorEventListener {

    private WebSocketClient mWebSocketClient;
    private Button sendButton;
    private SensorManager mSensorManager;
    private Sensor aceleromterSensor;
    private double[] gravity;
    private double[] linear_acceleration;

    public void onSensorChanged(SensorEvent event){
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        final double alpha = 0.8;

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

//        Log.d("debug", "x: "+linear_acceleration[0]+" - Y: "+linear_acceleration[1]+" - Z: "+linear_acceleration[2]);
        Log.d("debug", new JSONArray(Arrays.asList(linear_acceleration)).toString());
//        mWebSocketClient.send(new JSONArray(Arrays.asList(linear_acceleration)).toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gravity = new double[3];
        linear_acceleration = new double[3];

        setContentView(R.layout.activity_gesture);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                Gesture.this.connectWebSocket();

                mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                aceleromterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

                mSensorManager.registerListener(Gesture.this, aceleromterSensor, SensorManager.SENSOR_DELAY_NORMAL);

                sendButton = (Button) stub.findViewById(R.id.send_button);
                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://localhost:8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        TextView textView = (TextView)findViewById(R.id.messages);
//                        textView.setText(textView.getText() + "\n" + message);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }
}
