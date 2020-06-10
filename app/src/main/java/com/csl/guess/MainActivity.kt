package com.csl.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName
    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Secret number: $secretNumber")

    }

    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG, "number: $n")

        val diff = secretNumber.validate(n)
        var msg = getString(R.string.got_it)
        if (diff < 0) {
           msg = getString(R.string.bigger)
        } else if (diff > 0){
            msg = getString(R.string.smaller)
        }

       // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(msg)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }


}