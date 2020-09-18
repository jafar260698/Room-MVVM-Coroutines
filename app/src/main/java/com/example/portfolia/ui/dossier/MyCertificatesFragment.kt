package com.example.portfolia.ui.dossier


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.portfolia.R

class MyCertificatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.fragment_my_certificates, container, false)

       view.findViewById<View>(R.id.float_category).setOnClickListener {
           this.findNavController().navigate(R.id.action_myCertificatesFragment_to_certificateAddFragment)
       }

        return view
    }
}