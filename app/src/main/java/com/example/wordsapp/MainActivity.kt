/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding
import java.util.zip.Inflater

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    //نتابع حالة layout في اي وضع (linear or grid)
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Sets the LinearLayoutManager of the recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LetterAdapter()



    }
    //دالة مسؤولة عن تغيير حالة layout
    private fun ChangeLayout(){
        if(isLinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(this)
        }else{
            recyclerView.layoutManager = GridLayoutManager(this,4)
        }
        recyclerView.adapter = LetterAdapter()

    }

    //نغير شكل الايقونة بناء على اختيار المستخدم
    private fun ChangeIcon(menuItem: MenuItem?){
        if(menuItem == null)
            return

        menuItem.icon = if(isLinearLayoutManager)
            ContextCompat.getDrawable(this,R.drawable.ic_grid_layout)
        else
            ContextCompat.getDrawable(this,R.drawable.ic_linear_layout)
    }


    //نفعل option menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //فعلنا زر menu في شاشة المستخدم
       menuInflater.inflate(R.menu.layout_menu,menu)

        //يظهر لنا بالبداية ListIcon
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        ChangeIcon(layoutButton)

        return  true }

    //اذا ضغط المستخدم على optionMenu يتفاعل معه
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch_layout->{
                isLinearLayoutManager=!isLinearLayoutManager
                ChangeLayout()
                ChangeIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
