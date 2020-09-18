package com.example.portfolia.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.portfolia.R
import com.example.portfolia.util.Constants
import com.example.restaurants.ui.preference.RegisterPreference

class SplashFragment : Fragment() {
    private val SPLASH_SCREEN =4600
    var title: TextView?=null
    var e_version:TextView?=null
    var lottie:LottieAnimationView?=null
    var back_image:ImageView?=null
    var preference:RegisterPreference?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view:View=inflater.inflate(R.layout.fragment_splash, container, false)
        title=view.findViewById(R.id.title_splash)
        lottie=view.findViewById(R.id.lottie)
        e_version=view.findViewById(R.id.e_version)
        back_image=view.findViewById(R.id.imageview)
        preference= RegisterPreference(requireActivity())
        lottie!!.animate().translationY(-1600F).setDuration(1000).startDelay = 4000
        title!!.animate().translationY(1400F).setDuration(1000).startDelay = 4000
        back_image!!.animate().translationY(1400F).setDuration(1000).startDelay = 4000
        e_version!!.animate().translationY(1400F).setDuration(1000).startDelay = 4000


        Handler().postDelayed({

            val is_firstTime= preference!!.getRegistration(Constants.IS_REGISTERED)
            if (is_firstTime!!){
                this.findNavController().navigate(R.id.action_splashFragment_to_homeFragment,null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashFragment,
                            true).build()
                )
            }else{
                this.findNavController().navigate(R.id.action_splashFragment_to_registerFragment,null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashFragment,
                            true).build()
                )
            }


        },SPLASH_SCREEN.toLong())
        return view
    }

}