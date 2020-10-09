package com.example.portfolia.ui

import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.portfolia.R
import com.example.portfolia.database.Entity.RegistrationEntity
import com.example.portfolia.ui.activity.MainActivity
import com.example.portfolia.ui.dialogfragment.AboutFragment
import com.example.portfolia.ui.dialogfragment.MyProfileFragment
import com.example.portfolia.ui.dialogfragment.PdfPortfoliaFragment
import com.example.portfolia.util.Constants
import com.example.portfolia.util.Function
import com.example.restaurants.ui.preference.RegisterPreference
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView

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
    var linear_drop:LinearLayout?=null
    var linear_drop_passport:LinearLayout?=null
    var string_usri=""
    // viewmodel
    lateinit var viewModel: RegisterViewModel
    var registrationEntity: List<RegistrationEntity>?=null
    // Header
    var profile_image_header:ImageView?=null

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
        linear_drop=view.findViewById(R.id.linear_drop)
        linear_drop_passport=view.findViewById(R.id.linear_drop_passport)
        val header:View=navigationView!!.getHeaderView(0)
        profile_image_header=header.findViewById(R.id.profile_image_header)
        if (preference!!.getRegistration(Constants.IS_REGISTERED)!!){
            Log.d(TAG, "registered")
            viewModel.getRegistration()
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer { response ->
                    registrationEntity = response
                    Log.d(TAG, "Response:" + registrationEntity.toString())
                    var ss = ""
                    var dd = ""
                    for (i in registrationEntity!!) {
                        ss = i.image_uri
                        dd = i.name
                    }
                    username!!.text = dd
                    string_usri = ss
                    val requestOptions = RequestOptions()
                        .placeholder(R.color.card1)
                        .error(R.color.card3)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                    Glide.with(requireActivity())
                        .applyDefaultRequestOptions(requestOptions)
                        .load(Uri.parse(ss))
                        .into(profile_image_home!!)

                    // Header Glide
                    val requestOptions2 = RequestOptions()
                        .placeholder(R.color.card1)
                        .error(R.color.card4)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()

                    Glide.with(requireActivity())
                        .applyDefaultRequestOptions(requestOptions2)
                        .load(Uri.parse(ss))
                        //.transition(DrawableTransitionOptions.withCrossFade())
                        //  .into(profile_image_header!!)
                })

        }else    Log.d(TAG, "unregistered")

        // OnClick methods
        view.findViewById<LinearLayout>(R.id.dossier_linear).setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_dossierFragment)
        }
        view.findViewById<RelativeLayout>(R.id.relative_background_learning).setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_languageBackFragment2)
        }
        view.findViewById<View>(R.id.summary_of_mylanguage).setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_summaryofLanguageFragment)
        }

        view.findViewById<View>(R.id.linear_language).setOnClickListener {
            if (linear_drop!!.visibility==View.VISIBLE){
                linear_drop!!.visibility=View.GONE
            }else  linear_drop!!.visibility=View.VISIBLE
        }

        view.findViewById<View>(R.id.linear_passport).setOnClickListener {
            if (linear_drop_passport!!.visibility==View.VISIBLE){
                linear_drop_passport!!.visibility=View.GONE
            }else  linear_drop_passport!!.visibility=View.VISIBLE
        }

        view.findViewById<View>(R.id.relative_home).setOnClickListener {
            val dial4: DialogFragment = MyProfileFragment()
            dial4.show(childFragmentManager, "profile")
        }

        view.findViewById<View>(R.id.relative_self_assesment_grid).setOnClickListener {
            this.findNavController().navigate(
                R.id.action_homeFragment_to_selfAssessmentGridFragment
            )
        }
        view.findViewById<View>(R.id.relative_learning_aims).setOnClickListener {
            this.findNavController().navigate(
                R.id.action_homeFragment_to_learningAimsFragment
            )
        }
        view.findViewById<RelativeLayout>(R.id.self_assesment).setOnClickListener {
            this.findNavController().navigate(
                R.id.action_homeFragment_to_selfAssessmentFragment
            )
        }

        val headerView:View=navigationView!!.getHeaderView(0)
        navigationDraver()

        /*
        view.findViewById<View>(R.id.linear_language).setOnClickListener {
            Function.showToast(requireActivity(),"File $string_usri")
            val file = File(Uri.parse(string_usri).path)
            val deleted:Boolean=file.delete()
            if (file.exists()){
                file.canonicalFile.delete()
                if (file.exists()){
                    requireActivity().deleteFile(file.name)
                }
            }
            if (deleted){
                Function.showToast(requireActivity(),"Deleted")
            }else  Function.showToast(requireActivity(),"Not Deleted")
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    if (drawerLayout!!.isDrawerVisible(GravityCompat.START)) {
                        drawerLayout!!.closeDrawer(GravityCompat.START)
                    } else requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
*/
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
                val dial: DialogFragment = AboutFragment()
                dial.show(childFragmentManager, "about")
            }
            R.id.close_app -> {

                val bottomSheet = BottomSheetDialog(requireActivity(), R.style.BottomsheetDemo)
                val view1 = LayoutInflater.from(requireActivity())
                    .inflate(
                        R.layout.layout_bottom_sheet,
                        view?.findViewById<View>(R.id.bottomSheetContainer) as LinearLayout?
                    )
                view1.findViewById<View>(R.id.btn_dismiss).setOnClickListener {
                    bottomSheet.dismiss()
                }
                view1.findViewById<View>(R.id.btn_close).setOnClickListener {
                    val preferences2: SharedPreferences =
                        requireActivity().getSharedPreferences(
                            R.string.restaurant_file.toString(), 0
                        )
                    preferences2.edit().clear().apply()
                    viewModel.deleteReg()
                    requireActivity().deleteDatabase("portfolia_db.db")

                    Function.showToast(requireActivity(), "Log out")
                    requireActivity().finish()
                }
                bottomSheet.setContentView(view1)
                bottomSheet.show()
            }
            R.id.pdf_portfolia -> {
                val dial1: DialogFragment = PdfPortfoliaFragment()
                dial1.show(childFragmentManager, "pdf")
            }
            R.id.home -> {
                drawerLayout!!.closeDrawer(GravityCompat.START)
            }
/*
            R.id.settings -> {
                val dial: DialogFragment = SettingsFragment()
                dial.show(childFragmentManager, "settings")
            }
 */

            R.id.my_profile -> {
                val dial4: DialogFragment = MyProfileFragment()
                dial4.show(childFragmentManager, "profile")
            }

        }
        return true
    }

    fun onBackPressed() {
        if (drawerLayout!!.isDrawerVisible(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else super.requireActivity().onBackPressed()
    }

}