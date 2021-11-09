package com.example.tippingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tippingapp.databinding.ActivityMainBinding
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    // setup the features binding to limit the view find code
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //call the binding activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //when the calculate button clicked call the func
        binding.calculateButton.setOnClickListener { calculateTip() }


    }//onCreate end;

    // function to calculate the tip
    private fun calculateTip() {

        // get the cost of the service -> String
        val costOfService = (binding.costOfService.text).toString()

        // convert cost str to double or return if null
        val cost = costOfService.toDoubleOrNull() ?: return

        // get percent multiplier based on radio button group
        val percent = when ((binding.tipRadioGroup.checkedRadioButtonId)) {
            R.id.button1 -> 1.20
            R.id.button2 -> 1.15
            R.id.button3 -> 1.10
            R.id.button4 -> 1.05
            else -> 1.20
        }

        // set the cost and tip doubles
        var final_cost = cost * percent
        var tip_amount = final_cost - cost

        // update tip and final cost based on check box
        if(binding.roundSwitch.isChecked){
            //round to the nearest doller (ones value)
            tip_amount = floor(tip_amount)+1
            final_cost = cost+tip_amount
        }
        // final cost strings rounded to 2 decimal places
        val final_cost_string = String.format("%.2f",final_cost)
        val tip_cost_string = String.format("%.2f",tip_amount)

        // update the button and tip amount: text
        binding.calculateButton.text = "The Total Cost is $${final_cost_string}"
        binding.tipAmountText.text = "Tip Amount: $${tip_cost_string}"

        //Tests
        //println("Cost $final_cost")
        //println("percent button $percentButton ${percentButton.javaClass.name}")

        return

    }//calculateTip end;

}//MainActivity class end;