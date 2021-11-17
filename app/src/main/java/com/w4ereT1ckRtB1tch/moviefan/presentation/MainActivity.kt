package com.w4ereT1ckRtB1tch.moviefan.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (savedInstanceState == null) {
            initMenuNavigation()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initMenuNavigation()
    }

    private fun initMenuNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfig = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.selectionsFragment,
                R.id.favoritesFragment
            )
        )
        binding.menuNavigation.setupWithNavController(navController)
    }

    //функция отображения SnackBar с заданной позицией и цветом
    fun showSnackBar(text: Int): Snackbar {
        return Snackbar.make(binding.frameSnackBar, text, Snackbar.LENGTH_LONG).also {
            val view = it.view
            val paramsView: CoordinatorLayout.LayoutParams =
                view.layoutParams as CoordinatorLayout.LayoutParams
            paramsView.gravity = Gravity.BOTTOM
            view.layoutParams = paramsView
        }.setBackgroundTint(ContextCompat.getColor(binding.frameSnackBar.context, R.color.ivi_blue))
    }

    //диалоговое окно выходы из приложения
    fun showExitDialog() {
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

}