package com.ggcs.hellowidgets

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var helloButton: Button
    private lateinit var nameInputField: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    // Find and bind UI elements
    private fun initializeViews() {
        helloButton = findViewById(R.id.button1)
        nameInputField = findViewById(R.id.inputField1)
    }

    // Attach event listeners
    private fun setupListeners() {
        helloButton.setOnClickListener {
            val name = nameInputField.text.toString().trim()
            if (name.isNotEmpty()) {
                showToast("Hello, $name!")
            } else {
                showToast("Hello from Activity 1")
            }
        }

        nameInputField.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(view)
                view.clearFocus()
                true
            } else {
                false
            }
        }
    }

    // Show a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Hide the soft keyboard
    private fun hideKeyboard(view: android.view.View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
