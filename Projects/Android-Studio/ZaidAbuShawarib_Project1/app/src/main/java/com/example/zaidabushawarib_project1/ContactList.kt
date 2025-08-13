package com.example.zaidabushawarib_project1

data class Contact(
    val name: String,
    val value: String,
    val type: ContactType
){
    enum class ContactType {
        EMAIL, PHONE
    }
}