package com.startup.tugas_3_eureka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.notkamui.keval.keval
import com.startup.tugas_3_eureka.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///event click button all clear
        binding.btnAllClear.setOnClickListener(){
            binding.tvCalc.text=""
            binding.tvResult.text="0"
        }

        ///event click button clear
        binding.btnClear.setOnClickListener(){
            var length = binding.tvCalc.text.length
            if (binding.tvCalc.text.isNotEmpty()){
                binding.tvCalc.text=binding.tvCalc.text.subSequence(0,length - 1)
            }
        }

        ///event click button plus
        binding.btnPlus.setOnClickListener(){
            showOperator(binding.btnPlus)
        }

        ///event click button minus
        binding.btnMinus.setOnClickListener(){
            showOperator(binding.btnMinus)
        }

        ///event click button multiple
        binding.btnMultiple.setOnClickListener(){
            showOperator(binding.btnMultiple)
        }

        ///event click button divide
        binding.btnDivide.setOnClickListener(){
            showOperator(binding.btnDivide)
        }

        ///event click button coma
        binding.btnComa.setOnClickListener(){
            showOperator(binding.btnComa)
        }

        ///event click button zero
        binding.btnZero.setOnClickListener(){
            var lastChar : Char = binding.tvCalc.text.toString().last()
            //mengantisipasi penyebut nya 0
            if (lastChar!='/'){
                binding.tvCalc.append(binding.btnZero.text)
            }
        }

        ///event click button one
        binding.btnOne.setOnClickListener(){
            binding.tvCalc.append(binding.btnOne.text)
        }

        ///event click button two
        binding.btnTwo.setOnClickListener(){
            binding.tvCalc.append(binding.btnTwo.text)
        }

        ///event click button three
        binding.btnThree.setOnClickListener(){
            binding.tvCalc.append(binding.btnThree.text)
        }

        ///event click button four
        binding.btnFour.setOnClickListener(){
            binding.tvCalc.append(binding.btnFour.text)
        }

        ///event click button five
        binding.btnFive.setOnClickListener(){
            binding.tvCalc.append(binding.btnFive.text)
        }

        ///event click button six
        binding.btnSix.setOnClickListener(){
            binding.tvCalc.append(binding.btnSix.text)
        }

        ///event click button seven
        binding.btnSeven.setOnClickListener(){
            binding.tvCalc.append(binding.btnSeven.text)
        }

        ///event click button eight
        binding.btnEight.setOnClickListener(){
            binding.tvCalc.append(binding.btnEight.text)
        }

        ///event click button nine
        binding.btnNine.setOnClickListener(){
            binding.tvCalc.append(binding.btnNine.text)
        }

        ///event click button equal
        binding.btnEqual.setOnClickListener(){
            if (binding.tvCalc.text.isNotEmpty()){
                var lastChar : Char = binding.tvCalc.text.toString().last()
                if (lastChar!='+'&&lastChar!='-'&&lastChar!='*'&&lastChar!='/'&&lastChar!='.'){
                    binding.tvResult.text= binding.tvCalc.text.toString().keval().toString()
                }
            }
        }
    }

//    fun mathExpAdjustment(){
//
//    }

    ///menampilkan lambang operator di perhitungan
    private fun showOperator(button: Button){
        if (binding.tvCalc.text.isNotEmpty()){
            var lastChar : Char = binding.tvCalc.text.toString().last()
            if (lastChar!='+'&&lastChar!='-'&&lastChar!='*'&&lastChar!='/'&&lastChar!='.'){
                when(button){
                    binding.btnMultiple -> binding.tvCalc.append("*")
                    binding.btnDivide -> binding.tvCalc.append("/")
                    binding.btnComa -> binding.tvCalc.append(".")
                    else -> binding.tvCalc.append(button.text)
                }
            }
        }

    }
}