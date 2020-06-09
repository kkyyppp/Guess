package com.csl.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ed_number
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    val TAG = MaterialActivity::class.java.simpleName
    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        Log.d(TAG, "Secret number: $secretNumber")

        val fab = findViewById<FloatingActionButton>(R.id.fab)
            fab.setOnClickListener { view ->
                AlertDialog.Builder(this)
                    .setTitle("Replay game")
                    .setMessage("Are you sure")
                    .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                        secretNumber.reset()
                        counter.text = ""
                    }
                    .setNeutralButton("Cancel", null)
                    .show()

            }

        counter.text = secretNumber.count.toString()

    }


    fun checck(view: View) {
        val n = ed_number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG, "number: $n")

        val diff = secretNumber.validate(n)
        var msg = getString(R.string.got_it)
        if (diff < 0) {
            msg = getString(R.string.bigger)
        } else if (diff > 0){
            msg = getString(R.string.smaller)
        } else {
            if (secretNumber.count < 3)
                msg = "Excellent! The number is $n"
        }

        counter.text = secretNumber.count.toString()

        // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(msg)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}