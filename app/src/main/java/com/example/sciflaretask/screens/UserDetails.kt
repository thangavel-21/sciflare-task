package com.example.sciflaretask.screens

import com.example.sciflaretask.adapter.UserAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sciflaretask.BaseApplication.Companion.roomDb
import com.example.sciflaretask.database.UserDatabase
import com.example.sciflaretask.database.UserEntityData
import com.example.sciflaretask.database.toUserDetails
import com.example.sciflaretask.databinding.UserDetailsBinding
import com.example.sciflaretask.dto.UserDetails
import com.example.sciflaretask.utills.HelperFunction
import com.example.sciflaretask.viewmodel.UserDetailsViewModel
import com.terareum.exchange.utills.NetworkUtil
import kotlinx.coroutines.launch

class UserDetails : Fragment() {
    private lateinit var binding: UserDetailsBinding
    private val viewModel: UserDetailsViewModel by viewModels()
    private var userAdapter: UserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (NetworkUtil.networkConnectivityStatus(requireContext())) {
            true -> viewModel.getUserDetails()
            else -> {
                roomDb.userDao().getAllUser().observe(viewLifecycleOwner) { value ->
                    value.forEach {
                        viewModel.roomUserList.add(it.toUserDetails())
                    }
                    userAdapter?.updateData(viewModel.roomUserList) ?: run {
                        loadAdapter(viewModel.roomUserList)
                    }
                }
            }
        }
        viewModel.apply {
            error.observe(viewLifecycleOwner) { error ->
                HelperFunction.showToast(requireContext(), error)
            }
            userDetails.observe(viewLifecycleOwner) { value ->
                lifecycleScope.launch {
                    roomDb.userDao().deleteAll()
                    value.forEach { data ->
                        roomDb.userDao().insert(
                            UserEntityData(
                                0L,
                                data.name ?: "",
                                data.email ?: "",
                                data.gender ?: "",
                                data.mobile ?: ""
                            )
                        )
                    }

                }
                userAdapter?.updateData(value) ?: run {
                    loadAdapter(viewModel.userDetails.value)
                }
            }
            isLoading.observe(viewLifecycleOwner) { loading ->
                binding.loader.root.visibility = if (loading) View.VISIBLE else View.GONE
            }
        }

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = userAdapter
        }
    }

    private fun loadAdapter(value: List<UserDetails>?) {
        userAdapter = value?.let { UserAdapter(it) }
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = userAdapter
        }
    }
}