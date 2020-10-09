package com.example.portfolia.util

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.show(){
    this.visibility=View.VISIBLE
}

fun View.hide(){
    this.visibility=View.GONE
}

fun View.manageVisibility(condition:Boolean) {
    if (condition) this.visibility = View.VISIBLE
    else visibility = View.GONE
}

fun View.isVisible():Boolean{
    return this.visibility!=(View.VISIBLE)
}

fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener { listener.onClick(this) }
}

fun Fragment.toast(message: String) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .apply {
            setAction("Ok") { this.dismiss() }.show()
        }
}

fun View?.hideSoftKeyboard() {
    val imm = this?.context?.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(this?.windowToken, 0)
}

fun View.onClick(listener: (View) -> Unit) {
    this.setOnClickListener { listener.invoke(it) }
}



