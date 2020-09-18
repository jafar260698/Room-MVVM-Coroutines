package com.example.portfolia.ui.dialogfragment

import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.portfolia.R
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception


class PdfPortfoliaFragment : DialogFragment() {
    val informationarray= arrayOf("Name","Company Name","Address","Phone","Email")
    var bmp:Bitmap?=null
    var scaled:Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogtheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.fragment_pdf_portfolia, container, false)
        bmp=BitmapFactory.decodeResource(resources,R.drawable.face)
        scaled=Bitmap.createScaledBitmap(bmp!!,50,50,false)

        view.findViewById<View>(R.id.btn_pdf_save).setOnClickListener {
            createPdf()
        }

        return view
    }
    private fun createPdf(){
        val pdfDocument= PdfDocument()
        val paint: Paint = Paint()
        val pageInfo: PdfDocument.PageInfo= PdfDocument.PageInfo.Builder(250, 400, 1).create()
        val mypage: PdfDocument.Page=pdfDocument.startPage(pageInfo)

        val canvas : Canvas =mypage.canvas
        paint.textAlign= Paint.Align.CENTER
        paint.textSize=12f
        canvas.drawText("HR Enterprise",(pageInfo.pageWidth/2).toFloat(), 30F,paint)

        paint.textSize=7f
        paint.textScaleX=1.5f
        paint.color= Color.rgb(122,119,119)
        canvas.drawText("Jafar Temirov Soxib o'g'li",(pageInfo.pageWidth/2).toFloat(), 40F,paint)
        paint.textScaleX=1f

        paint.textAlign= Paint.Align.RIGHT
        paint.textSize=9.0f
        paint.color= Color.rgb(122,119,119)
        canvas.drawText("My personal identification",10f,70f,paint)

        paint.textAlign= Paint.Align.LEFT
        paint.textSize=8.0f
        paint.color= Color.BLACK
/*
        val startXPosition=10f
        val endXPosition=pageInfo.pageWidth-10f
        var startYPosition=10f
        for (i in 0..4){
            canvas.drawText(informationarray[i],startXPosition,startYPosition,paint)
            canvas.drawLine(startXPosition,startYPosition+3,endXPosition,startYPosition+3,paint)
            startYPosition+=20f
        }
        canvas.drawLine(80f,92f,80f,190f,paint)
        paint.style= Paint.Style.STROKE
        paint.strokeWidth=2f
        canvas.drawRect(10F, 200F, (pageInfo.pageWidth-10).toFloat(), 300F,paint)
        canvas.drawLine(85F, 200F, 85F, 300F,paint)
        canvas.drawLine(85F, 200F, 85F, 300F,paint)
        paint.strokeWidth=0F
        paint.style= Paint.Style.FILL

        canvas.drawText("Photo",35F,250F,paint)
        canvas.drawText("Photo",110F,250F,paint)
        canvas.drawText("Photo",190F,250F,paint)
        canvas.drawText("Note",10F,320F,paint)
        canvas.drawLine(35F,325F, (pageInfo.pageWidth-10).toFloat(),325F,paint)
        canvas.drawLine(10F,345F, (pageInfo.pageWidth-10).toFloat(),345F,paint)
        canvas.drawLine(10F,365F, (pageInfo.pageWidth-10).toFloat(),365F,paint)
  */
        canvas.drawBitmap(bmp!!,40F,40F,paint)

        pdfDocument.finishPage(mypage)

        //   val random = java.util.Random()
        //   val randomNumber=random.nextInt(1000-1)+1;
        val file = File(Environment.getExternalStorageDirectory(), "/Resume2.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
        }catch (e: Exception){
            e.printStackTrace()
        }
        pdfDocument.close()
    }

    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
    }

}