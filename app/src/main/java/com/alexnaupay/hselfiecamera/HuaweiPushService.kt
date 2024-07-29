package com.alexnaupay.hselfiecamera

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import kotlin.concurrent.thread

class HuaweiPushService : HmsMessageService() {

    companion object {
        private val TAG = HuaweiPushService::class.java.simpleName
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "Mensaje Recibido")
        remoteMessage?.let {
            Log.d(TAG, " - Data ${it.data}")
        }
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.i(TAG, "Huawei push token: $token")
    }

}

class GetTokenAction() {
    private val handle: Handler = Handler(Looper.getMainLooper())
    fun getToken(context: Context, callback: (String) -> Unit) {
        thread {
            try {
                val appID = AGConnectServicesConfig.fromContext(context).getString("client/app_id")
                val token = HmsInstanceId.getInstance(context).getToken(appID, "HCM")
                handle.post { callback(token) }
            } catch (err: Exception) {
                Log.e("Error: ", err.toString())
            }
        }
    }
}