package com.example.image_api

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.image_api.Adapter
import com.example.image_api.ItemOfList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var imageList : List<ItemOfList> = listOf<ItemOfList>()
    var test : MutableList<ItemOfList> = mutableListOf<ItemOfList>()
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load.setOnClickListener{

            test.clear()

            for(count in 1..5)
            {
                Log.d("Debug_Count", count.toString())
                run("https://aws.random.cat/meow")
            }

            while (true)
            {
                if(test.size == 5)
                {
                    Thread.sleep(1000)
                    break
                }
            }

            var adapter = Adapter(test.toList())

            val recyclerView = findViewById<RecyclerView>(R.id.List)
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.setHasFixedSize(true)

            recyclerView.adapter = adapter

            Log.d("Debug_test_size", test.size.toString())

        }


    }

    private fun run(url:String){
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body()?.string()
                val jsonImage = (JSONObject(json).get("file")).toString()
                this@MainActivity.runOnUiThread {
                    if (response.isSuccessful) {
                        Log.d("Debug_Url_Signal", jsonImage.toString())
                    }
                }
                test.add(ItemOfList(jsonImage.toString()))
                Log.d("Debug_test_size", test.size.toString())

            }

        })
    }
}
