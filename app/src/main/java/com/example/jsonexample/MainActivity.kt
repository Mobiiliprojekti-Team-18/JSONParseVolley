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
            Response.Listener { response ->
                //Haetaan koko JSOn array
                val data = response.toString()
                var jArray = JSONArray(data)
                //Log.e("array", jArray.toString())

                //Loopataan arraysta objektit
                for (i in 0..jArray.length()-1){
                    var jObject = jArray.getJSONObject(i)
                    //Log.e("jObject", jObject.toString())

                    //Erotellaan userId, id, title, body
                    var id = jObject.getInt("id")
                    var name = jObject.getString("name")
                    var username = jObject.getString("username")
                    Log.e("id", id.toString())
                    Log.e("name", name.toString())
                    Log.e("username", username.toString())

                    //Aseta nimi textviewiin
                    if (id == 9 ){
                        tv.text = name
                    }
                }
            },Response.ErrorListener {  })
        queue.add(request)
    }
}