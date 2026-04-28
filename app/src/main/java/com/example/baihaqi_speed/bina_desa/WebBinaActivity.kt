package com.example.baihaqi_speed.bina_desa

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityWebBinaBinding

class WebBinaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinaBinding

    companion object {
        const val EXTRA_URL   = "extra_url"
        const val EXTRA_TITLE = "extra_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url   = intent.getStringExtra(EXTRA_URL) ?: "https://binades.id"
        val title = intent.getStringExtra(EXTRA_TITLE) ?: "Bina Desa"

        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            this.title = title
            subtitle = url
        }

        // WebView setup
        binding.webView.apply {
            settings.javaScriptEnabled    = true
            settings.domStorageEnabled    = true
            settings.useWideViewPort      = true
            settings.loadWithOverviewMode = true
            settings.builtInZoomControls  = true
            settings.displayZoomControls  = false

            webViewClient = WebViewClient()

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, newProgress: Int) {
                    binding.progressWeb.progress = newProgress
                    binding.progressWeb.visibility =
                        if (newProgress < 100) View.VISIBLE else View.GONE
                }
            }

            loadUrl(url)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
        return true
    }
}