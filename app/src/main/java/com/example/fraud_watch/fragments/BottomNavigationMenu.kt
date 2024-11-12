package com.example.fraud_watch.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fraud_watch.R
import com.example.fraud_watch.activity.DicasActivity
import com.example.fraud_watch.activity.HomeActivity
import com.example.fraud_watch.activity.PerfilActivity
import com.example.fraud_watch.activity.ProcedimentosActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationMenu : Fragment() {

    private lateinit var bottomNavigationMenu: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_navigation_bar, container, false)
        bottomNavigationMenu = view.findViewById(R.id.bottomNavigationView)

        // Configura o listener do menu de navegação
        bottomNavigationMenu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Bottom_Menu_Home -> {

                    if (activity !is HomeActivity) {
                        NavigationStateManager.selectedItemId = R.id.Bottom_Menu_Home

                        val intent = Intent(activity, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        startActivity(intent)
                    }
                    true
                }

                R.id.Bottom_Menu_Perfil -> {
                    if (activity !is PerfilActivity) {
                        // Atualiza o estado do item selecionado no singleton
                        NavigationStateManager.selectedItemId = R.id.Bottom_Menu_Perfil

                        // Navega para a PerfilActivity
                        val intent = Intent(activity, PerfilActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        startActivity(intent)
                    }
                    true
                }

                R.id.Bottom_Menu_Dicas -> {

                    if(activity !is DicasActivity) {
                        NavigationStateManager.selectedItemId = R.id.Bottom_Menu_Dicas

                        val intent = Intent(activity, DicasActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        startActivity(intent)
                    }
                    true
                }

                R.id.Bottom_Menu_Procedimentos -> {
                    if(activity !is ProcedimentosActivity) {
                        NavigationStateManager.selectedItemId = R.id.Bottom_Menu_Procedimentos

                        val intent = Intent(activity, ProcedimentosActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        startActivity(intent)
                    }
                    true
                }

                else -> false
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // Atualiza o item selecionado sempre que o fragmento se tornar visível
        bottomNavigationMenu.selectedItemId = NavigationStateManager.selectedItemId
    }

    object NavigationStateManager {
        var selectedItemId: Int = R.id.Bottom_Menu_Home 
    }
}
