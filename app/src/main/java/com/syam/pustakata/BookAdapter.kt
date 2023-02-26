package com.syam.pustakata

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class BookAdapter(private val context: Context, private var books: List<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBuku: TextView = itemView.findViewById(R.id.tv_judulbuku)
        val tvPenulis: TextView = itemView.findViewById(R.id.tv_namaPenulis)
        val deleteButton: ImageButton = itemView.findViewById(R.id.bt_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.tvBuku.text = book.buku
        holder.tvPenulis.text = book.penulis

        /*
        holder.editButton.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra("book_id", book.id)
            context.startActivity(intent)
        }
        */

        holder.deleteButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Hapus Buku")
                .setMessage("Apakah anda yakin ingin menghapus?")
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    book.id?.let { it1 ->
                        FirebaseFirestore.getInstance().collection("koleksi")
                            .document(it1).delete()
                    }
                }
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
    }

    override fun getItemCount() = books.size
    fun setBooks(books: MutableList<Book>) {
        this.books = books
        notifyDataSetChanged()
    }
}

