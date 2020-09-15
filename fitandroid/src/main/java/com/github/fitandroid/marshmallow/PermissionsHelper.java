package com.github.fitandroid.marshmallow;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class PermissionsHelper {
    private static final String TAG = PermissionsHelper.class.getSimpleName();

    @VisibleForTesting
    private Lazy<PermissionFragment> mPermissionFragment;

    public PermissionsHelper(@NonNull final FragmentActivity activity) {
        mPermissionFragment = getLazySingleton(activity.getSupportFragmentManager());
    }

    public PermissionsHelper(@NonNull final Fragment fragment) {
        mPermissionFragment = getLazySingleton(fragment.getChildFragmentManager());
    }

    @NonNull
    private Lazy<PermissionFragment> getLazySingleton(@NonNull final FragmentManager fragmentManager) {
        return new Lazy<PermissionFragment>() {
            private PermissionFragment rxPermissionsFragment;

            @Override
            public synchronized PermissionFragment get() {
                if (rxPermissionsFragment == null) {
                    rxPermissionsFragment = getPermissionFragment(fragmentManager);
                }
                return rxPermissionsFragment;
            }
        };
    }


    public void requestEach(String[] permissions, IPermissionListenerWrap.IEachPermissionListener eachPermissionListener) {
        mPermissionFragment.get().requestEach(permissions, eachPermissionListener);
    }

    public void request(String[] permissions, IPermissionListenerWrap.IPermissionListener requestPermissionListener) {
        mPermissionFragment.get().request(permissions, requestPermissionListener);
    }

    private PermissionFragment getPermissionFragment(FragmentManager fragmentManager) {
        PermissionFragment fragment = (PermissionFragment) fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = PermissionFragment.newInstance();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commitNow();
        }
        return fragment;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isGranted(String permission) {
        return !isMarshmallow() || mPermissionFragment.get().isGranted(permission);
    }

    /**
     * Returns true if the permission has been revoked by a policy.
     * <p>
     * Always false if SDK &lt; 23.
     */
    @SuppressWarnings("WeakerAccess")
    public boolean isRevoked(String permission) {
        return isMarshmallow() && mPermissionFragment.get().isRevoked(permission);
    }

    boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    @FunctionalInterface
    public interface Lazy<V> {
        V get();
    }
}
