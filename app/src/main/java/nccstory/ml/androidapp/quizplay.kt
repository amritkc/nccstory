package nccstory.ml.androidapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tags.*
import kotlinx.android.synthetic.main.startquiz.*

class quizplay:AppCompatActivity(){
    private lateinit var mInterstitialAd: InterstitialAd
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startquiz)
        supportActionBar!!.title = "Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
        adViewstart.loadAd(adRequest)


        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.adsunit)
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                mInterstitialAd.show()
                super.onAdLoaded()
            }
        }
        val myweb:WebView? = findViewById(R.id.quizweb)
        myweb!!.settings.javaScriptEnabled = true
        myweb.webViewClient = WebViewClient()
        myweb.loadUrl("http://nccquiz.mygamesonline.org")
    }

    @Suppress("OverridingDeprecatedMember")
    class MyWebViewClient : WebViewClient(){
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            super.onPageStarted(view, url, favicon)
        }
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
        }

    }

    override fun onBackPressed() {
        if ( quizweb!= null && quizweb.canGoBack()) quizweb.goBack() // if there is previous page open it
        else super.onBackPressed() //if there is no previous page, close app
    }
}