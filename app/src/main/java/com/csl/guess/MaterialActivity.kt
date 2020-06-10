package com.csl.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ed_number
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.android.synthetic.main.content_material.*
import kotlinx.android.synthetic.main.content_material.counter

class MaterialActivity : AppCompatActivity() {

    val REQUEST_RECORD: Int = 100
    val TAG = MaterialActivity::class.java.simpleName
    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")

        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        Log.d(TAG, "Secret number: $secretNumber")

        val fab = findViewById<FloatingActionButton>(R.id.fab)
            fab.setOnClickListener { view ->
                replay()
            }

        counter.text = secretNumber.count.toString()
        Log.d(TAG, "secretNumber: ${secretNumber.secret} ")

        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", 0)
        val nickname = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", "")
        Log.d(TAG, "data: $count / $nickname")
    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle("Replay game")
            .setMessage("Are you sure")
            .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                secretNumber.reset()
                counter.text = "0"
            }
            .setNeutralButton("Cancel", null)
            .show()
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
        } else {
            if (secretNumber.count < 3)
                msg = "Excellent! The number is $n"
        }

        counter.text = secretNumber.count.toString()

        // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(msg)
            .setPositiveButton(getString(R.string.ok)) {dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            }
            .show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_RECORD) {
            if (resultCode == Activity.RESULT_OK) {
                val nickName = data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult: $nickName")
                replay()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}