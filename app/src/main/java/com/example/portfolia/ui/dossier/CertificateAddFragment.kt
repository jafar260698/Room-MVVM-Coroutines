package com.example.portfolia.ui.dossier

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.portfolia.R
import com.example.portfolia.ui.MainActivity
import com.example.portfolia.util.Constants
import com.example.portfolia.util.Constants.Companion.PDF_SELECTION_CODE
import com.example.portfolia.util.FileUtil
import com.example.portfolia.util.Function
import com.github.barteksc.pdfviewer.PDFView
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.OutputStream

class CertificateAddFragment : Fragment() {
    lateinit var viewModel:ViewModel
    var pdfView :PDFView?=null
    var outputStream: OutputStream?=null
    private var actualImage1: File? = null

    val TAG="CertificateAddFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_certificate_add, container, false)
        viewModel= (activity as MainActivity).viewModel
        pdfView=view.findViewById(R.id.pdfview)
        view.findViewById<View>(R.id.save_certificate).setOnClickListener {
            chooseFile()
        }

        return view
    }

    private fun chooseFile(){
        Intent(Intent.ACTION_GET_CONTENT).also {
            it.type="application/pdf"
            it.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(
                Intent.createChooser(it, "Select PDF"), PDF_SELECTION_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedPdfFromStorage = data.data
            showPdfFromUri(selectedPdfFromStorage)
            saveImageToStorage(selectedPdfFromStorage)
        }
    }

    private fun showPdfFromUri(uri: Uri?) {
        Log.d(TAG, uri.toString())
        pdfView!!.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()

        val file:File=FileUtil.from(requireActivity(),uri)
        Log.d(TAG,uri.toString())
        Log.d(TAG,file.path)
        Log.d(TAG,file.absolutePath)
        Log.d(TAG,file.toString())

    }

    private fun saveImageToStorage(uri: Uri?){
        Log.d(TAG,"saveImageToStorage")
        val filePath: File = Environment.getExternalStorageDirectory()
        val dir= File(filePath.absolutePath+"/Porfolia/")
        dir.mkdirs()
        val filerr:File=FileUtil.from(requireActivity(),uri)
        val file= File(dir,System.currentTimeMillis().toString()+".pdf")
        try {
            outputStream= FileOutputStream(file)
        }catch (e: FileNotFoundException){
            e.printStackTrace()
            Log.e(TAG,e.localizedMessage)
        }

        Log.e(TAG,Uri.fromFile(file).toString())
        Log.e(TAG,file.path.toString())
     //   bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
        outputStream?.write(filerr.readBytes())
        outputStream?.flush()
        outputStream?.close()
    }

}