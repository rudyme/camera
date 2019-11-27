package com.rudyme.android.camera;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;

public class CameraInfo {
    private Context mContext;
    private CameraManager mCameraManager;
    private CameraId[] mCameraIds;

    public CameraInfo(Context context) {
        mContext = context;
        mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        initCameraIds();
    }

    public CameraId[] getCameraIds() {
        return mCameraIds;
    }

    private void initCameraIds() {
        try {
            String[] ids = mCameraManager.getCameraIdList();
            mCameraIds = new CameraId[ids.length];
            for (int i = 0; i < ids.length; i ++) {
                CameraCharacteristics characteristics = mCameraManager.getCameraCharacteristics(ids[i]);
                boolean isFront = characteristics.get(CameraCharacteristics.LENS_FACING) == CameraMetadata.LENS_FACING_FRONT;
                mCameraIds[i] = new CameraId(Integer.valueOf(ids[i]), isFront, characteristics);
            }
        } catch (CameraAccessException cae) {
            cae.printStackTrace();
        }
    }


}

class CameraId {
    private int mId;
    private boolean mIsFront;
    private CameraCharacteristics mCharacteristics;

    public CameraId(int id, boolean isFront, CameraCharacteristics characteristics) {
        mId = id;
        mIsFront = isFront;
        mCharacteristics = characteristics;
    }

    public int getId() {
        return mId;
    }

    public boolean isFront() {
        return mIsFront;
    }

    public CameraCharacteristics getCharacteristics() {
        return mCharacteristics;
    }

    @Override
    public String toString() {
        return "id = " + mId + ", isFront = " + mIsFront;
    }
}
