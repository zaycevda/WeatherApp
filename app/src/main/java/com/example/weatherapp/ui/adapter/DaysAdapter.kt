package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemDayBinding

class DaysAdapter : RecyclerView.Adapter<DaysAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemDayBinding::bind)

        fun bind() {
            val context = binding.root.context

            binding.wivDescription.setData(context.getString(R.string.weather_description, "Осадки"))
            binding.wivHumidity.setData(context.getString(R.string.weather_humidity, "80"))
            binding.wivWindSpeed.setData(context.getString(R.string.weather_wind_speed, "4.87"))
            binding.wivTemp.setData(context.getString(R.string.weather_temp, "5"))
            binding.tvDate.text = context.getString(R.string.date, "11.08.2023")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 4
}