package com.nao20010128nao.RapidLocker

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.Icon
import android.service.quicksettings.TileService

/**
 * Created by nao on 2016/12/25.
 */
public class LockTileService extends TileService{
    @Override
    void onClick() {
        def dpm=getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        if(dpm.isAdminActive(new ComponentName(this,AdminReceiver))){
            if(!locked){
                dpm.lockNow()
            }
        }else{
            startActivityAndCollapse(new Intent(this,RequestPermissionActivity))
        }
    }

    @Override
    void onDestroy() {

    }

    @Override
    void onStartListening() {
        qsTile.icon=Icon.createWithResource(this,R.drawable.ic_lock_white_48dp)
        def dpm=getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        if(dpm.isAdminActive(new ComponentName(this,AdminReceiver))){
            qsTile.label=getText(R.string.lockNow)
        }else{
            qsTile.label=getText(R.string.permRequired)
        }
    }

    @Override
    void onStopListening() {

    }

    @Override
    void onTileAdded() {
        qsTile.icon=Icon.createWithResource(this,R.drawable.ic_lock_white_48dp)
        def dpm=getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        if(dpm.isAdminActive(new ComponentName(this,AdminReceiver))){
            qsTile.label=getText(R.string.lockNow)
        }else{
            qsTile.label=getText(R.string.permRequired)
        }
    }

    @Override
    void onTileRemoved() {

    }
}