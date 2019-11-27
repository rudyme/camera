package com.rudyme.android.camera;

import android.Manifest;
import android.app.Activity;

public class Permission {
    public static void requestPermission(Activity activity) {
        String[] permissions = new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        };
        activity.requestPermissions(permissions, 0);
    }
}
