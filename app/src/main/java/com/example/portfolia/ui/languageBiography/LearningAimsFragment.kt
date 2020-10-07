package com.example.portfolia.ui.languageBiography

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
import com.example.portfolia.adapter.AimsAdapter
import com.example.portfolia.ui.MainActivity
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_learning_aims.*


class LearningAimsFragment : Fragment(){
    lateinit var aimsAdapter: AimsAdapter
    lateinit var recyclerview: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    var viewModel: RegisterViewModel?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learning_aims, container, false) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_add.setOnClickListener {  this.findNavController().navigate(
            R.id.action_learningAimsFragment_to_learningAimsAddFragment
        ) }
        back_aims.setOnClickListener {this.findNavController().popBackStack()}

        viewModel= (activity as MainActivity).viewModel
        recyclerview=view.findViewById(R.id.recyclerview_learning_aims)
        layoutManager= LinearLayoutManager(activity)
        recyclerview.layoutManager = layoutManager
        recyclerview.apply {
            itemAnimator= DefaultItemAnimator()
            isNestedScrollingEnabled=false
            setHasFixedSize(true)
        }
        viewModel?.getAims()?.observe(viewLifecycleOwner, Observer { response ->
            aimsAdapter = AimsAdapter(requireActivity(), response)
            recyclerview.adapter = aimsAdapter
            Log.d("TAG", response.toString() + "Response::")
            aimsAdapter.notifyDataSetChanged()
        })

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && fab_add.isShown()) fab_add.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) fab_add.show()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
}