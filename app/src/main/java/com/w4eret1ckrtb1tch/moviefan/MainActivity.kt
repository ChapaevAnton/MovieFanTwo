package com.W4ereT1ckRtB1tch.moviefan

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.W4ereT1ckRtB1tch.moviefan.data.Film
import com.W4ereT1ckRtB1tch.moviefan.ui.details.FilmDetailsFragment
import com.W4ereT1ckRtB1tch.moviefan.ui.favorites.FavoritesFragment
import com.W4ereT1ckRtB1tch.moviefan.ui.home.HomeFragment
import com.W4ereT1ckRtB1tch.moviefan.ui.selections.SelectionsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.moviefan.R


class MainActivity : AppCompatActivity() {

    companion object {
        const val ITEM_FILM_DETAILS = "ITEM_FILM_DETAILS"
        const val HOME_FRAGMENT_TAG = "home_fragment"
        const val SELECTIONS_FRAGMENT_TAG = "selections_fragment"
        const val FAVORITES_FRAGMENT_TAG = "favorite_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //добавление default фрагмента
        val fragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.main_fragment_container)
        if (fragment == null) {
            fragment = HomeFragment()
            fragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, fragment, HOME_FRAGMENT_TAG)
                .commit()
        }
        //нижнее меню
        val menuMainNavigationBottom =
            findViewById<BottomNavigationView>(R.id.main_menu_navigation_bottom_bar)
        //обработчик выбора пунктов меню Navigation Bottom
        menuMainNavigationBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_menu_home -> {
                    switchFragmentMenu(
                        checkFragmentExistence(HOME_FRAGMENT_TAG) ?: HomeFragment(),
                        HOME_FRAGMENT_TAG
                    )
                    //showSnackBar(R.string.main_menu_home)
                    true
                }

                R.id.main_menu_my_selections -> {
                    switchFragmentMenu(
                        checkFragmentExistence(SELECTIONS_FRAGMENT_TAG) ?: SelectionsFragment(),
                        SELECTIONS_FRAGMENT_TAG
                    )
                    //showSnackBar(R.string.main_menu_my_selections)
                    true
                }

                R.id.main_menu_favorites -> {
                    switchFragmentMenu(
                        checkFragmentExistence(FAVORITES_FRAGMENT_TAG) ?: FavoritesFragment(),
                        FAVORITES_FRAGMENT_TAG
                    )
                    //showSnackBar(R.string.main_menu_favorites)
                    true
                }

                R.id.main_menu_profile -> {
                    showSnackBar(R.string.main_menu_profile)
                    true
                }
                else -> false
            }
        }
    }
    //кнопка назад
    override fun onBackPressed() {
        Log.d("TAG", "onBackPressed: ${supportFragmentManager.backStackEntryCount}")
        if (supportFragmentManager.backStackEntryCount == 0) showExitDialog() else super.onBackPressed()
    }
    //функция открытия и передачи данных фрагменту FilmDetailsFragment
    fun launchFilmDetailsFragment(film: Film) {
        val bundle = Bundle()
        bundle.putParcelable(ITEM_FILM_DETAILS, film)
        val filmDetailsFragment = FilmDetailsFragment()
        filmDetailsFragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, filmDetailsFragment)
            .addToBackStack(null)
            .commit()
        Log.d("TAG", "launchFilmDetailsFragment: ${supportFragmentManager.backStackEntryCount}")
        supportFragmentManager
    }
    //функция отображения SnackBar с заданной позицией и цветом
    fun showSnackBar(text: Int) {
        val viewSnackBar = findViewById<CoordinatorLayout>(R.id.main_frame_snack_bar)
        Snackbar.make(viewSnackBar, text, Snackbar.LENGTH_LONG).also {
            val view = it.view
            val paramsView: CoordinatorLayout.LayoutParams =
                view.layoutParams as CoordinatorLayout.LayoutParams
            paramsView.gravity = Gravity.BOTTOM
            view.layoutParams = paramsView
        }.setBackgroundTint(ContextCompat.getColor(viewSnackBar.context, R.color.ivi_blue)).show()
    }

    //диалоговое окно выходы из приложения
    private fun showExitDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)

        builder
            .setTitle(R.string.exit_dialog_title)
            .setMessage(R.string.exit_dialog_message)
            .setPositiveButton(R.string.exit_dialog_yes) { _, _ ->
                finish()
            }
            .setNegativeButton(R.string.exit_dialog_no) { dialog, _ ->
                dialog.cancel()
            }

        val exitDialog: AlertDialog = builder.create()
        exitDialog.setCanceledOnTouchOutside(false)
        exitDialog.show()
    }
    //выбор фрагментов раздела контейнера
    private fun switchFragmentMenu(fragment: Fragment, tag: String) {
        Log.d("TAG", "switchFragmentMenu: $tag")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment, tag)
            .commit()
    }

    private fun checkFragmentExistence(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

}