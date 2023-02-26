package com.syam.pustakata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditActivity : AppCompatActivity() {

    private lateinit var et_buku: EditText
    private lateinit var et_penulis: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

            et_buku = findViewById(R.id.et_editbuku)
            et_penulis = findViewById(R.id.et_editpenulis)

            et_buku.setText(Book.FIELD_BUKU)
            et_penulis.setText(Book.FIELD_PENULIS)

            findViewById<Button>(R.id.bt_input).setOnClickListener {
                saveData()
            }
        }

    private fun saveData() {
        TODO("Not yet implemented")
    }
}