package com.example.sciflaretask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sciflaretask.dto.UserDetails
import com.example.sciflaretask.network.APIRepository
import kotlinx.coroutines.launch

class UserDetailsViewModel(private var repository: APIRepository = APIRepository()) : ViewModel() {
    val userDetails = MutableLiveData<List<UserDetails>>()
    val error = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    var roomUserList = arrayListOf<UserDetails>()

    fun getUserDetails() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                userDetails.value = repository.getUser()
            } catch (exception: Exception) {
                error.value = exception.message
            } finally {
                isLoading.value = false
            }
        }
    }
}