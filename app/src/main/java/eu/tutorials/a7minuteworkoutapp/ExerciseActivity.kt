package eu.tutorials.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import eu.tutorials.a7minuteworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    //Todo 1 creat a binding variable
    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer?= null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer?= null
    private var exerciseProgress = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//Todo 2 inflate the layout
        binding = ActivityExerciseBinding.inflate(layoutInflater)
//Todo 3 pass in binding?.root in the content view
        setContentView(binding?.root)
//Todo 5: then set support action bar and get toolBarExcerciser using the binding
//variable
        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.flProgressBar?.visibility = View.VISIBLE
        setRestView()
    }
private fun setRestView(){
    if(restTimer != null){
        restTimer?.cancel()
            restProgress = 0
    }

    setRestProgressBar()
}

    private fun setupExerciseView(){
        binding?.flProgressBar?. visibility = View.INVISIBLE
        binding?.tvTitle?.text ="Exercise Name"
        binding?.flExerciseView?.visibility = View.VISIBLE
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
    }
   private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress

       restTimer = object: CountDownTimer(10000, 1000){
           override fun onTick(millisUntilFinished: Long) {
restProgress++
               binding?.progressBar?.progress = 10 - restProgress
               binding?.tvTimer?.text=(10 - restProgress).toString()
           }

           override fun onFinish() {
               setupExerciseView()
           }
       }.start()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object: CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
             exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text=(30 - exerciseProgress).toString()
            }

            override fun onFinish() {

                Toast.makeText(
                    this@ExerciseActivity,
                    "Here now we will start the exercise.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }

    override fun onDestroy(){
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
binding = null
    }
}