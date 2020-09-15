package com.github.fitandroid.marshmallow;

public interface IPermissionListenerWrap {
    public interface IEachPermissionListener {
        void onAccepted(Permission permission);
    }

    public interface IPermissionListener {
        void onAccepted(boolean isGranted);
    }
}
