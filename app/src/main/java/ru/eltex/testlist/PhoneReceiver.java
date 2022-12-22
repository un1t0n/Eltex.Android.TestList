package ru.eltex.testlist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class PhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println(intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER));
        System.out.println(intent.getExtras().getString(TelephonyManager.EXTRA_STATE));
    }
}
