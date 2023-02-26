package com.syam.pustakata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var etBuku: EditText
    private lateinit var etPenulis: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //button batal
        val bt_profil = findViewById<Button>(R.id.bt_batal)
        bt_profil.setOnClickListener {
            finish()
        }

        //inisialisasi firebase
        db = FirebaseFirestore.getInstance()

        etBuku =findViewById(R.id.et_buku)
        etPenulis =findViewById(R.id.et_penulis)

        val btInput = findViewById<Button>(R.id.bt_input)
        btInput.setOnClickListener {
            val buku = etBuku.text.toString().trim()
            val penulis = etPenulis.text.toString().trim()

            if (buku.isEmpty() || penulis.isEmpty()) {
                return@setOnClickListener
            }

            val koleksi = hashMapOf(
                "buku" to buku,
                "penulis" to penulis,
                "createdAt" to Date()
            )

            db.collection("koleksi")
                .add(koleksi)
                .addOnSuccessListener {
                    intent.putExtra("book", koleksi)
                    finish()
                }
                .addOnFailureListener {
                    // handle exception
                }
        }

    }
}