package com.csl.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Secret number: $secretNumber")

    }

    fun checck(view: View) {
        val n = ed_number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG, "number: $n")

        val diff = secretNumber.validate(n)
        var msg = "Yes, you got it"
        if (diff < 0) {
           msg = "Bigger"
        } else if (diff > 0){
            msg = "smaller"
        }

       // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(msg)
            .setPositiveButton("ok", null)
            .show()
    }
}