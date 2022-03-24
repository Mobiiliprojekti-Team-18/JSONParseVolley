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
        val tv = findViewById<TextView>(R.id.tvMyTextView)
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

                    //Erotellaan id, name, userName
                    val id = jObject.getInt("id")
                    val name = jObject.getString("name")
                    val username = jObject.getString("username")

                    println("id: ${id}")
                    println("name: ${name}")
                    println("userName: ${username}")

                    //Aseta nimi textviewiin
                    if (id == 1 ){
                        tv.text = "Name: " + name
                    }
                }
            }, {  })
        queue.add(request)
    }
}