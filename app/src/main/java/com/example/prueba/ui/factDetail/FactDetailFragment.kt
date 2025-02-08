package com.example.prueba.ui.factDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prueba.data.model.Facts
import com.example.prueba.databinding.FragmentFactDetailBinding
import com.example.prueba.uitl.Tools


const val ARG_FACT = "fact"

class FactDetailFragment : Fragment() {

    private var _binding: FragmentFactDetailBinding? = null
    private val binding get() = _binding!!

    private var fact: Facts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fact = it.getParcelable(ARG_FACT) as Facts?
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serView()
        setListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun serView() {
        binding.tvId.text = "Id: " + fact?.id
        binding.tvdateInsert.text = "Date Insert: " + fact?.dateInsert
        binding.tvFact.text = "Fact: " + fact?.fact
        binding.tvSlug.text = "Slug: " + fact?.slug
        binding.tvColumns.text = "Columns: " + fact?.columns
        binding.tvOrganization.text = "Organization: " + fact?.organization
        binding.tvResource.text = "Resource: " + fact?.resource
        binding.tvUrl.text = "Url: " + fact?.url
        binding.tvOperations.text = "Operations: " + fact?.operations
        binding.tvDataset.text = "Dataset: " + fact?.dataset
        binding.tvCreatedAt.text = "Created at: " + fact?.createdAt.toString()
    }

    private fun setListeners() {
        binding.btnShare.setOnClickListener {
            Tools.shareMessageViaWhatsApp(requireContext(), getWhatsappMessage())
        }
    }

    private fun getWhatsappMessage(): String =
        StringBuilder().apply {
            append("Id: ${fact?.id}\n")
            append("Date Insert: ${fact?.dateInsert}\n")
            append("Fact: ${fact?.fact}\n")
            append("Slug: ${fact?.slug}\n")
            append("Columns: ${fact?.columns}\n")
            append("Organization: ${fact?.organization}\n")
            append("Resource: ${fact?.resource}\n")
            append("Url: ${fact?.url}\n")
            append("Operations: ${fact?.operations}\n")
            append("Dataset: ${fact?.dataset}\n")
            append("Created at: ${fact?.createdAt}\n")
        }.toString()

}