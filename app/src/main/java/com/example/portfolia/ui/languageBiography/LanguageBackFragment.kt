package com.example.portfolia.ui.languageBiography

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolia.R
import com.example.portfolia.adapter.AimsAdapter
import com.example.portfolia.adapter.CertificateAdapter
import com.example.portfolia.adapter.DiaryAdapter
import com.example.portfolia.ui.activity.MainActivity
import com.example.portfolia.util.Function
import com.example.restaurants.ui.viewmodel.RegisterViewModel

class LanguageBackFragment : Fragment() {
    lateinit var adapter:DiaryAdapter
    lateinit var viewModel: RegisterViewModel
    lateinit var recyclerview: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    val TAG="LanguageBackFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?)
            :View?{return inflater.inflate(R.layout.fragment_language_back, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as MainActivity).viewModel
        recyclerview=view.findViewById(R.id.recyclerview_diary)
        layoutManager= LinearLayoutManager(activity)
        recyclerview.layoutManager = layoutManager
        recyclerview.apply {
            itemAnimator= DefaultItemAnimator()
            isNestedScrollingEnabled=false
            setHasFixedSize(true)
        }
        viewModel.getDiary().observe(viewLifecycleOwner, Observer {response->
            Log.e(TAG,response.toString())
            if (response.size>0){
                adapter= DiaryAdapter(requireActivity(),response)
                recyclerview.adapter=adapter
                adapter.notifyDataSetChanged()
            }else{
               Function.showToast(requireActivity(),"No data found !!!")
            }
        })

    }
}