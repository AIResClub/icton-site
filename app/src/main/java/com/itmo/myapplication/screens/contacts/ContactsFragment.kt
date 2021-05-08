package com.itmo.myapplication.screens.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.itmo.myapplication.R


class ContactsFragment: Fragment(R.layout.contacts_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.contacts_fragment, container, false)
        var mWebView = v.findViewById<View>(R.id.webview) as WebView
        mWebView.loadUrl("https://dict.ifmo.ru/contacts/")

        val webSettings: WebSettings = mWebView.getSettings()
        webSettings.javaScriptEnabled = true

        mWebView.setWebViewClient(WebViewClient())
        return v
    }
}