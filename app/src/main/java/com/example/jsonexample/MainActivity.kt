package com.example.jsonexample

import android.annotation.SuppressLint
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
    var idNum : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener{
           downloadTask()
           incrementId()
        }
    }

    @SuppressLint("SetTextI18n")
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

                //Loopataan arraysta objektit
                for (i in 0..jArray.length()-1){
                    val jObject = jArray.getJSONObject(i)

                    //Erotellaan id, name, userName objectista
                    val id = jObject.getInt("id")
                    val name = jObject.getString("name")
                    val username = jObject.getString("username")

                    //Haetaan nested object address
                    val address = jObject.getJSONObject("address")

                    //Haetaan address objectista street ja city
                    val street = address.getString("street")
                    val city = address.getString("city")

                    //Asetetaan id, nimi ja osoite textviewiin
                    if (id == idNum ){
                        tvId.text = "Id: " + id
                        tvName.text = "Name: " + name
                        tvAddress.text = "Address: " + street + " , " + city
                    }
                }
            }, {  })
        queue.add(request)
    }

    fun incrementId(){
        idNum++
        if (idNum==11){
            idNum=1
        }
    }
}