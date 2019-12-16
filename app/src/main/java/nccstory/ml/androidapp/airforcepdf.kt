package nccstory.ml.androidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

class airforcepdf:AppCompatActivity(){
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(nccstory.ml.androidapp.R.layout.pdf3)

        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        MobileAds.initialize(this, getString(nccstory.ml.androidapp.R.string.addmob_app_id))

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(nccstory.ml.androidapp.R.string.adsunit)
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                mInterstitialAd.show()
                super.onAdLoaded()
            }
        }



        val pdfView = findViewById<PDFView>(nccstory.ml.androidapp.R.id.pdf_view3)
        pdfView.fromAsset("airforcehandbook.pdf").load()


    }


}