package com.example.zaidabushawarib_mt_25s

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val productList = listOf(
            Product("Product 1", 25.00, "P1_Description", R.drawable.product1),
            Product("Product 2", 40.00, "P2_Description", R.drawable.product2),
            Product("Product 3", 35.00, "P3_Description", R.drawable.product3),
            Product("Product 4", 35.00, "P4_Description", R.drawable.product4),
            Product("Product 5", 5.00, "P5_Description", R.drawable.productempty),
            Product("Product 6", 15.00, "P6_Description", R.drawable.productempty),
            Product("Product 7", 25.00, "P7_Description", R.drawable.productempty),
            Product("Product 8", 105.00, "P8_Description", R.drawable.productempty),
        )



        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.adapter = ProductAdapter(productList)

    }
}