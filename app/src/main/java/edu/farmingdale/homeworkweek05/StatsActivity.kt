package edu.farmingdale.homeworkweek05
// Author:      Jayson Gonzalez
// Date:        10/09/2023
// Purpose:     Shows statistics for the previous QuestionsActivity intent
//              such as correctly answered questions w/ percentage and total questions asked
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        // Get correctAnswers from QuestionsActivity and sets a static totalQuestion value of 10
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val totalQuestions = 10

        // Calculate the user's percentage of correct answers
        val percentage = (correctAnswers.toFloat() / totalQuestions.toFloat()) * 100

        // Find TextViews in the layout
        val correctAnswersTextView = findViewById<TextView>(R.id.correctAnswersTextView)
        val totalQuestionsTextView = findViewById<TextView>(R.id.totalQuestionsTextView)
        val percentageTextView = findViewById<TextView>(R.id.percentageTextView)

        // Set the text of the TextViews with the statistics
        correctAnswersTextView.text = "Correct Answers: $correctAnswers"
        totalQuestionsTextView.text = "Total Questions: $totalQuestions"
        percentageTextView.text = "Percentage: ${String.format("%.2f", percentage)}%"
    }
}