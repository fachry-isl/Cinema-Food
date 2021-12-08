package com.boredom.cinema_food.ui.home.order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.boredom.cinema_food.R
import com.boredom.cinema_food.data.entity.FoodEntity
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.data.entity.MovieEntity
import com.boredom.cinema_food.data.entity.OrderEntity
import com.boredom.cinema_food.databinding.ActivityOrderBinding
import com.boredom.cinema_food.ui.ViewModelFactory
import com.boredom.cinema_food.utils.DataDummy
import com.boredom.cinema_food.utils.ObjectMaper.getCoffeeImage
import com.boredom.cinema_food.utils.ObjectMaper.getCoffeePrice
import com.boredom.cinema_food.utils.ObjectMaper.getFoodDesc
import com.boredom.cinema_food.utils.ObjectMaper.getFoodImage
import com.boredom.cinema_food.utils.ObjectMaper.getFoodPrice
import com.boredom.cinema_food.utils.ObjectMaper.getJuiceImage
import com.boredom.cinema_food.utils.ObjectMaper.getJuicePrice
import com.boredom.cinema_food.utils.ObjectMaper.getMovieImage
import com.boredom.cinema_food.utils.ObjectMaper.getMoviePrice
import com.boredom.cinema_food.utils.ObjectMaper.getTeaImage
import com.boredom.cinema_food.utils.ObjectMaper.getTeaPrice
import com.boredom.cinema_food.utils.ObjectMaper.getWaterImage
import com.boredom.cinema_food.utils.ObjectMaper.getWaterPrice
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding

    private lateinit var orderedItem: OrderEntity

    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpAddOrder()
        orderedItem = OrderEntity()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[OrderViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movie = extras.getParcelable<MovieEntity>(EXTRA_MOVIE)
            if (movie != null) {
                populateMovieInformation(movie)
                orderedItem.movie = movie.title
            }
        }

        val rvFood = binding.rvFood
        val foodAdapter = FoodAdapter(object : FoodAdapter.OnItemClickListener {
            override fun onItemClick(
                item: FoodEntity?
            ) {
                if (item != null) {
                    orderedItem.food = item.foodName
                }
            }
        })

        setupDrinkRadioButton()
        foodAdapter.setFoods(DataDummy.generateDummyFood())
        with(rvFood) {
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    private fun setupDrinkRadioButton() {
        val radioMineral = binding.radioGroupMineral
        val radioCoffee = binding.radioGroupCoffee
        val radioTea = binding.radioGroupTea
        val radioJuice = binding.radioGroupJuice

        binding.btnResetOrder.setOnClickListener {
            radioMineral.clearCheck()
            radioCoffee.clearCheck()
            radioTea.clearCheck()
            radioJuice.clearCheck()
        }


        radioMineral.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio0 -> {
                    orderedItem.mineralWater = "Normal Mineral Water"
                }
                R.id.radio1 -> {
                    orderedItem.mineralWater = "Cold Mineral Water"
                }
                else -> {
                    orderedItem.mineralWater = null
                }
            }
        }

        radioCoffee.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btn_arabica -> {
                    orderedItem.coffee = "Arabica Coffee"
                }
                R.id.btn_robusta -> {
                    orderedItem.coffee = "Robusta Coffee"
                }
                R.id.btn_gayo -> {
                    orderedItem.coffee = "Gayo Coffee"
                }
                else -> {
                    orderedItem.coffee = null
                }
            }
        }

        radioTea.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btn_hot -> {
                    orderedItem.tea = "Hot Tea"

                }
                R.id.btn_ice -> {
                    orderedItem.tea = "Ice Tea"
                }
                else -> {
                    orderedItem.tea = null
                }
            }
        }

        radioJuice.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btn_avocado -> {
                    orderedItem.juice = "Avocado Juice"
                }
                R.id.btn_guava -> {
                    orderedItem.juice = "Guava Juice"
                }
                R.id.btn_orange -> {
                    orderedItem.juice = "Orange Juice"
                }
                else -> {
                    orderedItem.juice = null
                }
            }
        }
    }

    private fun setUpAddOrder() {
        binding.btnAddOrder.setOnClickListener {
            if (orderedItem.movie != null) {
                val itemMovie =
                    ItemOrderEntity(
                        0,
                        "Ticket Movie",
                        getMovieImage(orderedItem.movie),
                        orderedItem.movie,
                        getMoviePrice(orderedItem.movie),
                        1
                    )
                viewModel.insertItemOrder(itemMovie)
            }
            if (orderedItem.mineralWater != null) {
                val itemWater = ItemOrderEntity(
                    0,
                    "Mineral Water",
                    getWaterImage(orderedItem.mineralWater),
                    orderedItem.mineralWater,
                    getWaterPrice(orderedItem.mineralWater),
                    1
                )
                viewModel.insertItemOrder(itemWater)
            }
            if (orderedItem.coffee != null) {
                val itemCoffee = ItemOrderEntity(
                    0,
                    "Coffee",
                    getCoffeeImage(orderedItem.coffee),
                    orderedItem.coffee,
                    getCoffeePrice(orderedItem.coffee),
                    1
                )
                viewModel.insertItemOrder(itemCoffee)
            }

            if (orderedItem.tea != null) {
                val itemTea = ItemOrderEntity(
                    0,
                    "Tea",
                    getTeaImage(orderedItem.tea),
                    orderedItem.tea,
                    getTeaPrice(orderedItem.tea),
                    1
                )
                viewModel.insertItemOrder(itemTea)
            }

            if (orderedItem.juice != null) {
                val itemJuice = ItemOrderEntity(
                    0,
                    "Juice",
                    getJuiceImage(orderedItem.juice),
                    orderedItem.juice,
                    getJuicePrice(orderedItem.juice),
                    1
                )
                viewModel.insertItemOrder(itemJuice)
            }
            if (orderedItem.food != null) {
                val itemFood = ItemOrderEntity(
                    0,
                    orderedItem.food,
                    getFoodImage(orderedItem.food),
                    getFoodDesc(orderedItem.food),
                    getFoodPrice(orderedItem.food),
                    1
                )
                viewModel.insertItemOrder(itemFood)
            }
            finish()
        }
    }


    private fun populateMovieInformation(movie: MovieEntity) {
        Glide.with(applicationContext)
            .load(movie.image)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.ivMovieOrder)
        binding.tvTitleMovieOrder.text = movie.title
        binding.tvDescMovieOrder.text = movie.description
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

}