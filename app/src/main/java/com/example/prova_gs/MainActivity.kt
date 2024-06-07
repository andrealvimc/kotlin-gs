package com.example.prova_gs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prova_gs.ui.theme.PROVAGSTheme

class MainActivity: AppCompatActivity() {

    private lateinit var etNomePraia: EditText
    private lateinit var etCidade: EditText
    private lateinit var etEstado: EditText
    private lateinit var btnIncluir: Button
    private lateinit var recyclerViewPraias: RecyclerView
    private lateinit var praiaAdapter: PraiaAdapter
    private val praias = mutableListOf<Praia>()
    private lateinit var btnIntegrantes: Button


    @Override()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNomePraia = findViewById(R.id.etNomePraia)
        etCidade = findViewById(R.id.etCidade)
        etEstado = findViewById(R.id.etEstado)
        btnIncluir = findViewById(R.id.btnIncluir)
        recyclerViewPraias = findViewById(R.id.recyclerViewPraias)


        praiaAdapter = PraiaAdapter(praias) { position ->
            praias.removeAt(position)
            praiaAdapter.notifyItemRemoved(position)
        }

        recyclerViewPraias.layoutManager = LinearLayoutManager(this)
        recyclerViewPraias.adapter = praiaAdapter

        btnIncluir.setOnClickListener {
            val nome = etNomePraia.text.toString()
            val cidade = etCidade.text.toString()
            val estado = etEstado.text.toString()

            if (nome.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nome.length < 3 || cidade.length < 3 || estado.length < 2) {
                Toast.makeText(this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val praia = Praia(nome, cidade, estado)
            praias.add(praia)
            praiaAdapter.notifyItemInserted(praias.size - 1)

            etNomePraia.text.clear()
            etCidade.text.clear()
            etEstado.text.clear()



            btnIntegrantes = findViewById(R.id.btnIntegrantes)
            btnIntegrantes.setOnClickListener {
                val intent = Intent(this, IntegrantesActivity::class.java) // Assuming IntegrantesActivity exists
                startActivity(intent)
            }
        }
    }
}