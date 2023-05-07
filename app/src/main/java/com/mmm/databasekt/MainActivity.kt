package com.mmm.databasekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmm.databasekt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private   val TAG = "MainActivity"
     lateinit var adapter: StudentAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.btnsubmit.setOnClickListener {
            var name = binding.edtname.text.toString()
            var surname = binding.edtsurname.text.toString()

            var data = StudentModel(0, name, surname)
            dbHelper.addStudents(data)

            adapter.update(dbHelper.getStudents())

        }

        var list = dbHelper.getStudents()
        adapter = StudentAdapter(list)
        binding.rcvstudentlist.layoutManager = LinearLayoutManager(this)
        binding.rcvstudentlist.adapter = adapter

    }
}