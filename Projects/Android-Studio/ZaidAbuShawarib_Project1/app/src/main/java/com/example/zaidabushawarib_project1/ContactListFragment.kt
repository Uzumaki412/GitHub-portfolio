package com.example.zaidabushawarib_project1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import com.example.zaidabushawarib_project1.ContactManger
import androidx.core.content.edit

// Fragment to display the list of contacts using a RecyclerView
class ContactListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView // RecyclerView to show contacts
    private lateinit var contactListAdapter: ContactListAdapter // Adapter for RecyclerView
    private lateinit var contactManager : ContactManger // Manages contact data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Inflate the fragment layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    // Called after the view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize contact manager and set up RecyclerView
        contactManager = ContactManger.getInstance(requireContext())
        setupRecyclerView(view)
        loadContacts()
    }

    // Set up the RecyclerView and its adapter
    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rv_contacts)
        contactListAdapter = ContactListAdapter(mutableListOf())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contactListAdapter
        }
    }

    // Load contacts from storage and update the adapter
    private fun loadContacts() {
        val contacts = contactManager.getContacts()
        contactListAdapter.updateContacts(contacts)
    }

    companion object {
        // Unused inner class, can be removed
        class ContactListFragment : Fragment(R.layout.fragment_contact_list)
    }
}