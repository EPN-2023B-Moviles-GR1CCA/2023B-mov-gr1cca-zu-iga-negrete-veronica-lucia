package com.example.deber3_inkiit_rview

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bookRecyclerView: RecyclerView
    private lateinit var recommendationRecyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    private lateinit var recommendationAdapter: RecommendationAdapter
    private lateinit var bookList: ArrayList<book>
    private lateinit var recommendationList: ArrayList<recommendation>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        // Inicializar la lista de libros
        bookList = ArrayList<book>().apply {
            add(book("Relatos Paranormales", "Relatos Paranormales", "35 relatos paranormales inspirados en la vida real, que narran distintas experiencias sobrenaturales que van desde encuentros con espíritus, hasta pactos de sangre.", "Completa", 4.5, R.drawable.ik1))
            add(book("Secretos ocultos", "Jimena Paez", "Dos hermanas idénticas fueron abandonadas después de nacer creciendo en un orfanato. Ambas son muy parecidas físicamente pero muy diferentes en su interior. ", "Completa", 5.0, R.drawable.ik2))
            add(book("La Leyenda de Licaón", " TLillo", "Samuel es un niño de cinco años al que la vida no ha tratado bien. Su madre, lo único que tenía en el mundo, se ha ido frente a sus ojos de una forma violenta.", "Completa", 4.2, R.drawable.ik3))
            add(book("No te vayas", " Nakary", "El amor verdadero es el sentimiento de afecto, pasión, intimidad y compromiso genuino que una persona siente por otra. En mi opinión eso es falso.. Encontrar el amor es un agradable accidente. ", "En curso", 4.2, R.drawable.ik4))
            add(book("Creepypastas","Im Ingrid", "Adéntrate en las profundidades perturbadoras de la mente humana con este libro inspirado en los relatos de terror conocidos como 'Creepypastas'.", "En proceso",4.8,R.drawable.ik5))
            add(book("Algo Ocultan","Neymichelle26","En un pueblo llamado \"Los santos\" se encuentran cuatro chicos misteriosos cada uno tiene un don.Todos en ese pueblo les temen menos Sarah Wenham ella sabe que Algo ocultan y quiere descubrirlo.","",4.9,R.drawable.ik6))
            add(book("Intersección ","Luna","¿Qué estás dispuesto a hacer para proteger a los tuyos? ¿Estarías dispuesto a llenar tus manos de sangre? ¿Dejarías la moral de lo que está «bien»? ","",4.1,R.drawable.ik7))
            add(book("EL PILOTO DE LA NACIÓN DEL OESTE","Karol Erskin","Era un niño cuando su padre murió, hoy tiene que competir con otros jóvenes para ser seleccionado el piloto de su nación. En la base militar reconocerá lo absoluto del miedo, del odio, y del amor. ","Completa",5.0,R.drawable.ik8))
            add(book("HASTA EL INFINITO","DotzeTaronjes","Adit es un chico que siempre soñó con ser un pirata. Un día, su tío le dio la oportunidad de salir al espacio exterior con un barco de piratas para emprender su primera aventura. ","En curso",4.5,R.drawable.ik9))
            add(book("Dos en Próxima","Benjamín Cochia","Destino o determinación, ¿cuál dicta el resultado? Adam debe decir adiós al mundo tal y como lo conocía. Su activa vida social y su espíritu desenfadado se ven interrumpidos por la llegada de su desconocido hermano gemelo","En curso",4.2,R.drawable.ik10))

            // Agrega más libros según sea necesario
        }

        // Configurar el RecyclerView para libros
        bookRecyclerView = findViewById(R.id.recyclerViewBooks)
        bookAdapter = BookAdapter(bookList)
        bookRecyclerView.layoutManager = LinearLayoutManager(this)
        bookRecyclerView.adapter = bookAdapter


    }


}
