package com.example.portfolia.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.portfolia.R


class DossierFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.fragment_dossier, container, false)
        view.findViewById<View>(R.id.linear_mycertificate).setOnClickListener {
            this.findNavController().navigate(R.id.action_dossierFragment_to_myCertificatesFragment)
        }

        return view
    }

}