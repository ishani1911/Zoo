package com.example.food


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listofAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load Animal
        listofAnimals.add(Animal("Babon","Babon lives in Zoo",R.drawable.baboon,false))
        listofAnimals.add(Animal("Bulldog","Bulldog lives in Zoo",R.drawable.bulldog, false))
        listofAnimals.add(Animal("Panda","Panda lives in Zoo",R.drawable.panda, true))
        listofAnimals.add(Animal("Swallow Bird","Swallow Bird lives in Zoo",R.drawable.swallow_bird, false))
        listofAnimals.add(Animal("White Tiger","White Tiger lives in Zoo",R.drawable.white_tiger, true))
        listofAnimals.add(Animal("Zebra","Zebra lives in Zoo",R.drawable.zebra, false))

        adapter = AnimalsAdapter(this,listofAnimals)
        tvListAnimal.adapter = adapter
    }

    fun delete(index : Int){
        listofAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    fun add(index: Int){
        listofAnimals.add(index,listofAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalsAdapter : BaseAdapter{
        var listofAnimals = ArrayList<Animal>()
        var context: Context?=null
        constructor(context: Context, listofAnimals : ArrayList<Animal>):super(){
            this.listofAnimals = listofAnimals
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listofAnimals[position]
            if(animal.isKiller == true){
                var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.animal_killer_ticket,null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {
                    val intent = Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des" ,animal.des!!)
                    intent.putExtra("image" ,animal.image!!)
                    context!!.startActivity(intent)
                  
                }
                return myView
            }else{
                var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.animal_ticket,null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {
                    val intent = Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des" ,animal.des!!)
                    intent.putExtra("image" ,animal.image!!)
                    context!!.startActivity(intent)
                  
                }
                return myView
            }
        }

        override fun getItem(position: Int): Any {
            return listofAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listofAnimals.size
        }

    }
}

