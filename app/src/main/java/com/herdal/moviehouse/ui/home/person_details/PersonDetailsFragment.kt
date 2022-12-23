package com.herdal.moviehouse.ui.home.person_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.common.downloadImage
import com.herdal.moviehouse.common.getPlaceHolder
import com.herdal.moviehouse.databinding.FragmentPersonDetailsBinding
import com.herdal.moviehouse.domain.uimodel.person.PersonDetailUiModel
import com.herdal.moviehouse.ui.home.person_details.adapter.AlsoKnownAsAdapter
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonDetailsFragment : Fragment() {

    private var _binding: FragmentPersonDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val navigationArgs: PersonDetailsFragmentArgs by navArgs()

    private fun getArgs() = navigationArgs.personId

    private val viewModel: PersonDetailsViewModel by viewModels()

    private val alsoKnownAsAdapter: AlsoKnownAsAdapter by lazy {
        AlsoKnownAsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        collectPersonDetailRequest()
        setupRecyclerViews()
        return view
    }

    private fun setupRecyclerViews() = binding.apply {
        rvAlsoKnownAs.adapter = alsoKnownAsAdapter
    }

    private fun collectPersonDetailRequest() = binding.apply {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getPersonDetails(getArgs())
                viewModel.personDetail.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            pbPersonDetail.show()
                            tvPersonDetailError.hide()
                        }
                        is Resource.Success -> {
                            resource.data.let { personDetail ->
                                setupUI(personDetail)
                                alsoKnownAsAdapter.submitList(personDetail?.also_known_as)
                            }
                            tvPersonDetailError.hide()
                            pbPersonDetail.hide()

                        }
                        is Resource.Error -> {
                            pbPersonDetail.hide()
                            tvPersonDetailError.show()
                        }
                    }
                }
            }
        }
    }

    private fun setupUI(personDetail: PersonDetailUiModel?) = binding.apply {
        personDetail?.let {
            tvPersonDetailsName.text = personDetail.name
            tvBiography.text = personDetail.biography
            ivPerson.downloadImage(
                personDetail.profile_path,
                getPlaceHolder(requireContext())
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}