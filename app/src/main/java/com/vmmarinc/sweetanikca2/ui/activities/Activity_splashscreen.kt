package com.vmmarinc.sweetanikca2.ui.activities

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.data.models.Product
import com.vmmarinc.sweetanikca2.data.models.StoreInfo
import com.vmmarinc.sweetanikca2.ui.viewmodels.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class Activity_splashscreen : AppCompatActivity() {
    private lateinit var splash: LottieAnimationView
    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        splash = findViewById<LottieAnimationView>(R.id.splashscreen)

    }

    override fun onStart() {
        super.onStart()
        splashViewModel.isLoggedIn()
        splashViewModel.insert(listOf(
            Comment(1, "wef", "dfghfjdngffdghtrbvc", "descripcion 1"),
            Comment(2, "Pepito", "dfghfjdngffdghtrbvc","descr2"),
            Comment(3, "Andres", "dfghfjdngffdghtrbvc", "descr3")
        ), listOf(
            Product(1, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 1", "$5000",  "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(2, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 2", "$10000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(3, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 3", "$20000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(4, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 1", "$5000",  "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(5, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 2", "$10000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(6, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 3", "$20000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(7, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 1", "$5000",  "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(8, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 2", "$10000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(9, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 3", "$20000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            Product(10, "https://www.bettycrocker.lat/wp-content/uploads/2020/12/BClatam-recipe-pastel-arcoiris.png", "Producto 4", "$30000", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit")
        ), StoreInfo(
            1,
            "Sweet Anikca",
            "https://www.nicepng.com/png/full/255-2556817_elegant-images-of-birthday-cakes-png-cake-png.png",
            "Donde vivo 123",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididu",
            null,
            null
        )
        )

        splash.imageAssetsFolder = "image"
        splash.playAnimation()
        splash.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animator: Animator?) {
                /* val intent = Intent(this@Activity_splashscreen, MainActivity::class.java)
                 startActivity(intent)*/
                splashViewModel.user.observe(this@Activity_splashscreen, Observer { user->
                    if (user != null){
                        val intent = Intent(this@Activity_splashscreen, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@Activity_splashscreen, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    finish()
                })

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
    }
}