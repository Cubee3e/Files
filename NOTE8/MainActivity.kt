package com.example.irblaster

import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var consumerIrManager: ConsumerIrManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация ConsumerIrManager для работы с IR
        consumerIrManager = getSystemService(CONSUMER_IR_SERVICE) as ConsumerIrManager

        val buttonSendIrSignal = findViewById<Button>(R.id.buttonSendIrSignal)

        // Проверяем, поддерживает ли устройство IR
        if (!consumerIrManager.hasIrEmitter()) {
            Toast.makeText(this, "IR не поддерживается этим устройством", Toast.LENGTH_SHORT).show()
        }

        // Обработчик нажатия кнопки для передачи IR сигнала
        buttonSendIrSignal.setOnClickListener {
            sendIrSignal()
        }
    }

    private fun sendIrSignal() {
        // Пример передачи сигнала: частота и паттерн импульсов для телевизора
        val frequency = 38000 // Частота передачи IR
        val pattern = intArrayOf(1000, 1000, 500, 500) // Паттерн импульсов IR

        // Отправка сигнала
        consumerIrManager.transmit(frequency, pattern)

        Toast.makeText(this, "IR сигнал отправлен!", Toast.LENGTH_SHORT).show()
    }
}
