package com.example.portfolia.util

import android.content.Context
import android.widget.Toast

class Function {
      companion object{
          fun showToast(context: Context, message: String){
              Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
          }

      }
}