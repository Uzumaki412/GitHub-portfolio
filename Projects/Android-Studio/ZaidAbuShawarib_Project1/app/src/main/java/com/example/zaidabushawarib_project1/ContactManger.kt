package com.example.zaidabushawarib_project1

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContactManger private constructor(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("contacts_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val contactsKey = "contacts"


    companion object {
        @Volatile
        private var instance: ContactManger? = null

        fun getInstance(context: Context): ContactManger {
            return instance ?: synchronized(this) {
                instance ?: ContactManger(context).also { instance = it }
            }
        }
    }
    fun saveContacts(contacts: List<Contact>) {
        val json = gson.toJson(contacts)
        sharedPreferences.edit().putString(contactsKey, json).apply()
    }

    fun getContacts(): MutableList<Contact> {
        val json = sharedPreferences.getString(contactsKey, null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<Contact>>() {}.type
            gson.fromJson(json, type) ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }

    fun addContact(contact: Contact) {
        val contacts = getContacts()
        contacts.add(contact)
        saveContacts(contacts)
    }
}