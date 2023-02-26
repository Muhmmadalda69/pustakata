package com.syam.pustakata

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.syam.pustakata.BookAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter
    private lateinit var books: MutableList<Book>
    private lateinit var db: FirebaseFirestore

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt_add = findViewById<Button>(R.id.bt_add)
        bt_add.setOnClickListener {
            val it = Intent(this, AddActivity::class.java)
            startActivity(it)
        }

        val bt_profil = findViewById<ImageView>(R.id.bt_akun)
        bt_profil.setOnClickListener {
            val it = Intent(this, ProfilActivity::class.java)
            startActivity(it)
        }

        //fungsi read
        db = FirebaseFirestore.getInstance()
        books = mutableListOf()
        adapter = BookAdapter(this, books)

        recyclerView = findViewById(R.id.recycleview_koleksi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // mengambil data dari firestore
        db.collection("koleksi")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                // membersihkan data lama dan menambahkan data baru ke adapter
                value?.let {
                    val books = mutableListOf<Book>()
                    for (doc in it.documents) {
                        val id = doc.id
                        val buku = doc.getString(Book.FIELD_BUKU) ?: ""
                        val penulis = doc.getString(Book.FIELD_PENULIS) ?: ""
                        val book = Book(id, buku, penulis)
                        books.add(book)
                    }
                    adapter.setBooks(books)
                }
            }
    }
}
