package com.example.weatherapp.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemDayBinding
import com.example.weatherapp.domain.model.Weather
import kotlin.math.roundToInt

class DaysAdapter : RecyclerView.Adapter<DaysAdapter.ViewHolder>() {
    private val differ = AsyncListDiffer(this, DiffUtilCallback())

    var weathers: List<Weather>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding(ItemDayBinding::bind)

        fun bind(weather: Weather) {
            val context = binding.root.context

            val date = weather.date
            val day = weather.day
            val condition = day.condition
            val humidity = day.humidity.roundToInt().toString()
            val temp = day.temp.toString()
            val windSpeed = day.windSpeed.toString()
            val description = condition.description
            val icon = condition.icon
            val iconLink = "$PROTOCOL$icon"

            binding.tvDate.text = context.getString(R.string.date, date)
            binding.wivHumidity.setData(context.getString(R.string.weather_humidity, humidity))
            binding.wivTemp.setData(context.getString(R.string.weather_temp, temp))
            binding.wivWindSpeed.setData(context.getString(R.string.weather_wind_speed, windSpeed))
            binding.wivDescription.setData(context.getString(R.string.weather_description, description))
            Glide.with(context).load(iconLink).into(binding.ivCondition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weathers[position])
    }

    override fun getItemCount() = weathers.count()

    private class DiffUtilCallback : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather) = oldItem.day == newItem.day
        override fun areContentsTheSame(oldItem: Weather, newItem: Weather) = oldItem == newItem
    }

    private companion object {
        private const val PROTOCOL = "https:"
    }
}