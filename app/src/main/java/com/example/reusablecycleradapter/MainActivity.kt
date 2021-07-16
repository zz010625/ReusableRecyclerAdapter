package com.example.reusablecycleradapter

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reusablerecycleradapter.R
import com.example.reusablerecycleradapter.databinding.ItemTest1Binding


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: ReusableRecyclerAdapter<*>
    private lateinit var recyclerView: RecyclerView
    private var textList = ArrayList<String>()
    private var imageList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_test)
        for (i in 0 until 10) {
            textList.add(i.toString())
            imageList.add(i.toString())
        }
        recyclerAdapter = ReusableRecyclerAdapter(textList, imageList)
        recyclerAdapter
            .onBindView(ReusableRecyclerAdapter.BindingCallBack<ItemTest1Binding>(
                layoutId = R.layout.item_test1,
                isItemPosition = { position: Int -> position in 0 until 10 },
                onBindView = { binding: ItemTest1Binding, holder: ReusableRecyclerAdapter.BindingVH, position: Int ->
                    binding.text = textList[position]
                }
            ))
            .onBindView(ReusableRecyclerAdapter.CommonCallBack<Test2ViewHolder>(
                layoutId = R.layout.item_test2,
                isItemPosition = { position: Int -> position in 10 until 20 },
                viewHolderClass = Test2ViewHolder::class.java,
                onBindView = { holder: Test2ViewHolder, position: Int ->
                    holder.text2.text = textList[position - 10]
                }
            ))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
    }

    class Test2ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
      val text2=itemView.findViewById<TextView>(R.id.tv_test2)
    }
}