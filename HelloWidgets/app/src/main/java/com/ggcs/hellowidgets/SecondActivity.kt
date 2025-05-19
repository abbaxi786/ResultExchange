package com.ggcs.hellowidgets

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SecondActivity : AppCompatActivity() {

    private lateinit var toastButton: Button
    private lateinit var userInputField: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        configureStatusBar()
        initializeViews()
        setupListeners()
    }

    // Set white status bar and dark icons
    private fun configureStatusBar() {
        window.statusBarColor = getColor(android.R.color.white)
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    // Bind UI elements
    private fun initializeViews() {
        toastButton = findViewById(R.id.button2)
        userInputField = findViewById(R.id.inputField2)
    }

    // Attach listeners
    private fun setupListeners() {
        toastButton.setOnClickListener {
            val input = userInputField.text.toString().trim()
            if (input.isNotEmpty()) {
                showToast("Welcome, $input!")
            } else {
                showToast("Hello from Activity 2")
            }
        }

        userInputField.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(view)
                view.clearFocus()
                true
            } else {
                false
            }
        }
    }

    // Display a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Hide the soft keyboard
    private fun hideKeyboard(view: android.view.View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
