package nccstory.ml.androidapp


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * A simple [Fragment] subclass.
 */
class ProductsFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)


    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myWebView: WebView?
        myWebView = view.findViewById(R.id.webView2)
        myWebView.settings.javaScriptEnabled = true
        webView2.webViewClient = WebViewClient()
        webView2.loadUrl("https://www.nccstory.ml/p/products.html")
        webView2.canGoBack()
        webView2.goBack()
        /*progressBar4.visibility = View.VISIBLE
        Timer("settingup",false).schedule(6000){
            progressBar4.visibility = View.INVISIBLE
        }*/

    }
    @Suppress("OverridingDeprecatedMember")
    class MyWebViewClient : WebViewClient(){

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            view?.visibility = View.INVISIBLE
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

    }


}