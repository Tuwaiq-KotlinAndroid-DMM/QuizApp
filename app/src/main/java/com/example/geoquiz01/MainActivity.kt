package com.example.geoquiz01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CHEAT_CODE = 0

class MainActivity: AppCompatActivity() {

     private lateinit var trueButton: Button
     private lateinit var falseButton: Button
     private lateinit var nextButton: Button
     private lateinit var questionTextView: TextView
     private lateinit var cheatButton: Button

     private  val quizViewModel: QuizViewModel by lazy {
          ViewModelProvider(this).get(QuizViewModel::class.java)
     }


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
          cheatButton = findViewById(R.id.cheat_button)

          trueButton.setOnClickListener(){
               checkAnswer(true)
          }

          falseButton.setOnClickListener(){
               checkAnswer(false)
          }

          nextButton.setOnClickListener{
               quizViewModel.moveToNext()
               updateQuestion()
          }

          cheatButton.setOnClickListener(){
               val answerIsTrue = quizViewModel.currentQuestionAnswer
               val intent = CheatActivity.newIntent(this, answerIsTrue)
           startActivityForResult(intent, REQUEST_CHEAT_CODE)

          }

          updateQuestion()
     }

     override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?){
          super.onActivityResult(requestCode, resultCode, data)
          if(resultCode != Activity.RESULT_OK){
               return
          }
          if(requestCode == REQUEST_CHEAT_CODE){
               quizViewModel.isCheater =
                    data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
          }
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
          val questionTextResId =  quizViewModel.currentQuestionText
          questionTextView.setText(questionTextResId)
     }

     private fun checkAnswer(userAnswer: Boolean){1
          val correctAnswer = quizViewModel.currentQuestionAnswer

//          val messageResId = if(userAnswer == correctAnswer){
//               R.string.toast_correct
//          } else {
//               R.string.toast_incorrect
//          }

          val messageResId = when {
               quizViewModel.isCheater -> R.string.judgement_toast
               userAnswer == correctAnswer -> R.string.toast_correct
               else -> R.string.toast_incorrect
          }

               Toast.makeText(this , messageResId, Toast.LENGTH_SHORT).show()

     }

     override fun onSaveInstanceState(savedInstanceState: Bundle) {
          super.onSaveInstanceState(savedInstanceState)
          Log.i(TAG, "onSaveInstanceState")
          savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
     }

}







