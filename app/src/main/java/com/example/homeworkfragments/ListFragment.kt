package com.example.homeworkfragments

import ApiClient
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkfragments.databinding.ListFragmentLayoutBinding
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListFragment : Fragment(), SuperheroRecyclerViewAdapter.OnItemClickListener{
    private lateinit var binding: ListFragmentLayoutBinding
    private var onItemClick:(String) -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val api =ApiClient.client.create(ApiInterface::class.java)
        val adapter = SuperheroRecyclerViewAdapter(onItemClickListener = this)

        api.getSuperheroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.items = it
                adapter.notifyDataSetChanged()
                binding.superheroRecyclerview.adapter = adapter
            },
            {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            })
        binding.superheroRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClick(item: Superheroes) {

    }

    fun setItemClickListener(lambda: (String)-> Unit) {
        onItemClick = lambda
    }
}

data class Superheroes (

    @SerializedName("id"          ) var id          : Int?         = null,
    @SerializedName("name"        ) var name        : String?      = null,
    @SerializedName("slug"        ) var slug        : String?      = null,
    @SerializedName("powerstats"  ) var powerstats  : Powerstats?  = Powerstats(),
    @SerializedName("appearance"  ) var appearance  : Appearance?  = Appearance(),
    @SerializedName("biography"   ) var biography   : Biography?   = Biography(),
    @SerializedName("work"        ) var work        : Work?        = Work(),
    @SerializedName("connections" ) var connections : Connections? = Connections(),
    @SerializedName("images"      ) var images      : Images?      = Images()

)

data class Powerstats (

    @SerializedName("intelligence" ) var intelligence : Int? = null,
    @SerializedName("strength"     ) var strength     : Int? = null,
    @SerializedName("speed"        ) var speed        : Int? = null,
    @SerializedName("durability"   ) var durability   : Int? = null,
    @SerializedName("power"        ) var power        : Int? = null,
    @SerializedName("combat"       ) var combat       : Int? = null

)

data class Appearance (

    @SerializedName("gender"    ) var gender    : String?           = null,
    @SerializedName("race"      ) var race      : String?           = null,
    @SerializedName("height"    ) var height    : ArrayList<String> = arrayListOf(),
    @SerializedName("weight"    ) var weight    : ArrayList<String> = arrayListOf(),
    @SerializedName("eyeColor"  ) var eyeColor  : String?           = null,
    @SerializedName("hairColor" ) var hairColor : String?           = null

)

data class Biography (

    @SerializedName("fullName"        ) var fullName        : String?           = null,
    @SerializedName("alterEgos"       ) var alterEgos       : String?           = null,
    @SerializedName("aliases"         ) var aliases         : ArrayList<String> = arrayListOf(),
    @SerializedName("placeOfBirth"    ) var placeOfBirth    : String?           = null,
    @SerializedName("firstAppearance" ) var firstAppearance : String?           = null,
    @SerializedName("publisher"       ) var publisher       : String?           = null,
    @SerializedName("alignment"       ) var alignment       : String?           = null

)

data class Work (

    @SerializedName("occupation" ) var occupation : String? = null,
    @SerializedName("base"       ) var base       : String? = null

)

data class Connections (

    @SerializedName("groupAffiliation" ) var groupAffiliation : String? = null,
    @SerializedName("relatives"        ) var relatives        : String? = null

)

data class Images (

    @SerializedName("xs" ) var xs : String? = null,
    @SerializedName("sm" ) var sm : String? = null,
    @SerializedName("md" ) var md : String? = null,
    @SerializedName("lg" ) var lg : String? = null

)
