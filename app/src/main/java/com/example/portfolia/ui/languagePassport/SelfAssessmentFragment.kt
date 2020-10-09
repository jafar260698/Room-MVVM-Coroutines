package com.example.portfolia.ui.languagePassport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.portfolia.R
import com.example.portfolia.database.Entity.SelfAssesment
import com.example.portfolia.ui.activity.MainActivity
import com.example.portfolia.util.Constants
import com.example.portfolia.util.Function
import com.example.restaurants.ui.preference.RegisterPreference
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_self_assessment.*

class SelfAssessmentFragment : Fragment() {
    lateinit var viewModel: RegisterViewModel
    var preference: RegisterPreference?=null
    var listening=""
    var reading=""
    var spoken_interaction=""
    var spoken_production=""
    var writing=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_self_assessment, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as MainActivity).viewModel
        preference= RegisterPreference(requireActivity())
        back_self_assessment.setOnClickListener {
            this.findNavController().popBackStack()
        }

        val is_firstTime = preference!!.getSelfAssessment(Constants.IS_SELF_ASSESSED)
        if (!is_firstTime!!){
            linear_myscore.visibility=View.GONE
            linear_radiobutton.visibility=View.VISIBLE
        }else{
            linear_radiobutton.visibility=View.GONE
            linear_myscore.visibility=View.VISIBLE
            viewModel.getSelf_Assessment().observe(viewLifecycleOwner, Observer {response->
                if (response.size>0){
                    for (i in response){
                        listening_self.text="Listening: "+i.listening
                        reading_self.text="Reading: "+i.reading
                        spoken_interaction_self.text="Spoken Interaction: "+i.spoken_interaction
                        spoken_production_self.text="Spoken Production: "+i.spoken_production
                        writing_self.text="Writing: "+i.writing
                    }
                }
            })
        }
        save_self_assesment.setOnClickListener {
            val selectedlistening:Int=radioGroup_listening.checkedRadioButtonId
            val selectedreading:Int=radioGroup_reading.checkedRadioButtonId
            val selectedspoken_interaction:Int=radioGroup_spoken_interaction.checkedRadioButtonId
            val selectedspoken_production:Int=radioGroup_spoken_production.checkedRadioButtonId
            val selectedwriting:Int=radioGroup_writing.checkedRadioButtonId

            when(selectedlistening){
                R.id.listening_a1-> listening=listening_a1.getText().toString()
                R.id.listening_a2-> listening=listening_a2.getText().toString()
                R.id.listening_b1-> listening=listening_b1.getText().toString()
                R.id.listening_b2-> listening=listening_b2.getText().toString()
                R.id.listening_c1-> listening=listening_c1.getText().toString()
            }
            when(selectedreading){
                R.id.reading_a1-> reading=reading_a1.getText().toString()
                R.id.reading_a2-> reading=reading_a2.getText().toString()
                R.id.reading_b1-> reading=reading_b1.getText().toString()
                R.id.reading_b2-> reading=reading_b2.getText().toString()
                R.id.reading_c1-> reading=reading_c1.getText().toString()
            }
            when(selectedspoken_interaction){
                R.id.spoken_interaction_a1-> spoken_interaction=spoken_interaction_a1.getText().toString()
                R.id.spoken_interaction_a2-> spoken_interaction=spoken_interaction_a2.getText().toString()
                R.id.spoken_interaction_b1-> spoken_interaction=spoken_interaction_b1.getText().toString()
                R.id.spoken_interaction_b2-> spoken_interaction=spoken_interaction_b2.getText().toString()
                R.id.spoken_interaction_c1-> spoken_interaction=spoken_interaction_c1.getText().toString()
            }
            when(selectedspoken_production){
                R.id.spoken_production_a1-> spoken_production=spoken_production_a1.getText().toString()
                R.id.spoken_production_a2-> spoken_production=spoken_production_a1.getText().toString()
                R.id.spoken_production_b1-> spoken_production=spoken_production_a1.getText().toString()
                R.id.spoken_production_b2-> spoken_production=spoken_production_a1.getText().toString()
                R.id.spoken_production_c1-> spoken_production=spoken_production_a1.getText().toString()
            }
            when(selectedwriting){
                R.id.writing_a1-> writing=writing_a1.getText().toString()
                R.id.writing_a2-> writing=writing_a2.getText().toString()
                R.id.writing_b1-> writing=writing_b1.getText().toString()
                R.id.writing_b2-> writing=writing_b2.getText().toString()
                R.id.writing_c1-> writing=writing_c1.getText().toString()
            }
        if (listening.isNotEmpty()
            &&reading.isNotEmpty()
            &&spoken_interaction.isNotEmpty()
            &&spoken_production.isNotEmpty()
            &&writing.isNotEmpty()){
            val self_asses:SelfAssesment=
                SelfAssesment(listening,reading,spoken_interaction,spoken_production,writing)
            viewModel.saveSelf_Assess(self_asses)
            preference!!.setSelfAssessment(Constants.IS_SELF_ASSESSED,true)
            this.findNavController().popBackStack()
        }else{
            Function.showToast(requireActivity(),"All items should be filled !!!")
        }

        }
    }


}