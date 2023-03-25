package com.example.jkshop.base

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.jkshop.R

fun Context.showToastMsg(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showAlertDialog(msg: String, action: () -> Unit) {
    AlertDialog.Builder(this).apply {
        setTitle(getString(R.string.alert_title))
        setMessage(msg)
        setPositiveButton(getString(R.string.btn_ok)) { _, _ ->
            action.invoke()
        }
        setNegativeButton(getString(R.string.btn_cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        show()
    }
}