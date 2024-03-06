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
            add(book("universo_visual", "2min", "Sumérgete en un universo visual donde cada imagen cuenta una historia. Desde paisajes impresionantes hasta retratos conmovedores, este es un espacio para apreciar la belleza y la creatividad en todas sus formas.", "2 replies  64 likes", 4.5, R.drawable.i1))
            add(book("mentes_inspiradas", "15min", "Inspirando cada día. Compartimos pensamientos, ideas y motivación para alimentar tu creatividad", "2 replies  87 likes", 5.0, R.drawable.i2))
            add(book("expresion_artesanal", " 19min", "En cada hebra, una historia. La artesanía es el alma de la creatividad. #Artesanía #HechoAMano #ExpresiónArtesanal", "2 replies  64 likes", 4.2, R.drawable.i3))
            add(book("visiones_alternativas", " 25min", "Desde arte experimental hasta ideas innovadoras, aquí encontrarás un espacio para la creatividad sin límites. Únete a nuestra comunidad de pensadores y creadores. #Innovación ", "3 replies  7 likes", 4.2, R.drawable.i4))
            add(book("estilo_eclectico","30min", "Donde los estilos se encuentran y se mezclan. Celebramos la diversidad en todas sus formas, uniendo diferentes estilos y tendencias en una expresión única.", "22 replies  64 likes",4.8,R.drawable.i5))
            add(book("arteypensamiento","2h","Exploramos la intersección entre el arte y el pensamiento. Aquí encontrarás reflexiones profundas, análisis críticos y una amplia gama de expresiones artísticas. Únete a nuestra comunidad de pensadores y amantes del arte. #Arte","",4.9,R.drawable.i6))
            add(book("imaginario_infinito ","17h","En este espacio, exploramos el infinito poder de la imaginación. Desde fantasía hasta ciencia ficción, cada publicación es una ventana a un universo único ","",4.1,R.drawable.i7))
            add(book("creatividad_en_accion","19h","Cuando el arte y el pensamiento se encuentran, nacen nuevas formas de ver el mundo.  ","2 replies  50 likes",5.0,R.drawable.i8))
            add(book("inspiracion_creativa","23h", "La belleza está en la diversidad. En la mezcla de estilos, encontramos la armonía.","12 replies 3 likes",4.5,R.drawable.i9))
            add(book("DosPies","1d","Explorando el infinito poder de la imaginación. En cada imagen, un universo por descubrir...","1 replies  1 likes",4.2,R.drawable.i10))

            // Agrega más libros según sea necesario
        }

        // Configurar el RecyclerView para libros
        bookRecyclerView = findViewById(R.id.recyclerViewBooks)
        bookAdapter = BookAdapter(bookList)
        bookRecyclerView.layoutManager = LinearLayoutManager(this)
        bookRecyclerView.adapter = bookAdapter


    }


}
