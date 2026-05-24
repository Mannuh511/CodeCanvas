package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.BuildConfig
import com.example.data.AppRepository
import com.example.data.Project
import com.example.data.Review
import com.example.data.User
import com.example.network.Content
import com.example.network.GenerateContentRequest
import com.example.network.Part
import com.example.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AppRepository) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    val allProjects = repository.allProjects.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val allReviews = repository.allReviews.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Chat State for AI Assistant
    private val _chatHistory = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatHistory: StateFlow<List<ChatMessage>> = _chatHistory
    
    private val _isChatLoading = MutableStateFlow(false)
    val isChatLoading: StateFlow<Boolean> = _isChatLoading

    fun login(email: String, pin: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            var user = repository.login(email, pin)
            if (user == null && repository.getUser(email) == null) {
                // Auto register for demo
                user = User(email = email, name = email.substringBefore("@"), pin = pin)
                repository.insertUser(user)
            }
            _currentUser.value = user
            onResult(user != null)
        }
    }

    fun logout() {
        _currentUser.value = null
    }

    fun saveProject(title: String, content: String, language: String) {
        val userEmail = _currentUser.value?.email ?: return
        viewModelScope.launch {
            repository.insertProject(
                Project(title = title, content = content, language = language, ownerEmail = userEmail)
            )
        }
    }

    fun submitReview(content: String, rating: Int) {
        val userEmail = _currentUser.value?.email ?: "Anonymous"
        viewModelScope.launch {
            repository.insertReview(
                Review(userEmail = userEmail, content = content, rating = rating)
            )
        }
    }

    fun sendChatMessage(message: String) {
        val newChat = _chatHistory.value + ChatMessage(role = "user", text = message)
        _chatHistory.value = newChat
        _isChatLoading.value = true

        viewModelScope.launch {
            try {
                val apiKey = BuildConfig.GEMINI_API_KEY
                
                // Build history for API
                val contents = newChat.map { msg ->
                    Content(parts = listOf(Part(text = msg.text)))
                }
                
                val req = GenerateContentRequest(
                    contents = contents,
                    systemInstruction = Content(listOf(Part("You are a professional AI programming assistant in a prestigious IDE app named CodeCanvas. Keep responses expert-level, actionable, and formatted well with markdown.")))
                )
                val response = RetrofitClient.service.generateContent(apiKey, req)
                val botText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "I am encountering an issue generating a response."
                
                _chatHistory.value = _chatHistory.value + ChatMessage(role = "model", text = botText)
            } catch (e: Exception) {
                _chatHistory.value = _chatHistory.value + ChatMessage(role = "model", text = "Error: ${e.localizedMessage}")
            } finally {
                _isChatLoading.value = false
            }
        }
    }
    
    fun clearChat() {
        _chatHistory.value = emptyList()
    }
}

data class ChatMessage(val role: String, val text: String)

class MainViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
