package nccstory.ml.androidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.songview.*

class songview:AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.songview)
        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        MobileAds.initialize(this, getString(R.string.addmob_app_id))

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.indus)
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                mInterstitialAd.show()
                super.onAdLoaded()
            }
        }


        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
        adView1.loadAd(adRequest)

    }
}