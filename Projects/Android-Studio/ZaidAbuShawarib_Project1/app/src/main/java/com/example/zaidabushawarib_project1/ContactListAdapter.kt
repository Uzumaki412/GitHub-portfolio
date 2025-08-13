package com.example.zaidabushawarib_project1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.zaidabushawarib_project1.Contact.ContactType
import android.widget.ImageView
import android.widget.LinearLayout

// Adapter for displaying a list of contacts in a RecyclerView
class ContactListAdapter (var contactList: MutableList<Contact>) :
    RecyclerView.Adapter<ContactListAdapter.ContactListAdapter>() {

    // ViewHolder class to hold references to the views for each item
    class ContactListAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextLabel: TextView = itemView.findViewById<TextView>(R.id.name_label)
        val nameTextView: TextView = itemView.findViewById(R.id.name_value)
        val valueTextView: TextView = itemView.findViewById(R.id.details_value)
        val valueLabel: TextView = itemView.findViewById(R.id.details_label)
        val actionIcon: ImageView = itemView.findViewById(R.id.action_icon)
        val actionText: TextView = itemView.findViewById(R.id.action_text)
        val actionBtn: LinearLayout = itemView.findViewById(R.id.contact_action_area)
    }

    // Inflates the item layout and creates a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListAdapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ContactListAdapter(view)
    }

    // Binds data to the views in each ViewHolder
    override fun onBindViewHolder(holder: ContactListAdapter, position: Int) {
        val contact = contactList[position]
        holder.nameTextLabel.text = "Name"
        holder.nameTextView.text = contact.name

        // Set up views based on contact type (EMAIL or PHONE)
        when (contact.type) {
            ContactType.EMAIL -> {
                holder.valueLabel.text = "Details"
                holder.valueTextView.text = contact.value
                holder.actionIcon.setImageResource(R.drawable.ic_email_action)
                holder.actionText.text = "Tap to email"
            }

            ContactType.PHONE -> {
                holder.valueLabel.text = "Phone"
                holder.valueTextView.text = contact.value
                holder.actionIcon.setImageResource(R.drawable.ic_phone_action)
                holder.actionText.text = "Tap to call"
            }
        }

        // Set up click listener for action button (call or email)
        holder.actionBtn.setOnClickListener {
            val context = holder.itemView.context
            when (contact.type) {
                ContactType.PHONE -> {
                    val intent = android.content.Intent(android.content.Intent.ACTION_DIAL)
                    intent.data = android.net.Uri.parse("tel:${contact.value}")
                    context.startActivity(intent)
                }
                ContactType.EMAIL -> {
                    val intent = android.content.Intent(android.content.Intent.ACTION_SENDTO)
                    intent.data = android.net.Uri.parse("mailto:${contact.value}")
                    context.startActivity(intent)
                }
            }
        }
        // Set up long click listener to remove contact from list and storage
        holder.itemView.setOnLongClickListener {
            contactList.removeAt(position)
            notifyItemRemoved(position)
            // Also remove from SharedPreferences
            val contactManager = ContactManger.getInstance(holder.itemView.context)
            contactManager.saveContacts(contactList)
            true
        }

    }


    // Returns the total number of items in the list
    override fun getItemCount(): Int = contactList.size

    // Updates the contact list and refreshes the RecyclerView
    fun updateContacts(newContacts: List<Contact>) {
        contactList.clear()
        contactList.addAll(newContacts)
        notifyDataSetChanged()
    }

}