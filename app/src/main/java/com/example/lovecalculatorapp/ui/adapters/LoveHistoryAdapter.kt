package com.example.lovecalculatorapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lovecalculatorapp.data.local.LoveEntity
import com.example.lovecalculatorapp.databinding.ItemHistoryBinding
import com.example.lovecalculatorapp.ui.OnClickItem
import com.example.lovecalculatorapp.ui.adapters.LoveHistoryAdapter.LoveViewHolder

class LoveHistoryAdapter(private val onClickItem: OnClickItem) : ListAdapter<LoveEntity, LoveViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoveViewHolder {
        return LoveViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LoveViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onClickItem.setOnCLickItem(getItem(position))
            true
        }
    }

    inner class LoveViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: LoveEntity) = with(binding) {
            itemTvFName.text = item.firstName
            itemTvSName.text = item.secondName
            itemTvPercentage.text = item.percentage.toString()
            itemTvResult.text = item.result
        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<LoveEntity>() {

        override fun areItemsTheSame(oldItem: LoveEntity, newItem: LoveEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LoveEntity, newItem: LoveEntity): Boolean {
            return oldItem == newItem
        }

    }
}

