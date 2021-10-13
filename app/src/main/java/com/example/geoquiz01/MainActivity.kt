package com.example.geoquiz01

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity: AppCompatActivity() {

     private lateinit var trueButton: Button
     private lateinit var falseButton: Button
     private lateinit var nextButton: Button
     private lateinit var questionTextView: TextView

     private val questionBank = listOf(
          Question(R.string.question_kotlinClass, true),
          Question(R.string.question_australia, true),
          Question(R.string.question_america, false),
          Question(R.string.question_asia, true),
          Question(R.string.question_mideast, false),
          Question(R.string.question_oceans, false)
     )
     private var currentIndex = 0

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          Log.d(TAG, "onCreate Bundle? called")
          // set a view
          setContentView(R.layout.activity_main)
          // get buttons by ID
          trueButton = findViewById(R.id.true_button)
          falseButton = findViewById(R.id.false_button)
          nextButton = findViewById(R.id.next_button)
          questionTextView = findViewById(R.id.question_text_view)

          trueButton.setOnClickListener(){
               checkAnswer(true)
          }

          falseButton.setOnClickListener(){
               checkAnswer(false)
          }

          nextButton.setOnClickListener{
               currentIndex = (currentIndex + 1) % questionBank.size
               updateQuestion()
          }
          updateQuestion()
     }

     override fun onStart(){
          super.onStart()
          Log.d(TAG, "onStart() called.")
     }

     override fun onResume(){
          super.onResume()
          Log.d(TAG, "OnResume called")
     }

     override fun onPause() {
          super.onPause()
          Log.d(TAG, "OnPause called.")
     }

     override fun onStop() {
          super.onStop()
          Log.d(TAG, "OnStop called.")
     }

     override fun onDestroy() {
          super.onDestroy()
          Log.d(TAG, "onDestroy called.")
     }

     private fun updateQuestion(){
          val questionTextResId =  questionBank[currentIndex].textResId

          questionTextView.setText(questionTextResId)
     }

     private fun checkAnswer(userAnswer: Boolean){
          val correctAnswer = questionBank[currentIndex].answer

          val messageResId = if(userAnswer == correctAnswer){
               R.string.toast_correct
          } else {
               R.string.toast_incorrect
          }
          Toast.makeText(this , messageResId, Toast.LENGTH_SHORT).show()

     }


}