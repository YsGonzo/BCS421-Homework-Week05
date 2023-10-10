package edu.farmingdale.homeworkweek05
// Data class that will hold information for each question and display it dynamically in the activity_questions.xml layout file
data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    var selectedAnswer: Int? = null
)