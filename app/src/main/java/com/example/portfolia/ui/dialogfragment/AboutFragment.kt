package com.example.portfolia.ui.dialogfragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.portfolia.R
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogtheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        about_back.setOnClickListener {dismiss()}
        val sourceString:String=" Electronic version of UzELP has been developed on the basis of Uzbek Model of European Language Portfolio"+" (Urazbaev Khikmatullo, copyright certificate #001657)"
        about_app_title.setText(Html.fromHtml(sourceString))
    }

    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
    }

}