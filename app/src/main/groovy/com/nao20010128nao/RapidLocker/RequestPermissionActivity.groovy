package com.nao20010128nao.RapidLocker

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RequestPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        contentView=R.layout.activity_main
        showAskingActivity()
    }

    public void showAskingActivity(){
        def cn=new ComponentName(this,AdminReceiver)
        def intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn)
        startActivityForResult(intent, 1)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode!=RESULT_OK){
            showAskingActivity()
        }else{
            (getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager).lockNow()
            finish()
        }
    }
}
