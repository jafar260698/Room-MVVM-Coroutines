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
import com.example.portfolia.adapter.WritingAdapter
import com.example.portfolia.database.Entity.Writing
import com.example.portfolia.ui.activity.MainActivity
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_writing.*


class WritingFragment : Fragment(),WritingAdapter.OnItemClickListener {

    lateinit var adapter: WritingAdapter
    lateinit var recyclerview: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    var viewModel: RegisterViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{return inflater.inflate(R.layout.fragment_writing, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as MainActivity).viewModel
        recyclerview=view.findViewById(R.id.recyclerview_writing)
        layoutManager= LinearLayoutManager(activity)
        recyclerview.layoutManager = layoutManager
        recyclerview.apply {
            itemAnimator= DefaultItemAnimator()
            isNestedScrollingEnabled=false
            setHasFixedSize(true)
        }

        viewModel!!.getWriting().observe(viewLifecycleOwner, Observer {
            adapter = WritingAdapter(requireActivity(), it, this)
            recyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
            Log.e("TAG", it.toString() + "Resposne")
        })
        view.findViewById<View>(R.id.float_writing).setOnClickListener {
            this.findNavController().navigate(R.id.action_writingFragment_to_writingAddFragment)
        }
        back_all_writing.setOnClickListener {
            this.findNavController().popBackStack()
        }
    }

    override fun onItemClick(item: Writing, position: Int) {
       // Function.showToast(requireActivity(), "Position: ${item.file_uri}")
        val browserIntent = Intent(Intent.ACTION_VIEW)
        browserIntent.setDataAndType(Uri.parse(item.file_uri), "application/pdf")
        val chooser = Intent.createChooser(browserIntent, "Pdf ochadigan dasturni tanlang !!!")
        chooser.flags = Intent.FLAG_ACTIVITY_NEW_TASK // optional
        startActivity(chooser)
    }


}