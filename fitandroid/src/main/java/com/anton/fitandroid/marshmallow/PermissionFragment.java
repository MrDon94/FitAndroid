package com.anton.fitandroid.marshmallow;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;

import java.util.Random;
public class PermissionFragment extends Fragment {

    private SparseArray<IPermissionListenerWrap.IPermissionListener> mCallbacks = new SparseArray<>();
    private SparseArray<IPermissionListenerWrap.IEachPermissionListener> mEachCallbacks = new SparseArray<>();
    private Random mCodeGenerator = new Random();
    private FragmentActivity mActivity;

    public PermissionFragment() {
        // Required empty public constructor
    }

    public static PermissionFragment newInstance() {
        return new PermissionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mActivity = getActivity();

    }

    public void request(@NonNull String[] permissions, IPermissionListenerWrap.IPermissionListener callback) {
        int requestCode = makeRequestCode();
        mCallbacks.put(requestCode, callback);
        requestPermissions(permissions, requestCode);
    }

    public void requestEach(@NonNull String[] permissions, IPermissionListenerWrap.IEachPermissionListener callback) {
        int requestCode = makeRequestCode();
        mEachCallbacks.put(requestCode, callback);
        requestPermissions(permissions, requestCode);
    }


    /**
     * 随机生成唯一的requestCode，最多尝试10次
     *
     * @return
     */
    private int makeRequestCode() {
        int requestCode;
        int tryCount = 0;
        do {
            requestCode = mCodeGenerator.nextInt(0x0000FFFF);
            tryCount++;
        } while (mCallbacks.indexOfKey(requestCode) >= 0 && tryCount < 10);
        return requestCode;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handlePermissionCallBack(requestCode, grantResults);
        handleEachPermissionCallBack(requestCode, permissions, grantResults);
    }

    private void handlePermissionCallBack(int requestCode, @NonNull int[] grantResults) {
        IPermissionListenerWrap.IPermissionListener callback = mCallbacks.get(requestCode);
        mCallbacks.remove(requestCode);

        if (callback == null) {
            return;
        }

        boolean allGranted = false;
        int length = grantResults.length;
        for (int i = 0; i < length; i++) {
            int grantResult = grantResults[i];
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
            allGranted = true;
        }
        if (allGranted) {
            callback.onAccepted(true);
        } else {
            callback.onAccepted(false);
        }
    }

    private void handleEachPermissionCallBack(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        IPermissionListenerWrap.IEachPermissionListener eachCallback = mEachCallbacks.get(requestCode);

        if (eachCallback == null) {
            return;
        }

        mEachCallbacks.remove(requestCode);
        int length = grantResults.length;
        for (int i = 0; i < length; i++) {
            int grantResult = grantResults[i];
            Permission permission;
            String name = permissions[i];
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                permission = new Permission(name, true);
                eachCallback.onAccepted(permission);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, name)) {
                    permission = new Permission(name, false, true);
                } else {
                    permission = new Permission(name, false, false);
                }
                eachCallback.onAccepted(permission);
            }

        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    boolean isGranted(String permission) {
        if (mActivity == null) {
            throw new IllegalStateException("This fragment must be attached to an activity.");
        }
        return mActivity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    boolean isRevoked(String permission) {
        if (mActivity == null) {
            throw new IllegalStateException("This fragment must be attached to an activity.");
        }
        return mActivity.getPackageManager().isPermissionRevokedByPolicy(permission, mActivity.getPackageName());
    }

}
