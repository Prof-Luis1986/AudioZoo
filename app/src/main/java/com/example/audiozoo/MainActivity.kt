package com.example.audiozoo

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.audiozoo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el objeto de View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Establece el layout de la actividad usando View Binding
        setContentView(binding.root)

        // Inicializa el MediaPlayer (no necesitas crearlo con un sonido específico aquí)
        mediaPlayer = MediaPlayer()

        // Asigna la acción al hacer clic en el botón de tigre
        binding.btntigre.setOnClickListener {
            togglePlayback(R.raw.sonido_tigre)
        }

        // Asigna la acción al hacer clic en el botón de león
        binding.btnLeon.setOnClickListener {
            togglePlayback(R.raw.sonido_leon)
        }

        // Asigna la acción al hacer clic en el botón de jirafa
        binding.btnJirafa.setOnClickListener {
            togglePlayback(R.raw.sonido_jirafa)
        }

        // Asigna la acción al hacer clic en el botón de hiena
        binding.btnHiena.setOnClickListener {
            togglePlayback(R.raw.sonido_hiena)
        }
    }

    private fun togglePlayback(soundResource: Int) {
        if (mediaPlayer.isPlaying) {
            // Si está reproduciendo, detén la reproducción
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        } else {
            // Si no está reproduciendo, carga el nuevo sonido y inicia la reproducción
            mediaPlayer.apply {
                reset()
                setDataSource(resources.openRawResourceFd(soundResource))
                prepare()
                start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Libera los recursos del MediaPlayer al destruir la actividad
        mediaPlayer.release()
    }
}
