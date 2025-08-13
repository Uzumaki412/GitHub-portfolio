package com.example.zaidabushawarib_project1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class PhoneFragment : Fragment() {
    private lateinit var ContactName : EditText
    private lateinit var ContactPhone : EditText
    private lateinit var  btnSubmit : Button
    private lateinit var  contactManager : ContactManger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactManager = ContactManger.getInstance(requireContext())
        setupViews(view)
        setupSubmitButton()
    }

    private fun setupViews(view: View) {
        ContactName = view.findViewById(R.id.ContactName)
        ContactPhone = view.findViewById(R.id.PhoneNumber)
        btnSubmit = view.findViewById(R.id.btnSubmit)
    }

    private fun setupSubmitButton(){
        btnSubmit.setOnClickListener {
            val name = ContactName.text.toString()
            val phone = ContactPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val contact = Contact(
                    name = name,
                    value = phone,
                    type = Contact.ContactType.PHONE
                )
                contactManager.addContact(contact)
                ContactName.text.clear()
                ContactPhone.text.clear()
                Toast.makeText(requireContext(), "Contact added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        class PhoneFragment : Fragment(R.layout.fragment_phone)
    }
}