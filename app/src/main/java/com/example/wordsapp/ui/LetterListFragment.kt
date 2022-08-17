package com.example.wordsapp.ui

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.R
import com.example.wordsapp.adapter.LetterAdapter
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {


    // نفعل view binding في fragment
    private var _binding :
            FragmentLetterListBinding? = null // ( _ ) -> مايوصله الا المتغير اللي احدده > binding
    private val binding get() =_binding!! // get()->> مانقدر نحفظه في متغير اخر binding اذا تم حفظ قيمته في

    private lateinit var recyclerView: RecyclerView //(lateinit) ->> راح نسند قيمته بعدين

    //نتابع حالة layout في اي وضع (linear or grid)
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    //Fragment Lifecycle Function.
    //onCreateView >> عشان اربط واجهة Fragment.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater,container,false)
        return binding.root

    }

    //Fragment Lifecycle Function - bind specific views to properties.
    //تسمح لي اتواصل مع بعض العناصر مثل RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       recyclerView = binding.recyclerView
        ChangeLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //دالة مسؤولة عن تغيير حالة layout
    private fun ChangeLayout(){
        if(isLinearLayoutManager)
            recyclerView.layoutManager = LinearLayoutManager(context)
        else
            recyclerView.layoutManager = GridLayoutManager(context,4)


        recyclerView.adapter = LetterAdapter()
    }

    //نغير شكل الايقونة بناء على اختيار المستخدم
    private  fun ChangeIcon(menuItem: MenuItem?){

        menuItem?.icon = if(isLinearLayoutManager)
            ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_grid_layout)
        else
            ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_linear_layout)

    }

    //نفعل option menu
    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater){
        inflater.inflate(R.menu.menu_layout,menu)
        val menuButton = menu?.findItem(R.id.action_switch_layout)
        ChangeIcon(menuButton)
    }

    //اذا ضغط المستخدم على optionMenu يتفاعل معه
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                ChangeIcon(item)
                ChangeLayout()
                return true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }




}