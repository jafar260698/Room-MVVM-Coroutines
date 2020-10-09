package com.example.portfolia.ui.dossier

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolia.R
import com.example.portfolia.adapter.CertificateAdapter
import com.example.portfolia.database.Entity.Certificate
import com.example.portfolia.ui.activity.MainActivity
import com.example.portfolia.util.Function
import com.example.restaurants.ui.viewmodel.RegisterViewModel

class MyCertificatesFragment : Fragment(),CertificateAdapter.OnItemClickListener {
    lateinit var adapter:CertificateAdapter
    lateinit var recyclerview: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
     var viewModel:RegisterViewModel?=null
    val TAG="MyCertificatesFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):View?{
        val view:View= inflater.inflate(R.layout.fragment_my_certificates, container, false)
        viewModel= (activity as MainActivity).viewModel
        recyclerview=view.findViewById(R.id.recyclerview_certificate)
        layoutManager= LinearLayoutManager(activity)
        recyclerview.layoutManager = layoutManager
        recyclerview.apply {
            itemAnimator= DefaultItemAnimator()
            isNestedScrollingEnabled=false
            setHasFixedSize(true)
        }
        view.findViewById<View>(R.id.back_cer).setOnClickListener {
            this.findNavController().popBackStack()
        }
        Log.e(TAG,"Before Viewmodel"+"It")
        viewModel?.getCertificate()?.observe(viewLifecycleOwner, Observer {list->
            adapter= CertificateAdapter(requireActivity(),list,this)
            recyclerview.adapter=adapter
            adapter.notifyDataSetChanged()
            Log.e(TAG,list.toString()+"Viewmodel inside")
        })

        view.findViewById<View>(R.id.float_category).setOnClickListener {this.findNavController().navigate(R.id.action_myCertificatesFragment_to_certificateAddFragment)}
        return view
    }
    override fun onItemClick(item: Certificate, position: Int) {
        Function.showToast(requireActivity(),item.file_uri.toString())

        val browserIntent = Intent(Intent.ACTION_VIEW)
        browserIntent.setDataAndType(Uri.parse(item.file_uri), "application/pdf")
        val chooser = Intent.createChooser(browserIntent, "Pdf ochadigan dasturni tanlang !!!")
        chooser.flags = Intent.FLAG_ACTIVITY_NEW_TASK // optional
        startActivity(chooser)
    }

}