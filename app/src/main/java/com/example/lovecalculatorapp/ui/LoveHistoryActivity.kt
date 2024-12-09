package com.example.lovecalculatorapp.ui

import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculatorapp.R
import com.example.lovecalculatorapp.data.local.LoveDataBase
import com.example.lovecalculatorapp.data.local.LoveEntity
import com.example.lovecalculatorapp.databinding.ActivityLoveHistoryBinding
import com.example.lovecalculatorapp.ui.adapters.LoveHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoveHistoryActivity : AppCompatActivity(), OnClickItem {

    @Inject
    lateinit var loveDataBase: LoveDataBase

    private val binding by lazy {
        ActivityLoveHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = LoveHistoryAdapter(this)
        binding.rvHistory.adapter = adapter

        loveDataBase.loveDao().getByPercentage().observe(this) {
            adapter.submitList(it)
        }
        binding.svLove.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    loveDataBase.loveDao().getByNames(newText).observe(this@LoveHistoryActivity) {
                        adapter.submitList(it)
                    }
                }
                return true
            }
        })
    }

    override fun setOnCLickItem(loveEntity: LoveEntity) {
        val builder = AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.are_you_sure))
            setPositiveButton(R.string.yes) { _, _ ->
                loveDataBase.loveDao().deleteLove(loveEntity)
            }
            setNegativeButton(R.string.No) { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }
}