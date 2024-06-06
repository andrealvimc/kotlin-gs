package com.example.prova_gs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


data class Praia(val nome: String, val cidade: String, val estado: String)


class PraiaAdapter(private val praias: MutableList<Praia>, private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.Adapter<PraiaAdapter.PraiaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PraiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_praia, parent, false)
        return PraiaViewHolder(view as ViewGroup)
    }

    override fun onBindViewHolder(holder: PraiaViewHolder, position: Int) {
        val praia = praias[position]
        holder.tvPraia.text = "${praia.nome}, ${praia.cidade} - ${praia.estado}"
        holder.btnExcluir.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount() = praias.size

    class PraiaViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val tvPraia: TextView = itemView.findViewById(R.id.tvPraia)
        val btnExcluir: Button = itemView.findViewById(R.id.btnExcluir)
    }
}