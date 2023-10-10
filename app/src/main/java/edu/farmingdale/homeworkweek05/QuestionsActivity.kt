package edu.farmingdale.homeworkweek05
// Author:      Jayson Gonzalez
// Date:        10/09/2023
// Purpose:     Shows questions list and tappable RadioButtons for the users to answer the questions
//              background color for radioButtons is included in the code & confirm answer button
//              Also redirects user to stats page once all questions are answered
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.graphics.Color
import android.content.Intent

class QuestionsActivity : AppCompatActivity() {
    private var currentIndex = 0
    private var earnings = 0
    private var correctAnswers = 0
    private lateinit var questions: List<Question>
    private lateinit var questionText: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var confirmButton: Button

    // Creates the object list of questions that will be dynaically displayed in the activity_questions.xml layout file
    private fun createQuestions(): List<Question> {
        val questionList = mutableListOf<Question>()
        // Question #1
        val questionText1 = "Who wrote the play 'Romeo and Juliet'?"
        val answerOptions1 = listOf("William Shakespeare", "Jane Austen", "Charles Dickens", "Leo Tolstoy")
        val correctAnswer1 = 0
        val question1 = Question(questionText1, answerOptions1, correctAnswer1)
        // Question #2
        val questionText2 = "Which planet is known as the Red Planet?"
        val answerOptions2 = listOf("Mars", "Venus", "Jupiter", "Earth")
        val correctAnswer2 = 0
        val question2 = Question(questionText2, answerOptions2, correctAnswer2)
        // Question #3
        val questionText3 = "What is the capital of France?"
        val answerOptions3 = listOf("London", "Berlin", "Paris", "Madrid")
        val correctAnswer3 = 2
        val question3 = Question(questionText3, answerOptions3, correctAnswer3)
        // Question #4
        val questionText4 = "Which of the following is NOT a fruit?"
        val answerOptions4 = listOf("Rhubarb", "Tomatoes", "Avocados", "Bananas")
        val correctAnswer4 = 0
        val question4 = Question(questionText4, answerOptions4, correctAnswer4)
        // Question #5
        val questionText5 = "Who is generally considered the inventor of the motor car?"
        val answerOptions5 = listOf("Henry Ford", "Karl Benz", "Henry M. Leland", "Ferdinand Porsche")
        val correctAnswer5 = 1
        val question5 = Question(questionText5, answerOptions5, correctAnswer5)
        // Question #6
        val questionText6 = "Which of the following languages has the longest alphabet?"
        val answerOptions6 = listOf("Greek", "Russian", "Arabic", "Japanese")
        val correctAnswer6 = 1
        val question6 = Question(questionText6, answerOptions6, correctAnswer6)
        // Question #7
        val questionText7 = "Which flies a green, white, and orange (in that order) tricolor flag?"
        val answerOptions7 = listOf("Ireland", "Ivory Coast", "Italy", "France")
        val correctAnswer7 = 0
        val question7 = Question(questionText7, answerOptions7, correctAnswer7)
        // Question #8
        val questionText8 = "Who sang the title song for the latest Bond film, No Time to Die?"
        val answerOptions8 = listOf("Adele", "Sam Smith", "Billie Eilish", "Pop Smoke")
        val correctAnswer8 = 2
        val question8 = Question(questionText8, answerOptions8, correctAnswer8)
        // Question #9
        val questionText9 = "Who was the lead singer of the band The Who?"
        val answerOptions9 = listOf("Roger Daltrey", "Don Henley", "Robert Plant", "John Lennon")
        val correctAnswer9 = 0
        val question9 = Question(questionText9, answerOptions9, correctAnswer9)
        // Question #10
        val questionText10 = "What spirit is used in making a Tom Collins?"
        val answerOptions10 = listOf("Vodka", "Rum", "Whiskey", "Gin")
        val correctAnswer10 = 3
        val question10 = Question(questionText10, answerOptions10, correctAnswer10)

        questionList.addAll(listOf(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10))
        return questionList
    }

    // Displays each question in the questions list and saves the user's chosen answer for the Stats Activity
    // Also gives the selected RadioButton a green background color
    private fun loadQuestion(index: Int) {
        val question = questions[index]
        val questionTextView = findViewById<TextView>(R.id.questionText)
        questionTextView.text = question.questionText

        // Clear the previous options
        optionsRadioGroup.removeAllViews()
        // Dislays the radio button options dependent on the question list index
        for (i in question.options.indices) {
            val radioButton = RadioButton(this)
            radioButton.text = question.options[i]
            // Sets an OnClickListener for each RadioButton
            radioButton.setOnClickListener {
                // Clear background color for all RadioButtons
                for (j in 0 until optionsRadioGroup.childCount) {
                    val optionRadioButton = optionsRadioGroup.getChildAt(j) as RadioButton
                    optionRadioButton.setBackgroundColor(Color.TRANSPARENT)
                }

                // Sets a green background color for the user's chosen RadioButton
                radioButton.setBackgroundColor(0xFF00FF00.toInt()) // Green color
            }

            optionsRadioGroup.addView(radioButton)
        }

        // Highlight the selected answer if any
        val selectedAnswer = questions[index].selectedAnswer
        if (selectedAnswer != null) {
            val selectedRadioButton = optionsRadioGroup.getChildAt(selectedAnswer) as RadioButton
            selectedRadioButton.setBackgroundColor(0xFF00FF00.toInt()) // Green color
            selectedRadioButton.setTextColor(Color.WHITE)
        }
    }

    // Checks if the chosen answer is correct/incorrect and displays a corresponding Toast message to the User
    // Redirects user to the stats intent if the last question is answered
    private fun checkAnswer() {
        val selectedRadioButtonId = optionsRadioGroup.checkedRadioButtonId

        if (selectedRadioButtonId != -1) {
            val selectedAnswerIndex = optionsRadioGroup.indexOfChild(findViewById(selectedRadioButtonId))
            val correctAnswerIndex = questions[currentIndex].correctAnswerIndex

            // Tells user whether or not the answer was correct or not
            if (selectedAnswerIndex == correctAnswerIndex) {
                val earningsTextView = findViewById<TextView>(R.id.earningsTV)
                earnings += 100
                correctAnswers += 1
                earningsTextView.text = "Earnings: $$earnings"
                Toast.makeText(this, "This is the CORRECT answer\nYou've earned $$earnings", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
            }

            // Store the selected answer
            questions[currentIndex].selectedAnswer = selectedAnswerIndex
        } else {
            Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show()
        }

        // Move to the next question or display stats layout
        if (currentIndex < questions.size - 1) {
            currentIndex++
            loadQuestion(currentIndex)
        } else {
            // navigate to StatsActivity if final question is answered
            val intent = Intent(this, StatsActivity::class.java)
            intent.putExtra("correctAnswers", correctAnswers)
            startActivity(intent)
        }
    }

    //Questions Application using the previously declared functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        // Initializing the questions array
        questions = createQuestions()

        questionText = findViewById(R.id.questionText)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        confirmButton = findViewById(R.id.confirmButton)

        loadQuestion(currentIndex)

        confirmButton.setOnClickListener {
            checkAnswer()
        }
    }
}