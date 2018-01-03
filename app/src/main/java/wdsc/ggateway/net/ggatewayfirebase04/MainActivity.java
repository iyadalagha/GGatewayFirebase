package wdsc.ggateway.net.ggatewayfirebase04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-1365493179524878~1964033927");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1365493179524878/1833485060");


        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("86EC800312A82DECFB0347DA3AEEEC11")
                .build();
        AdView v = (AdView) findViewById(R.id.adView);
        //v.setAdSize(AdSize.BANNER);
        //v.setAdUnitId("ca-app-pub-1365493179524878/6909717779");
        v.loadAd(request);
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                //super.onAdClosed();
                AdRequest request = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .addTestDevice("86EC800312A82DECFB0347DA3AEEEC11")
                        .build();
                mInterstitialAd.loadAd(request);
            }
        });
        mInterstitialAd.loadAd(request);


        FirebaseMessaging.getInstance().subscribeToTopic("khanyounis");
        FirebaseMessaging.getInstance().subscribeToTopic("news");


        Button loginBtn = (Button) findViewById(R.id.mainLoginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        Button signUpBtn = (Button) findViewById(R.id.mainSignupBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });
    }
}
