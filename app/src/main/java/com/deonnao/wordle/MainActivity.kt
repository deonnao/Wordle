package com.deonnao.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etWord = findViewById<EditText>(R.id.etGuessWord)
        val tvGuess1 = findViewById<TextView>(R.id.textView8)
        val tvGuessCheck1 = findViewById<TextView>(R.id.textView9)
        val tvGuess2 = findViewById<TextView>(R.id.textView10)
        val tvGuessCheck2 = findViewById<TextView>(R.id.textView11)
        val tvGuess3 = findViewById<TextView>(R.id.textView12)
        val tvGuessCheck3 = findViewById<TextView>(R.id.textView13)
        val tvWordle = findViewById<TextView>(R.id.tvWordle)
        var i = 0

        //TODO keep the same generated word

        findViewById<Button>(R.id.guessBtn).setOnClickListener {
            i++
            if(i > 3) {
                Toast.makeText(this, "You have exceeded your guesses",Toast.LENGTH_SHORT).show()
            }

            var word = etWord.text.toString()
            var result = checkGuess(word.uppercase())
            //Log.i(this.toString(), wordToGuess)

            when (i) {
                1 -> {
                    //display guess in textView
                    tvGuess1.text = word
                    tvGuessCheck1.text = result
                }
                2 -> {
                    //display guess in textView
                    tvGuess2.text = word
                    tvGuessCheck2.text = result
                }
                3 -> {
                    //display guess in textView
                    tvGuess3.text = word
                    tvGuessCheck3.text = result
                }
            }
            if(word.uppercase() == wordToGuess || i == 3) {
                tvWordle.text = wordToGuess
            }
            //empty editText field and hide keyboard after each guess
            etWord.isEnabled = false
            etWord.setText("")
            etWord.isEnabled = true
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */

    val wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()

    private fun checkGuess(guess: String): String {

        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}