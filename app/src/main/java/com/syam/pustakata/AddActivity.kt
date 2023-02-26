package com.syam.pustakata

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        //prgres dialog
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)

        //inisialisasi firebase
        db = FirebaseFirestore.getInstance()

        etBuku =findViewById(R.id.et_buku)
        etPenulis =findViewById(R.id.et_penulis)

        val btInput = findViewById<Button>(R.id.bt_input)
        btInput.setOnClickListener {
            val buku = etBuku.text.toString().trim()
            val penulis = etPenulis.text.toString().trim()

            if (buku.isEmpty() || penulis.isEmpty()) {
                Toast.makeText(this,"Masukan data terlebih dahulu",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val koleksi = hashMapOf(
                "buku" to buku,
                "penulis" to penulis,
                "status" to "TERSEDIA",
                "createdAt" to Date()
            )

            val collectionRef = db.collection("koleksi")
            val documentRef = collectionRef.document(buku)
            // Menampilkan progress dialog
            progressDialog.show()
            //input data ke firestore
            documentRef.set(koleksi)
                .addOnSuccessListener {
                    // Menutup progress dialog
                    progressDialog.dismiss()
                    intent.putExtra("book", koleksi)
                    finish()
                }
                .addOnFailureListener {
                    // Menutup progress dialog
                    progressDialog.dismiss()
                }
        }

    }
}