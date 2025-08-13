package com.example.zaidabushawarib_project1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class EmailFragment : Fragment() {
    private lateinit var ContactName : EditText
    private lateinit var ContactEmail : EditText
    private lateinit var  btnSubmit : Button
    private lateinit var  contactManager : ContactManger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactManager = ContactManger.getInstance(requireContext())
        setupViews(view)
        setupSubmitButton()
    }

    private fun setupViews(view: View) {
        ContactName = view.findViewById(R.id.ContactName)
        ContactEmail = view.findViewById(R.id.EmailAddress)
        btnSubmit = view.findViewById(R.id.btnSubmit)
    }

    private fun setupSubmitButton(){
        btnSubmit.setOnClickListener {
            val name = ContactName.text.toString()
            val email = ContactEmail.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
              val contact = Contact(

                  name = name,
                  value = email,
                  type = Contact.ContactType.EMAIL
              )
                contactManager.addContact(contact)
                ContactName.text.clear()
                ContactEmail.text.clear()
                Toast.makeText(requireContext(), "Contact added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object {


        class EmailFragment : Fragment(R.layout.fragment_email)
    }
}