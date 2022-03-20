package com.useryoo.basicfunctionhookpractise

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.useryoo.basicfunctionhookpractise.databinding.ActivityMainBinding

private var clicked=0
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (hookMe()){
                clicked++
                when (clicked) {
                    2 -> {
                        Toast.makeText(this,"Where do you enter the flag",Toast.LENGTH_SHORT)
                            .show()
                    }
                    3 -> {
                        Toast.makeText(this,"Submit button??",Toast.LENGTH_SHORT)
                            .show()
                    }
                    1 -> {
                        Toast.makeText(this, "You cant get the flag so easily:D", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                clicked %= 3
            }
            else{
                binding.button2.visibility= View.VISIBLE
                binding.editTextFlag.visibility=View.VISIBLE
                Toast.makeText(this,"Flag is ${getString(R.string.flag)}",Toast.LENGTH_LONG).show()

                binding.button2.setOnClickListener {
                    val input_flag=binding.editTextFlag.text.toString()
                    if(input_flag == getString(R.string.flag)){
                        val intent = Intent(this,SuccessPageActivity::class.java)
                        startActivity(intent)

                    }
                    else{
                        Toast.makeText(this,"this is the flag bro : ${getString(R.string.flag)}",Toast.LENGTH_SHORT)
                            .show()
                    }
                    binding.editTextFlag.text=null
                }
            }
        }
    }
    private fun hookMe(): Boolean {
        return true
    }
}