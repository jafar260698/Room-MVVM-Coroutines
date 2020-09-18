package com.example.portfolia.ui

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.portfolia.R
import com.example.portfolia.database.Entity.RegistrationEntity
import com.example.portfolia.database.MainDatabase
import com.example.portfolia.repository.RegisterRepository
import com.example.portfolia.util.Constants
import com.example.portfolia.util.Constants.Companion.CAMERA_PERM_CODE
import com.example.portfolia.util.Constants.Companion.CAMERA_REQUEST_CODE
import com.example.portfolia.util.Constants.Companion.REQUEST_CODE_IMAGE
import com.example.portfolia.util.FileUtil
import com.example.portfolia.util.Function
import com.example.restaurants.ui.preference.RegisterPreference
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import com.example.restaurants.ui.viewmodel.RegisterViewModelFactory
import kotlinx.coroutines.launch
import java.io.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow

class RegisterFragment : Fragment() {
    lateinit var viewModel: RegisterViewModel
    private val calendar = Calendar.getInstance()
    var registrationEntity:RegistrationEntity?=null
    var preference:RegisterPreference?=null
    // image
    var profile_image:ImageView?=null
    private var actualImage1: File? = null
    var selectedImage: Uri?=null
    var currentPhotoPath: String? = null
    var externalstorage:String?=null
    // edittext android
    var name:EditText?=null
    var date_of_birth:EditText?=null
    var place_of_birth:EditText?=null
    var nationality:EditText?=null
    var mother_tongue:EditText?=null
    var field_of_study:EditText?=null
    var date_started:EditText?=null
    var parol:EditText?=null
    val TAG="RegisterFragment"

    var outputStream:OutputStream?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        viewModel= (activity as MainActivity).viewModel
        profile_image=view.findViewById(R.id.picture)
        name=view.findViewById(R.id.name)
        date_of_birth=view.findViewById(R.id.date_of_birth)
        place_of_birth=view.findViewById(R.id.place_of_birth)
        nationality=view.findViewById(R.id.nationality)
        mother_tongue=view.findViewById(R.id.mother_tongue)
        field_of_study=view.findViewById(R.id.field_of_study)
        date_started=view.findViewById(R.id.date_started)
        parol=view.findViewById(R.id.parol)
        preference= RegisterPreference(requireActivity())

        date_of_birth!!.setOnClickListener {
            DatePickerDialog(
                requireActivity(), date, calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
        date_started!!.setOnClickListener {
            DatePickerDialog(
                requireActivity(), date2, calendar[Calendar.YEAR], calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
        view.findViewById<View>(R.id.save_reg).setOnClickListener {
           if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
              if (ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)
              !=PackageManager.PERMISSION_GRANTED){
                  ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                  1001)
              }else{
                  Log.d(TAG,"saveImageToStorage()")
                  saveImageToStorage()
              }
           }else{
               Log.d(TAG,"Version Control under 23 version")
           }
            if (externalstorage!=null){
                registrationEntity= RegistrationEntity(
                    name!!.text.toString(),
                    date_of_birth!!.text.toString(),
                    place_of_birth!!.text.toString(),
                    nationality!!.text.toString(),
                    mother_tongue!!.text.toString(),
                    field_of_study!!.text.toString(),
                    date_started!!.text.toString(),
                    parol!!.text.toString(),
                    externalstorage!!
                )
                viewModel.saveRegistration(registrationEntity!!)
                Function.showToast(requireActivity(),"Bazaga saqlandi")
                preference!!.setRegistration(Constants.IS_REGISTERED,true)
                this.findNavController().navigate(R.id.action_registerFragment_to_homeFragment,null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashFragment,
                            true).build())
            }else{
                Function.showToast(requireActivity(),"External storage empty")
            }

        }

