package com.example.portfolia.ui.dialogfragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.portfolia.R
import com.example.portfolia.database.Entity.RegistrationEntity
import com.example.portfolia.database.MainDatabase
import com.example.portfolia.repository.RegisterRepository
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import com.example.restaurants.ui.viewmodel.RegisterViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyProfileFragment : DialogFragment() {

    lateinit var viewModel: RegisterViewModel
    var registrationEntity: List<RegistrationEntity>?=null
    val TAG="MyProfileFragment"
    var imageView:ImageView?=null
    var floatactionButton:FloatingActionButton?=null
    var image_uri=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullscreenDialogtheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view:View=inflater.inflate(R.layout.fragment_my_profile, container, false)
        val repository= RegisterRepository(MainDatabase(requireActivity()))
        val viewModelProviderFactory = RegisterViewModelFactory(requireActivity().application,repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(RegisterViewModel::class.java)
        imageView=view.findViewById(R.id.picture_myprofile)
        floatactionButton=view.findViewById(R.id.fabChoosePic_myprofile)
        viewModel.getRegistration()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { response ->
                registrationEntity = response
                Log.d(TAG, "Response:" + registrationEntity.toString())
                var ss = ""
                for (i in registrationEntity!!) {
                    ss = i.image_uri
                    view.findViewById<EditText>(R.id.name_myprofile).setText(i.name)
                    view.findViewById<EditText>(R.id.date_of_birth_myprofile).setText(i.date_of_birth)
                    view.findViewById<EditText>(R.id.place_of_birth_myprofile).setText(i.date_of_birth)
                    view.findViewById<EditText>(R.id.nationality_myprofile).setText(i.nationality)
                    view.findViewById<EditText>(R.id.mother_tongue_myprofile).setText(i.mother_tongue)
                    view.findViewById<EditText>(R.id.date_started_myprofile).setText(i.date_using)
                    view.findViewById<EditText>(R.id.date_of_birth_myprofile).setText(i.date_of_birth)
                    val requestOptions = RequestOptions()
                        .placeholder(R.color.card1)
                        .error(R.color.card3)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()

                    Glide.with(requireActivity())
                        .applyDefaultRequestOptions(requestOptions)
                        .load(Uri.parse(ss))
                       .into(imageView!!)
                    image_uri=ss
                }
            })

        view.findViewById<View>(R.id.edit_profile).setOnClickListener {
          if (floatactionButton!!.visibility==View.GONE){
              floatactionButton!!.visibility=View.VISIBLE
              view.findViewById<EditText>(R.id.name_myprofile).isEnabled=true
              view.findViewById<EditText>(R.id.date_of_birth_myprofile).isEnabled=true
              view.findViewById<EditText>(R.id.place_of_birth_myprofile).isEnabled=true
              view.findViewById<EditText>(R.id.nationality_myprofile).isEnabled=true
              view.findViewById<EditText>(R.id.mother_tongue_myprofile).isEnabled=true
              view.findViewById<EditText>(R.id.date_started_myprofile).isEnabled=true
              view.findViewById<EditText>(R.id.date_of_birth_myprofile).isEnabled=true
              view.findViewById<View>(R.id.save_reg_myprofile).isEnabled=true
          }else {
              floatactionButton!!.visibility=View.GONE
              view.findViewById<EditText>(R.id.name_myprofile).isEnabled=false
              view.findViewById<EditText>(R.id.date_of_birth_myprofile).isEnabled=false
              view.findViewById<EditText>(R.id.place_of_birth_myprofile).isEnabled=false
              view.findViewById<EditText>(R.id.nationality_myprofile).isEnabled=false
              view.findViewById<EditText>(R.id.mother_tongue_myprofile).isEnabled=false
              view.findViewById<EditText>(R.id.date_started_myprofile).isEnabled=false
              view.findViewById<EditText>(R.id.date_of_birth_myprofile).isEnabled=false
              view.findViewById<View>(R.id.save_reg_myprofile).isEnabled=false
              view.findViewById<View>(R.id.save_reg_myprofile).isFocusable=false

          }

            view.findViewById<View>(R.id.save_reg_myprofile).setOnClickListener {
                val registrationEntity=RegistrationEntity(
                    view.findViewById<EditText>(R.id.name_myprofile).text.toString(),
                    view.findViewById<EditText>(R.id.date_of_birth_myprofile).text.toString(),
                    view.findViewById<EditText>(R.id.place_of_birth_myprofile).text.toString(),
                    view.findViewById<EditText>(R.id.nationality_myprofile).text.toString(),
                    view.findViewById<EditText>(R.id.mother_tongue_myprofile).text.toString(),
                    view.findViewById<EditText>(R.id.date_started_myprofile).text.toString(),
                    image_uri)
                    viewModel.updateRegister(registrationEntity)
                    dismiss()
            }
        }

        view.findViewById<View>(R.id.back_profile).setOnClickListener {dismiss()}
        return view
    }

    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
    }

}