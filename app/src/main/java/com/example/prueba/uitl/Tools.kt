package com.example.prueba.uitl

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast

object Tools {

    fun shareMessageViaWhatsApp(context: Context, message: String) {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.setType("text/plain")
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, message)
        try {
            context.startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show()
        }
    }
}