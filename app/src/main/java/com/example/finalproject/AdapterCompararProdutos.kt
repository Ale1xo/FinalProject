package com.example.finalproject

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent

class AdapterCompararProdutos(val fragment: ListaProdutosFragment) : RecyclerView.Adapter<AdapterCompararProdutos.ViewHolderCompararProdutos>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderCompararProdutos? = null

    inner class ViewHolderCompararProdutos(itemProduto: View):RecyclerView.ViewHolder(itemProduto), View.OnClickListener {
        val textViewProduto = itemProduto.findViewById<TextView>(R.id.textViewProduto)
        val textViewLoja = itemProduto.findViewById<TextView>(R.id.textViewLoja)
        val textViewMarca = itemProduto.findViewById<TextView>(R.id.textViewMarca)

        init {
            itemProduto.setOnClickListener(this)
        }

        var produtos: Produtos? = null
            get() = field
            set(value: Produtos?){
                field = value
                textViewProduto.text = produtos?.name?:""
            }
        var loja:Loja? = null
            get() = field
            set(value: Loja?){
                field = value
                textViewLoja.text = loja?.name?:""
            }
        var marca: Marcas? = null
            get() = field
            set(value: Marcas?){
               field = value
               textViewMarca.text = marca?.name?:""
            }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }
        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_blue_light)
            viewHolderSelecionado = this
            fragment.produtoSelecionado = produtos
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

    override fun onBindViewHolder(holder: ViewHolderCompararProdutos, position: Int) {
        cursor!!.moveToPosition(position)
        holder.produtos = Produtos.fromCursor(cursor!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCompararProdutos {
        val itemProduto = fragment.layoutInflater.inflate(R.layout.item_produto, parent, false)
        return ViewHolderCompararProdutos(itemProduto)
    }
}


