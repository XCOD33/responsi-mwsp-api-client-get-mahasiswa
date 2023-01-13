package com.example.mahasiswarecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mahasiswarecyclerview.data.Response
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONException
import org.json.JSONObject

class Home : AppCompatActivity() {

    private var listMahasiswa= ArrayList<Response>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getData()
    }

    private fun getData() {
        AndroidNetworking.get("https://responsi-mwsp.loophole.site/api/mahasiswas")
            .addHeaders("Accept", "Application/json")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object: JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    try {
                        if(response!!.getString("status").equals(true.toString())) {
                            val data = response.getJSONArray("data")

                            Log.d("response", data.toString())

                            listMahasiswa.clear()

                            for (i in 0 until data.length()) {
                                val value = data.getJSONObject(i)

                                val id = value.getString("id")
                                val npm = value.getString("npm")
                                val name = value.getString("nama")
                                val major = value.getString("jurusan")
                                val gender = value.getString("jenis_kelamin")
                                val created = value.getString("created_at")

                                val responses =Response(name, created, npm, major, id.toInt(), gender)
                                listMahasiswa.add(responses)
                            }
                        }

                        val recyclerView = findViewById<RecyclerView>(R.id.mahasiswaRecyclerView)

                        recyclerView.adapter =CustomAdapter(listMahasiswa)
                        val layoutManager = LinearLayoutManager(applicationContext)
                        recyclerView.layoutManager = layoutManager
                    } catch (e: JSONException) {
                        Log.e("eResponse", e.toString())
                    }
                }

                override fun onError(anError: ANError?) {

                }
            })
    }
}