package com.syam.pustakata

data class Book(val id: String? = null, val buku: String, val penulis: String, val status: String) {
    companion object {
        const val FIELD_ID = "id"
        const val FIELD_BUKU = "buku"
        const val FIELD_PENULIS = "penulis"
        const val FIELD_STATUS = "status"
    }
}
