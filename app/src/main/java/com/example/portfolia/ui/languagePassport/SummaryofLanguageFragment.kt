package com.example.portfolia.ui.languagePassport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.portfolia.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_persistent.*
import kotlinx.android.synthetic.main.fragment_summaryof_language.*


class SummaryofLanguageFragment : Fragment() {

    var sheetBehavior: BottomSheetBehavior<*>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_summaryof_language, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        add_data.setOnClickListener {
            if ((sheetBehavior as BottomSheetBehavior<*>).state==BottomSheetBehavior.STATE_EXPANDED){
                (sheetBehavior as BottomSheetBehavior<*>).state=BottomSheetBehavior.STATE_COLLAPSED
            }else if ((sheetBehavior as BottomSheetBehavior<*>).state==BottomSheetBehavior.STATE_COLLAPSED){
                (sheetBehavior as BottomSheetBehavior<*>).state=BottomSheetBehavior.STATE_EXPANDED
            }
        }



    }

}