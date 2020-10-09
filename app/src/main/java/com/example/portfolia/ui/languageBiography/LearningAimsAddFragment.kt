package com.example.portfolia.ui.languageBiography

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.portfolia.R
import com.example.portfolia.database.Entity.MyAims
import com.example.portfolia.ui.activity.MainActivity
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_learning_aims_add.*

class LearningAimsAddFragment : Fragment() {
    lateinit var viewModel: RegisterViewModel
    var spinner:Spinner?=null
    val TAG="LearningAimsAddFragment"
    var language=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {return inflater.inflate(R.layout.fragment_learning_aims_add, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as MainActivity).viewModel
        spinner=view.findViewById(R.id.spinner_aims)
        val arguments=arguments?.getString("idsi")
        if (arguments=="2"){
            title_aims.text="Update aims"
            edit_aims.visibility=View.VISIBLE
            delete_aims.visibility=View.VISIBLE
            save_aims.text="Update"
        }

        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("English")
        arrayList.add("Russian")
        arrayList.add("German")
        arrayList.add("French")
        arrayList.add("Chinese")
        arrayList.add("Spanish")
        val arrayAdapter= ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, arrayList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner!!.adapter = arrayAdapter
        spinner!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selection = parent.getItemAtPosition(position) as String
                Log.d(TAG, "Selecton $selection")
                if (!TextUtils.isEmpty(selection)) {
                     language=selection
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        save_aims.setOnClickListener {
            val aims:MyAims= MyAims(
                language,
                reason_of_learning.text.toString(),
                exact_thing.text.toString(),
                cef_level.text.toString(),
                purpose_language.text.toString())
            viewModel.saveAims(aims)
            this.findNavController().popBackStack()
        }


    }

}