package uz.example.mathquiz.ui

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import uz.example.mathquiz.R
import uz.example.mathquiz.databinding.FragmentGameBinding


class GameFragment : Fragment() {

private val binding by lazy { FragmentGameBinding.inflate(layoutInflater) }

    private var A=0
    private var B=0


//    ,'*','/'
//    var raqam=(1 until 20).random()
    private var score=0
    private var timer=30000L

    private var questionNo=1


   var locationOfCorrectAnswer=0
    private val options:MutableList<Int> = ArrayList()
    private var incorrectAnswer=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         generateQuestion()
        startTimer()
        binding.restartGame.setOnClickListener {
            restart()
        }
    }

    private fun startTimer() {
        object :CountDownTimer(timer+100,1000){
            override fun onTick(millisUntilFinished: Long) {

                binding.textTime.setText((millisUntilFinished/1000).toString())

            }

            override fun onFinish() {
                setDialog()
                clearGame()

            }

        }.start()
    }

    fun generateQuestion(){
        options.clear()
         var ishora = listOf('+','-','*','/').random()

        A=(0 until 10).random()
        B=(0 until 10).random()

        if (A<B){
            A=(0 until 10).random()
            B=(0 until 10).random()
        }

            var hosila="$A$ishora$B"
            binding.textSavol.text="$hosila = ?"
            Log.e("hosila", "generateQuestion: $hosila", )





        incorrectAnswer=(1 until 10).random()

        locationOfCorrectAnswer=(0 until 4).random()

        when (ishora){
           '+' ->
        { for (i in 0 until 4){
               if (i==locationOfCorrectAnswer){
                   options.add(A+B)
               }else{
                   while (incorrectAnswer==A+B || incorrectAnswer in options){
                       incorrectAnswer=(1 until 10).random()
                   }
                   options.add(incorrectAnswer)
               }
           }


           }
            '-' -> {for (i in 0 until 4){
                if (i==locationOfCorrectAnswer){

                        options.add(Math.abs(A-B))
                }else{
                    while (incorrectAnswer==(Math.abs(A-B)) || incorrectAnswer in options){
                        incorrectAnswer=(1 until 10).random()
                    }
                    options.add(incorrectAnswer)
                }
            }
            }
            '*'->{
                for (i in 0 until 4){
                     if (i==locationOfCorrectAnswer){
                         options.add(A*B)
                     }else{
                         while (incorrectAnswer==A*B || incorrectAnswer in options){
                             incorrectAnswer=(1 until  10).random()
                         }
                         options.add(incorrectAnswer)
                     }
                }
            }

            '/'->if (B!=0){

                    for (i in 0 until 4){
                        if (i==locationOfCorrectAnswer){
                            options.add(A/B).toString()
                        }else{
                            while (incorrectAnswer==A/B || incorrectAnswer in options){
                                incorrectAnswer=(1 until  10).random()
                            }
                            options.add(incorrectAnswer)
                        }
                    }

            }

        }


        binding.btnAnswer1.text=options[0].toString()
        binding.btnAnswer2.text=options[1].toString()
        binding.btnAnswer3.text=options[2].toString()
        binding.btnAnswer4.text=options[3].toString()


        binding.btnAnswer1.setOnClickListener {
            checking(binding.btnAnswer1)
        }
        binding.btnAnswer2.setOnClickListener {
            checking(binding.btnAnswer2)
        }
        binding.btnAnswer3.setOnClickListener {
            checking(binding.btnAnswer3)
        }
        binding.btnAnswer4.setOnClickListener {
            checking(binding.btnAnswer4)
        }

    }

    fun checking(view: View){

        binding.natija.visibility=View.VISIBLE
        if (view.tag.toString()==locationOfCorrectAnswer.toString()){
            score++
            binding.natija.text="Correct"
            binding.natija.setTextColor(ContextCompat.getColor(view.context,R.color.white))
            binding.textScore.text="$score" + "/" + "${questionNo}"
            }
            else{
                binding.natija.text="Incorrect Answer"
                binding.natija.setTextColor(ContextCompat.getColor(view.context,R.color.color_incorrect))
            }

        object :CountDownTimer(1000,1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                binding.natija.visibility=View.INVISIBLE

            }

        }.start()

        questionNo++
        binding.textScore.text="$score" + "/" + "${questionNo}"
        generateQuestion()

    }
    fun restart(){

            generateQuestion()
            score=0
            questionNo=1
            binding.textScore.text="$score" + "/" + "${questionNo}"
            timer=30000L
            startTimer()

            binding.restartGame.visibility=View.INVISIBLE


            binding.btnAnswer1.isEnabled=true
            binding.btnAnswer2.isEnabled=true
            binding.btnAnswer3.isEnabled=true
            binding.btnAnswer4.isEnabled=true





    }

    fun setDialog(){
        val alertDialog=AlertDialog.Builder(requireContext())
            .setTitle("Game Over")
            .setCancelable(false)
            .setMessage("You have answered to $score questions correctly from $questionNo questions")
            .setPositiveButton("ok"){
                dialog,which->
            }
        alertDialog.show()


    }

    fun clearGame(){
        binding.restartGame.visibility=View.VISIBLE
        binding.textSavol.text=""
        binding.btnAnswer1.text=""
        binding.btnAnswer2.text=""
        binding.btnAnswer3.text=""
        binding.btnAnswer4.text=""

        binding.btnAnswer1.isEnabled=false
        binding.btnAnswer2.isEnabled=false
        binding.btnAnswer3.isEnabled=false
        binding.btnAnswer4.isEnabled=false
        Toast.makeText(context, "Vaqtingiz tugadi", Toast.LENGTH_SHORT).show()
    }

}