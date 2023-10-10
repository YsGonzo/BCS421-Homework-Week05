package edu.farmingdale.homeworkweek05
// Author:      Jayson Gonzalez
// Date:        10/09/2023
// Purpose:     Shows a quick splash screen and redirects user to QuestionsActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {

    // Displays a splash screen for the user and moves them to the QuestionsActivity.kt intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, QuestionsActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // Adds a 3 second delay
    }
}