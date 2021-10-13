package com.example.geoquiz01
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel: ViewModel() {
    var currentIndex = 0
    var isCheater = false

    private val questionBank = listOf(
        Question(R.string.question_kotlinClass, true),
        Question(R.string.question_australia, true),
        Question(R.string.question_america, false),
        Question(R.string.question_asia, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_oceans, false)
    )

    val currentQuestionText: Int
    get() = questionBank[currentIndex].textResId


    val currentQuestionAnswer: Boolean
    get() = questionBank[currentIndex].answer

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size

    }


}