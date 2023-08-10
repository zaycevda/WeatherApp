package com.example.weatherapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemWeatherBinding

class WeatherItemView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding by viewBinding(ItemWeatherBinding::bind)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_weather, this, true)
        initAttrs(attrs, defStyleAttr, defStyleRes)
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.WeatherItemView,
            defStyleAttr,
            defStyleRes
        )

        val data = typedArray.getString(R.styleable.WeatherItemView_data)
        setData(data)

        val title = typedArray.getString(R.styleable.WeatherItemView_title)
        binding.tvTitle.text = title

        typedArray.recycle()
    }

    fun setData(data: String?) {
        binding.tvData.text = data
    }
}