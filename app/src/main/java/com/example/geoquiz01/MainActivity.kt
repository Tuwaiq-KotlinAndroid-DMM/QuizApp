package com.example.geoquiz01

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

     private lateinit var trueButton: Button
     private lateinit var falseButton: Button

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          // set a view
          setContentView(R.layout.activity_main)
          // get buttons by ID
          trueButton = findViewById(R.id.true_button)
          falseButton = findViewById(R.id.false_button)

          trueButton.setOnClickListener(){
               Toast.makeText(this, R.string.toast_correct, Toast.LENGTH_SHORT).show()
          }

          falseButton.setOnClickListener(){
               Toast.makeText(this, R.string.toast_incorrect, Toast.LENGTH_SHORT).show()
          }
     }

}