package com.example.portfolia.ui

import android.graphics.Color
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.portfolia.R
import com.example.portfolia.database.Entity.RegistrationEntity
import com.example.portfolia.database.MainDatabase
import com.example.portfolia.repository.RegisterRepository
import com.example.portfolia.ui.dialogfragment.PdfPortfoliaFragment
import com.example.portfolia.util.Constants
import com.example.restaurants.ui.preference.RegisterPreference
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import com.example.restaurants.ui.viewmodel.RegisterViewModelFactory
import com.google.android.material.navigation.NavigationView
import java.util.*


class HomeFragment : Fragment(),NavigationView.OnNavigationItemSelectedListener {
    var drawerLayout: DrawerLayout? = null
    var navigationView: NavigationView? = null
    lateinit var contentView: RelativeLayout
    var preference :RegisterPreference?=null

    var menu_item: ImageView? = null
    var profile_image_home:ImageView?=null
    val END_SCALE = 0.7f
    val TAG="HomeFragment"
    var username: TextView?=null
    // viewmodel
    lateinit var viewModel: RegisterViewModel
    var registrationEntity: List<RegistrationEntity>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.fragment_home, container, false)
        viewModel= (activity as MainActivity).viewModel
        drawerLayout = view.findViewById(R.id.drawer_layout)
        preference= RegisterPreference(requireActivity())
        contentView=view.findViewById(R.id.content)
        navigationView = view.findViewById(R.id.navigation_view)
        menu_item=view.findViewById(R.id.menu_icon)
        profile_image_home=view.findViewById(R.id.profile_image_home)
        username=view.findViewById(R.id.username)
        view.findViewById<LinearLayout>(R.id.dossier_linear).setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_dossierFragment)
        }

        if (preference!!.getRegistration(Constants.IS_REGISTERED)!!){
            Log.d(TAG,"registered")
            viewModel.getRegistration()
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer { response->
                    registrationEntity=response
                    Log.d(TAG,"Response:"+registrationEntity.toString())
                    var ss=""
                    var dd=""
                    for (i in registrationEntity!!){
                        ss=i.image_uri
                        dd=i.name
                    }
                    username!!.text=dd
                    val requestOptions = RequestOptions()
                        .placeholder(R.color.card1)
                        .error(R.color.card3)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()

                    Glide.with(requireActivity())
                        .applyDefaultRequestOptions(requestOptions)
                        .load(Uri.parse(ss))
                        .into(profile_image_home!!)
                })
        }else    Log.d(TAG,"unregistered")

        view.findViewById<View>(R.id.relative_home).setOnClickListener {
            this.findNavController().navigate(
            R.id.action_homeFragment_to_myProfileFragment
        ) }
        val headerView:View=navigationView!!.getHeaderView(0)
        navigationDraver()

        return view
    }

    private fun navigationDraver() {
        navigationView!!.bringToFront()
        navigationView!!.setNavigationItemSelectedListener(this)
        navigationView!!.setCheckedItem(R.id.navigation_view)
        menu_item!!.setOnClickListener {
            if (drawerLayout!!.isDrawerVisible(GravityCompat.START)) drawerLayout!!.closeDrawer(
                GravityCompat.START
            ) else drawerLayout!!.openDrawer(GravityCompat.START)

        }
        animateNavigationDrawer()
    }

    private fun animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout!!.setScrimColor(Color.TRANSPARENT)
        drawerLayout!!.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

                // Scale the View based on current slide offset
                val diffScaledOffset: Float = slideOffset * (1 - END_SCALE)
                val offsetScale = 1 - diffScaledOffset
                contentView.setScaleX(offsetScale)
                contentView.setScaleY(offsetScale)

                // Translate the View, accounting for the scaled width
                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff: Float = contentView.getWidth() * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                contentView.setTranslationX(xTranslation)
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        when(id){
            R.id.about_app -> {
            }
            R.id.close_app -> {
            }
            R.id.pdf_portfolia->{
                val dial1: DialogFragment =PdfPortfoliaFragment()
                dial1.show(childFragmentManager, "pdf")
            }
        }
        return true
    }

}