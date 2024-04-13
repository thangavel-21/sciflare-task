package com.example.sciflaretask.utills

import android.content.Context
import android.widget.Toast

class HelperFunction {

    companion object {
        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}