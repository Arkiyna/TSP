package com.example.tsp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MultiselectAdapter(
    private var dataset: MutableList<DataSet>,
    private var showMenuSelect: (Boolean) -> Unit
    ) : RecyclerView.Adapter<MultiselectAdapter.MultiselectViewHolder>() {
    private var isEnable = false
    private val itemSelectedList = ArrayList<Int>()

    class MultiselectViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tv : TextView = view.findViewById(R.id.TextView)
        val iv : ImageView = view.findViewById(R.id.ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiselectViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MultiselectViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MultiselectViewHolder, position: Int) {
        val item = dataset[position]
        holder.tv.text = item.address
        holder.iv.visibility = View.GONE

        holder.tv.setOnLongClickListener {
            selectItem(holder, item, position)

            true
        }

        holder.tv.setOnClickListener {
            if(itemSelectedList.contains((position))) {
                itemSelectedList.removeAt(position)
                holder.iv.visibility = View.GONE
                item.selected = false
                if (itemSelectedList.isEmpty()) {
                    showMenuSelect(false)
                    isEnable = false
                }
            } else if(isEnable) {
                selectItem(holder, item, position)
            }
        }
    }

    private fun selectItem(holder: MultiselectAdapter.MultiselectViewHolder, item: DataSet, position: Int) {
        isEnable = true
        itemSelectedList.add(position)
        item.selected = true
        holder.iv.visibility = View.VISIBLE
        showMenuSelect(true)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun deleteSelectedItem() : ArrayList<Int> {
        if(itemSelectedList.isNotEmpty()) {
            isEnable = false
        }
        return itemSelectedList
    }
}