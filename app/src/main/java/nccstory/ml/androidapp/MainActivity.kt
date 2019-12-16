package nccstory.ml.androidapp


import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var progressBar: ProgressBar? = null
    internal var url = "https://www.nccstory.ml"
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.home ->{
                replaceFragment(homeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.Products ->{
                replaceFragment(ProductsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.Tags ->{
                replaceFragment(tagsFragment())
                return@OnNavigationItemSelectedListener  true
            }
            R.id.web ->{
                replaceFragment(webFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.hand ->{
                replaceFragment(activityhandbook())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val swipe = findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        swipe.setOnRefreshListener(OnRefreshListener {
           Toast.makeText(this,"LOODING",Toast.LENGTH_SHORT).show()
            loadweb()
        })


        // MobileAds.initialize(this, getString(R.string.addmob_app_id))
        //val adRequest = AdRequest.Builder().build()
       // adViewh.loadAd(adRequest)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(homeFragment())
        loadweb()
    }
    fun loadweb() {

        var wv: WebView?

        wv = findViewById<WebView>(R.id.webview)
        progressBar = findViewById<ProgressBar>(R.id.progressBar3)
        wv.webViewClient = myWebClient()
        wv.settings.javaScriptEnabled = true
        wv.settings.builtInZoomControls = true
        wv.settings.displayZoomControls = true
        wv.loadUrl(url)

    }

    inner class myWebClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

            progressBar!!.visibility = View.VISIBLE
            view.loadUrl(url)
            return true

        }

        override fun onPageFinished(view: WebView, url: String) {

            super.onPageFinished(view, url)
            progressBar!!.visibility = View.GONE
            swiperefresh.setRefreshing(false)


        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            webview.loadUrl("file:///android/assests/index.html")
        }
    }

    override fun onBackPressed() {
        if ( webview!= null && webview.canGoBack()) webview.goBack() // if there is previous page open it
        else super.onBackPressed() //if there is no previous page, close app
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.aboutappmenu) {
            Toast.makeText(this, "About App", Toast.LENGTH_LONG).show()
                val intent = Intent(this, aboutapp::class.java)
                startActivity(intent)
            return true
        } else if(id == R.id.shareapp){
            Toast.makeText(this, "Share App", Toast.LENGTH_LONG).show()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, " Download *NccStory* Android App                   " +
                    "                                               " +
                    "  With Handbook Pre-built  " +
                    "                                                                                       " +
                    "  NCC QUIZ " +
                    "                                                                                         " +
                    "  NCC STORY " +
                    "                                                                                  " +
                    "Tips and Tricks to Complete RDC,TSC, NSC,VSC" +
                    "                                                                                    " +
                    " https://play.google.com/store/apps/details?id=nccstory.ml.androidapp")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent ,"Select the App:"))
            return true
        }else if(id == R.id.notify){
            val intent = Intent(this,notification::class.java)
            startActivity(intent)
            return true
        }else if(id == R.id.refresh){
            loadweb()
            Toast.makeText(this, "Loading Home.....", Toast.LENGTH_SHORT).show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun replaceFragment(Fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentCointainer,Fragment)
        fragmentTransaction.commit()
    }
}