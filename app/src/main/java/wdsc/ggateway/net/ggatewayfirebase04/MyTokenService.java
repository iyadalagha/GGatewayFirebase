package wdsc.ggateway.net.ggatewayfirebase04;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by PROBOOK on 8/24/2017.
 */

public class MyTokenService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token =FirebaseInstanceId.getInstance().getToken();
        Log.d("ddd",token);
    }
}
