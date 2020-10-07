package com.example.portfolia.ui.languagePassport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.portfolia.R
import kotlinx.android.synthetic.main.fragment_self_assessment_grid.*


class SelfAssessmentGridFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {return inflater.inflate(R.layout.fragment_self_assessment_grid, container, false)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_self_assessment.setOnClickListener {this.findNavController().popBackStack()}
        cardview_listening.setOnClickListener {
            if (table_listening.visibility==View.GONE){
                table_listening.visibility=View.VISIBLE
            }else table_listening.visibility=View.GONE
        }
        cardview_reading.setOnClickListener {
            if (table_reading.visibility==View.GONE){
                table_reading.visibility=View.VISIBLE
            }else table_reading.visibility=View.GONE
        }
        cardview_spoken_production.setOnClickListener {
            if (table_spoken_production.visibility==View.GONE){
                table_spoken_production.visibility=View.VISIBLE
            }else table_spoken_production.visibility=View.GONE
        }
        cardview_spoken_interaction.setOnClickListener {
            if (table_spoken_interaction.visibility==View.GONE){
                table_spoken_interaction.visibility=View.VISIBLE
            }else table_spoken_interaction.visibility=View.GONE
        }

        cardview_writing.setOnClickListener {
            if (table_writing.visibility==View.GONE){
                table_writing.visibility=View.VISIBLE
            }else table_writing.visibility=View.GONE
        }

    }
}