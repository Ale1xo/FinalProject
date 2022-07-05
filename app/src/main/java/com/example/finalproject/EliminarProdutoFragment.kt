package com.example.finalproject

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentEliminarProdutoBinding
import com.google.android.material.snackbar.Snackbar

class EliminarProdutoFragment : Fragment() {
    private var _binding: FragmentEliminarProdutoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var produtos: Produtos
    private lateinit var loja: Loja
    private lateinit var marca: Marcas



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarProdutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        produtos = EliminarProdutoFragmentArgs.fromBundle(arguments!!).produto

        binding.textViewNomeEliminar.text = produtos.name
        binding.textViewLojaEliminar.text = loja.name
        binding.textViewMarcaEliminar.text = marca.name
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaLivro()
                true
            }
            R.id.action_cancelar -> {
                voltaListaProdutos()
                true
            }
            else -> false
        }

    private fun eliminaLivro() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle("Eliminar Produto")
            setMessage("Tem a certeza que pretende eliminar Produto")
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarLivro() })
            show()
        }
    }

    private fun confirmaEliminarLivro() {
        val enderecoproduto = Uri.withAppendedPath(ContentProviderProdutos.ENDERECO_PRODUTO, "${produtos.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoproduto, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewNomeEliminar,
                "Erro ao elminar Produto",
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), "Produto Eliminado com Sucesso", Toast.LENGTH_LONG).show()
        voltaListaProdutos()
    }

    private fun voltaListaProdutos() {
        val acao = EliminarProdutoFragmentDirections.actionEliminarProdutosFragmentToListaProdutosFragment()
        findNavController().navigate(acao)
    }
}