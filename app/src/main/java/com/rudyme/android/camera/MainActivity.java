package com.rudyme.android.camera;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends Activity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text);

        Permission.requestPermission(this);

        CameraInfo info = new CameraInfo(this);
        // mTextView.setText(Arrays.toString(info.getCameraIds()));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        checkPermissionResult(requestCode, grantResults);
    }

    private void checkPermissionResult(int requestCode, int[] grantResults) {
        if (requestCode == 0) {
            for (int i = 0; i < grantResults.length; i ++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Finish camera application because of the lack of necessary permissions.",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
}
