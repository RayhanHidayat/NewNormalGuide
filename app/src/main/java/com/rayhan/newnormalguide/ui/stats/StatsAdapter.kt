package com.rayhan.newnormalguide.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rayhan.newnormalguide.data.api.ApiData
import com.rayhan.newnormalguide.databinding.ListItemBinding
import com.robinhood.ticker.TickerUtils
import java.text.NumberFormat

class StatsAdapter(private val list: MutableList<ApiData>) :
    RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    inner class ViewHolder(private val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {

        fun bind(data: ApiData) {
            with(listItemBinding) {
                tvStates.text = data.state

                tickerPositif.setCharacterLists(TickerUtils.provideNumberList())
                tickerSembuh.setCharacterLists(TickerUtils.provideNumberList())
                tickerDeath.setCharacterLists(TickerUtils.provideNumberList())

                tickerSembuh.text = NumberFormat.getInstance().format(data.negativeIncrease)
                tickerPositif.text = NumberFormat.getInstance().format(data.positiveIncrease)
                tickerDeath.text = NumberFormat.getInstance().format(data.deathIncrease)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}