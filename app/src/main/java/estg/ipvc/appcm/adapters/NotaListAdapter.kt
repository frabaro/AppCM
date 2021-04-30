package estg.ipvc.appcm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import estg.ipvc.appcm.R
import estg.ipvc.appcm.entities.Nota

class NotaListAdapter : ListAdapter<Nota, NotaListAdapter.NotaViewHolder>(NotasComparator()) {

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val notaItemView: TextView = itemView.findViewById(R.id.tvTitulo)
        val notaItemTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val notaItemDescricao: TextView = itemView.findViewById(R.id.tvDescricao)

        fun bind(text: String?) {
            //notaItemView.text = text
            notaItemTitulo.text = text
            notaItemDescricao.text = text
        }

        companion object {
            fun create(parent: ViewGroup): NotaViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return NotaViewHolder(view)
            }
        }

        init{

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        return NotaViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val current = getItem(position)
        //holder.notaItemView.text = current.titulo + " - " + current.descricao
        holder.notaItemTitulo.text = current.titulo
        holder.notaItemDescricao.text = current.descricao

    }

    class NotasComparator : DiffUtil.ItemCallback<Nota>() {
        override fun areItemsTheSame(oldItem: Nota, newItem: Nota): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Nota, newItem: Nota): Boolean {
            return oldItem.titulo == newItem.titulo
        }
    }

}
