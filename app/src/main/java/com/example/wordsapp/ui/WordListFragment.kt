package com.example.wordsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.adapter.WordAdapter
import com.example.wordsapp.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {
    companion object{
        const val LETTER = "letter"
        const val Link = "https://www.google.com/search?q="
    }

    // نفعل view binding في fragment
    private var _binding :
            FragmentWordListBinding? = null // ( _ ) -> مايوصله الا المتغير اللي احدده > binding
    private val binding get() =_binding!! // get()->> مانقدر نحفظه في متغير اخر binding اذا تم حفظ قيمته في

    //Fragment Lifecycle Function
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val letter = arguments?.get("letter").toString()
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WordAdapter(letter, requireContext())


        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}