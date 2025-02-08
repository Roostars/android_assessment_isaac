package com.example.prueba.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prueba.R
import com.example.prueba.data.model.Facts
import com.example.prueba.data.model.UiState
import com.example.prueba.databinding.FragmentHomeBinding
import com.example.prueba.ui.fatDetail.ARG_FACT
import com.example.prueba.ui.home.adapter.FactsAdapter

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private lateinit var adapter: FactsAdapter
    private var factsList = mutableListOf<Facts>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        initRecyclerView()
        setObserver()
        setListers()
        homeViewModel.loadNextPage()
    }

    private fun initRecyclerView() {
        adapter = FactsAdapter()
        adapter.facts = factsList
        adapter.onClickItem = {
            val bundle = Bundle()
            bundle.putParcelable(ARG_FACT, it)
            navController.navigate(R.id.action_navigation_home_to_factDetailFragment, bundle )
        }
        binding.recyclerFacts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerFacts.adapter = adapter
    }

    private fun setObserver(){
        homeViewModel.facts.observe(viewLifecycleOwner){uiState ->
            when (uiState) {
                is UiState.Error -> {}
                UiState.Loading -> {}
                is UiState.Success -> {
                    factsList.clear()
                    factsList.addAll(uiState.data)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setListers() {
        binding.svFacts.setOnQueryTextListener(this)
        binding.btnNext.setOnClickListener {
            homeViewModel.loadNextPage()
        }
        binding.btnPrevious.setOnClickListener {
            homeViewModel.loadPreviousPage()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            homeViewModel.query = query
            homeViewModel.loadNextPage()
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0.isNullOrEmpty()){
            homeViewModel.getAllFacts()
        }
        return true
    }
}