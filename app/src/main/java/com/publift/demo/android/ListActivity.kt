package com.publift.demo.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val items = arrayOf(
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December")
        val itemsBigger = items + items + items + items

        // Insert an ad slot every 4 items.
        val itemsWithAdSlots = mutableListOf<String>()
        itemsBigger.forEachIndexed { index, item ->
            if (index > 0 && index % 4 == 0) {
                itemsWithAdSlots.add("ADSLOT")
            }
            itemsWithAdSlots.add(item)
        }
        println(itemsWithAdSlots)

        val customAdapter = ListAdapter(itemsWithAdSlots.toTypedArray())

        val recyclerView = findViewById<RecyclerView>(R.id.recycler1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
    }
}