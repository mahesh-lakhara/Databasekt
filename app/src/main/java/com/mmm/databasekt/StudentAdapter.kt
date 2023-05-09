package com.mmm.databasekt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class StudentAdapter(click: (Int) -> Unit) : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {
    var click = click
    lateinit var list : ArrayList<StudentModel>

    class StudentHolder(itemView: View) : ViewHolder(itemView){
        var id = itemView.findViewById<TextView>(R.id.txtId)
        var name = itemView.findViewById<TextView>(R.id.txtName)
        var Surname = itemView.findViewById<TextView>(R.id.txtSurname)
        var delete = itemView.findViewById<ImageView>(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.iteam,parent,false)
        return StudentHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
       holder.id.text = list.get(position).id.toString()
       holder.name.text = list.get(position).name
       holder.Surname.text = list.get(position).surname

        holder.delete.setOnClickListener {
            click.invoke(list.get(position).id)
        }
    }

    fun update(students: ArrayList<StudentModel>) {
        list = students
        notifyDataSetChanged()
    }

    fun setStudents(list: ArrayList<StudentModel>) {
        this.list = list
    }
}