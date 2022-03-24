package com.example.jsonexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    val url = "https://jsonplaceholder.typicode.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener(View.OnClickListener{
           downloadTask()
        })
    }

    fun downloadTask(){
        val tvName = findViewById<TextView>(R.id.tvMyTextView)
        val tvId = findViewById<TextView>(R.id.tvId)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)

        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET,url,
            { response ->
                //Haetaan koko JSOn array
                val data = response.toString()
                val jArray = JSONArray(data)
                //Log.e("array", jArray.toString())

                //Loopataan arraysta objektit
                for (i in 0..jArray.length()-1){
                    val jObject = jArray.getJSONObject(i)
                    //Log.e("jObject", jObject.toString())

                    //Erotellaan id, name, userName objectista
                    val id = jObject.getInt("id")
                    val name = jObject.getString("name")
                    val username = jObject.getString("username")

                    //Haetaan nested object
                    val address = jObject.getJSONObject("address")
                    val street = address.getString("street")
                    val city = address.getString("city")

                    println("id: ${id}")
                    println("name: ${name}")
                    println("userName: ${username}")
                    println("street: ${street}")
                    println("city: ${city}")

                    var idNumber = 1

                    //Aseta nimi textviewiin
                    if (id == idNumber ){
                        tvId.text = "Id: " + id
                        tvName.text = "Name: " + name
                        tvAddress.text = "Address: " + street + " , " + city

                    }
                }
            }, {  })
        queue.add(request)
    }
}