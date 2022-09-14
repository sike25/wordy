package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var wordToGuess = FourLetterWordList.getRandomFourLetterWord()

    /** wordToGuess : String - the target word the user is trying to guess
     *  guess : String - what the user entered as their guess
     *
     *  Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            result += when {
                guess[i] == wordToGuess[i] -> {
                    "O"
                }
                guess[i] in wordToGuess -> {
                    "+"
                }
                else -> {
                    "X"
                }
            }
        }
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0;

        val guess = findViewById<EditText>(R.id.input).text
        val button = findViewById<Button>(R.id.button)
        val reload = findViewById<Button>(R.id.reload)

        val tv1 = findViewById<TextView>(R.id.textView1)
        val tv2 = findViewById<TextView>(R.id.textView2)
        val tv3 = findViewById<TextView>(R.id.textView3)
        val tv4 = findViewById<TextView>(R.id.textView4)

        button.setOnClickListener {
            //Guss is the string form of the Editable! guess
            val guss = guess.toString()
            count++

            if (guss.length != 4) {
                Toast.makeText(it.context, "Word should have four letters!", Toast.LENGTH_SHORT).show()
            }

            else {
                val report = checkGuess(guess.toString())
                //Toast.makeText(it.context, report, Toast.LENGTH_SHORT).show()

                if (report.equals("OOOO")) {
                    Toast.makeText(it.context, "You little Einstein, you got it right!", Toast.LENGTH_LONG).show()
                }

                if ((count >= 3) && (!report.equals("0000"))) {
                    Toast.makeText(it.context, "Shucks! All out of your three guesses! The word was " + wordToGuess, Toast.LENGTH_LONG).show()
                    button.isEnabled = false
                }

                tv1.text = guss[0] + ""
                if (report[0].toString() == "O") tv1.setBackgroundResource(R.color.green)
                if (report[0].toString() == "+") tv1.setBackgroundResource(R.color.orange)
                if (report[0].toString() == "X") tv1.setBackgroundResource(R.color.red)


                tv2.text = guss[1] + ""
                if (report[1].toString() == "O") tv2.setBackgroundResource(R.color.green)
                if (report[1].toString() == "+") tv2.setBackgroundResource(R.color.orange)
                if (report[1].toString() == "X") tv2.setBackgroundResource(R.color.red)


                tv3.text = guss[2] + ""
                if (report[2].toString() == "O") tv3.setBackgroundResource(R.color.green)
                if (report[2].toString() == "+") tv3.setBackgroundResource(R.color.orange)
                if (report[2].toString() == "X") tv3.setBackgroundResource(R.color.red)

                tv4.text = guss[3] + ""
                if (report[3].toString() == "O") tv4.setBackgroundResource(R.color.green)
                if (report[3].toString() == "+") tv4.setBackgroundResource(R.color.orange)
                if (report[3].toString() == "X") tv4.setBackgroundResource(R.color.red)

            }
        }

        reload.setOnClickListener {
            count = 0;
            wordToGuess = FourLetterWordList.getRandomFourLetterWord()
            button.isEnabled = true

            tv1.text = ""
            tv2.text = ""
            tv3.text = ""
            tv4.text = ""

            tv1.setBackgroundResource(R.drawable.textviewbackground)
            tv2.setBackgroundResource(R.drawable.textviewbackground)
            tv3.setBackgroundResource(R.drawable.textviewbackground)
            tv4.setBackgroundResource(R.drawable.textviewbackground)
        }


    }
}
