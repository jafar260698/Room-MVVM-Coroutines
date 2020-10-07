package com.example.portfolia.ui.dossier

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.portfolia.R
import com.example.portfolia.database.Entity.Certificate
import com.example.portfolia.database.Entity.Writing
import com.example.portfolia.ui.MainActivity
import com.example.portfolia.util.Constants
import com.example.portfolia.util.FileUtil
import com.example.portfolia.util.Function
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.android.synthetic.main.fragment_writing_add.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.OutputStream

class WritingAddFragment : Fragment() {
    lateinit var viewModel: RegisterViewModel
    var outputStream: OutputStream?=null
    private var actualpdfpath: String? = null
    val TAG="WritingAddFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_writing_add, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as MainActivity).viewModel

        upload_file_writing.setOnClickListener { chooseFile() }
        save_writing.setOnClickListener {
            if (type_of_document_writing!!.text.toString().trim().isNotEmpty()
                && level_writing!!.text.toString().trim().isNotEmpty()
                && date_of_obtained_writing!!.text.toString().trim().isNotEmpty()
            ){
                if(actualpdfpath!=null&&actualpdfpath!!.trim().isNotEmpty()){
                    val certificate= Writing(
                        type_of_document_writing!!.text.toString().trim(),
                        level_writing!!.text.toString().trim(),
                        date_of_obtained_writing!!.text.toString().trim(),
                        actualpdfpath!!)
                    viewModel.saveWriting(certificate)
                    Log.d(TAG, "Writing:: $certificate")
                    Function.showToast(requireActivity(),getString(R.string.file_saved))
                    this.findNavController().popBackStack()
                }else{
                    Function.showToast(requireActivity(),getString(R.string.file_must_be_uploaded))
                }

            }else{
                Function.showToast(requireActivity(),getString(R.string.file_must_not_be_loaded))
            }
        }

    }

    private fun chooseFile(){
        Intent(Intent.ACTION_GET_CONTENT).also {
            it.type="application/pdf"
            it.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(
                Intent.createChooser(it, "Select PDF"), Constants.PDF_SELECTION_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedPdfFromStorage = data.data
            saveImageToStorage(selectedPdfFromStorage)
        }
    }

    private fun saveImageToStorage(uri: Uri?){
        Log.d(TAG,"saveImageToStorage")
        val filePath: File = Environment.getExternalStorageDirectory()
        val dir= File(filePath.absolutePath+"/Porfolia/Writing/")
        dir.mkdirs()
        val filerr: File = FileUtil.from(requireActivity(),uri)
        val file= File(dir,System.currentTimeMillis().toString()+".pdf")
        try {
            outputStream= FileOutputStream(file)
        }catch (e: FileNotFoundException){
            e.printStackTrace()
            Log.e(TAG,e.localizedMessage)
        }
        actualpdfpath= Uri.fromFile(file).toString()

        Log.e(TAG, Uri.fromFile(file).toString())
        Log.e(TAG,file.path.toString())
        //   bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
        outputStream?.write(filerr.readBytes())
        outputStream?.flush()
        outputStream?.close()
    }

}