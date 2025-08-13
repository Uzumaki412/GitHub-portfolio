package com.example.zaidabushawarib_mt_25s

import android.widget.Toast
import com.example.zaidabushawarib_mt_25s.Product

class ProductAdapter (var products: List<Product>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val view: android.view.View)
        : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        val nameTextView: android.widget.TextView = view.findViewById(R.id.product_name)
        val priceTextView: android.widget.TextView = view.findViewById(R.id.product_price)
        val descriptionTextView: android.widget.TextView = view.findViewById(R.id.product_description)
        val imageView: android.widget.ImageView = view.findViewById(R.id.product_image)
        val buyName: android.widget.Button = view.findViewById(R.id.buyNowButton)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ProductViewHolder {
        val view = android.view.LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.nameTextView.text = product.name
        holder.priceTextView.text = product.price.toString()
        holder.descriptionTextView.text = product.description
        holder.imageView.setImageResource(product.imageUrl)
        holder.buyName.setOnClickListener {
            Toast.makeText(holder.view.context, "Product coming soon!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int = products.size

}