        view.findViewById<View>(R.id.fabChoosePic).setOnClickListener {
            val popupmenu = PopupMenu(requireActivity(), it)
            popupmenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.photo_gallery -> {
                        openImageChooser()
                        true
                    }
                    R.id.photo_take -> {
                        askCameraPermissions();
                        true
                    }

                    else -> false
                }
            }
            popupmenu.inflate(R.menu.popup_menu_photo)
            try {
                val fieldsPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldsPopup.isAccessible = true
                val mPopup = fieldsPopup.get(popupmenu)
                mPopup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.d("TAG", "$e")
            } finally {
                popupmenu.show()
            }
        }
        return view
    }

    var date =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = monthOfYear
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            setDateOfBirth()
        }
    var date2 =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = monthOfYear
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            setDateStartUsing()
        }

    private fun setDateOfBirth() {
        val myFormat = "dd MMMM yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date_of_birth?.setText(sdf.format(calendar.getTime()))
    }

    private fun setDateStartUsing() {
        val myFormat = "dd MMMM yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date_started?.setText(sdf.format(calendar.getTime()))
    }

    private fun askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERM_CODE
            )
        } else {
            Log.d(TAG, "dispatchTake")
            dispatchTakePictureIntent()
        }
    }

    private fun getFileExt(contentUri: Uri): String? {
        val c: ContentResolver = requireActivity().contentResolver
        val mime: MimeTypeMap = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(c.getType(contentUri))
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                Log.d(TAG, ex.toString())
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(
                    requireActivity(),
                    "com.example.portfolia.fileprovider",
                    photoFile
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun openImageChooser(){
        Intent(Intent.ACTION_PICK).also {
            it.type="image/*"
            val mimeTypes= arrayOf("image/jpeg", "image/png", "image/jpg")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK ){
            when(requestCode){
                REQUEST_CODE_IMAGE -> {
                    try {
                        lifecycleScope.launch {
                            selectedImage = data?.data
                            //restoran_logo.setImageURI(selectedImage)
                            actualImage1 = FileUtil.from(requireActivity(), selectedImage)?.also {
                                //profile_image?.setImageURI(selectedImage)
                                Log.d(
                                    TAG, "Before compressing" + String.format(
                                        "Size : %s", getReadableFileSize(
                                            it.length()
                                        )
                                    )
                                )
                                val requestOptions = RequestOptions()
                                    .placeholder(R.color.card1)
                                    .error(R.color.card3)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .centerCrop()

                                Glide.with(requireActivity())
                                    .applyDefaultRequestOptions(requestOptions)
                                    .load(selectedImage)
                                    .into(profile_image!!)

                            }
                        }
                    } catch (e: IOException) {
                        Function.showToast(requireActivity(), "Failed to read picture data! $e")
                        e.printStackTrace()
                    }
                }

                CAMERA_REQUEST_CODE -> {
                    if (resultCode == Activity.RESULT_OK) {
                        val f = File(currentPhotoPath)
                        // profile_image?.setImageURI(Uri.fromFile(f))

                        Log.d("tag", "ABsolute Url of Image is " + Uri.fromFile(f))
                        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                        val contentUri = Uri.fromFile(f)
                        mediaScanIntent.data = contentUri
                        requireActivity().sendBroadcast(mediaScanIntent)

                        val requestOptions = RequestOptions()
                            .placeholder(R.color.card1)
                            .error(R.color.card3)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                        //.circleCrop()
                        Glide.with(requireActivity())
                            .applyDefaultRequestOptions(requestOptions)
                            .load(contentUri)
                            .into(profile_image!!)

                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }else {
                Toast.makeText(requireActivity(),"Camera Permission is Required to Use camera.",Toast.LENGTH_SHORT).show()
            }
        }
        if (requestCode==1001){
            if (grantResults.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
              saveImageToStorage()
            }else{
                Function.showToast(requireActivity(),"Permission not granted")
            }
        }
    }

    private fun saveImageToStorage(){
        Log.d(TAG,"saveImageToStorage")
        val drawable:BitmapDrawable=profile_image?.drawable as BitmapDrawable
        val bitmap: Bitmap =drawable.bitmap
        val filePath:File=Environment.getExternalStorageDirectory()
        val dir= File(filePath.absolutePath+"/Porfolia/")
        dir.mkdirs()
        val file=File(dir,System.currentTimeMillis().toString()+".jpg")
        try {
            outputStream=FileOutputStream(file)
        }catch (e:FileNotFoundException){
            e.printStackTrace()
            Log.e(TAG,e.localizedMessage)
        }
        // externalstorage=file.absolutePath
        externalstorage=Uri.fromFile(file).toString()
        Log.e(TAG,externalstorage.toString())
        Log.e(TAG,Uri.fromFile(file).toString())
        Log.e(TAG,file.path.toString())

        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
        Function.showToast(requireActivity(),"Inserted image")
        outputStream?.flush()
        outputStream?.close()
    }

    private fun getReadableFileSize(size: Long): String {
        if (size <= 0) {
            return "0"
        }
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }

}