package com.mmm.databasekt

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmm.databasekt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var adapter: StudentAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dbHelper = DBHelper(this)
        var list = dbHelper.getStudents()

        adapter = StudentAdapter { id->

            var dialog = AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are You Sure To Delete?")
                .setPositiveButton("Yes",object : DialogInterface.OnClickListener {

                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        dbHelper.deleteStudent(id)
                        adapter.update(dbHelper.getStudents())
                    }
                })
                .setNegativeButton("No",object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }

                })
                .create()
            dialog.show()
        }
        adapter.setStudents(list)
        binding.rcvstudentlist.layoutManager = LinearLayoutManager(this)
        binding.rcvstudentlist.adapter = adapter


        binding.btnsubmit.setOnClickListener {
            var name = binding.edtname.text.toString()
            var surname = binding.edtsurname.text.toString()

            if (name.isEmpty() || surname.isEmpty() ){
                Toast.makeText(this, "Please Enter Data.......", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Data Add Succesfullly", Toast.LENGTH_SHORT).show()
                var data = StudentModel(0, name, surname)
                dbHelper.addStudents(data)
                adapter.update(dbHelper.getStudents())
                clearEditText()
            }
        }
        }



    private fun clearEditText(){
        binding.edtname.setText("")
        binding.edtsurname.setText("")

    }






}
