package com.ibm.mobilefirst.mobileedge.gym;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

import com.ibm.mobilefirst.mobileedge.MobileEdgeController;
import com.ibm.mobilefirst.mobileedge.connectors.AndroidWear;
import com.ibm.mobilefirst.mobileedge.connectors.ConnectionStatus;
import com.ibm.mobilefirst.mobileedge.interfaces.ConnectionStatusListener;
import com.ibm.mobilefirst.mobileedge.interpretation.Classification;
import com.ibm.mobilefirst.mobileedge.interpretation.InterpretationListener;


public class GymApplication extends Application {
    public int[] exerciseCounts = {0,0,0,0,0,0,0};

    MobileEdgeController controller = new MobileEdgeController();
    Classification classification;

    public void connectToAndroidWear(){

        controller.setConnectionListener(new ConnectionStatusListener() {
            @Override
            public void onConnectionStatusChanged(String s, ConnectionStatus connectionStatus) {

                String message = "";

                switch (connectionStatus){
                    case Connected:
                        message = "Android Wear connected";
                        break;
                    case Disconnected:
                        message = "Android wear disconnected";
                        break;
                    case BluetoothUnavailable:
                        message = "Bluetooth unavailable";
                        break;
                    case DeviceUnavailable:
                        message = "Android wear unavailable";
                        break;
                }

                Toast toast = Toast.makeText(GymApplication.this, message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 200);
                toast.show();
            }
        });
        controller.connect(this, new AndroidWear());
    }

    public void startExerciseGestureDetection(int exerciseNumber, InterpretationListener listener){

        classification = new Classification(this);
        classification.setListener(listener);
        classification.loadGesture(this,"ex" + (exerciseNumber + 1) + ".js");

        controller.registerInterpretation(classification);
        controller.turnClassificationSensorsOn();
    }

    public void stopExerciseGestureDetection(){
        controller.turnClassificationSensorsOff();
        controller.unregisterClassification(classification);
        classification = null;
    }
}
