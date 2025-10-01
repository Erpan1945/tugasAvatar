package com.example.tugasavatar

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

@Composable
fun AvatarPage() {
    // 1. Membuat state untuk setiap bagian wajah.
    //    'remember' digunakan agar state tidak di-reset saat recomposition.
    //    'mutableStateOf(true)' berarti semua bagian wajah terlihat secara default.
    var showBrow by remember { mutableStateOf(true) }
    var showEyes by remember { mutableStateOf(true) }
    var showNose by remember { mutableStateOf(true) }
    var showMouth by remember { mutableStateOf(true) }

    // Column digunakan untuk menata elemen secara vertikal (Avatar di atas, Checkbox di bawah).
    Column(
        modifier = Modifier
            .fillMaxSize() // Mengisi seluruh layar
            .padding(16.dp), // Memberi sedikit jarak dari tepi layar
        horizontalAlignment = Alignment.CenterHorizontally, // Menengahkan semua elemen
        verticalArrangement = Arrangement.Center // Menempatkan konten di tengah layar secara vertikal
    ) {
        // 2. Box untuk menumpuk gambar-gambar avatar.
        Box(
            modifier = Modifier.size(300.dp), // Atur ukuran container avatar
            contentAlignment = Alignment.Center // Menengahkan semua gambar di dalam Box
        ) {
            // Gambar dasar wajah (selalu terlihat)
            // Wajah dasar
            Image(
                painter = painterResource(id = R.drawable.face_0004),
                contentDescription = "Face Base",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )

            if (showBrow) {
                Image(
                    painter = painterResource(id = R.drawable.face_0001),
                    contentDescription = "Eyebrows",
                    modifier = Modifier.size(120.dp).offset(y = (-25).dp)
                )
            }

            if (showEyes) {
                Image(
                    painter = painterResource(id = R.drawable.face_0003),
                    contentDescription = "Eyes",
                    modifier = Modifier.size(110.dp)
                )
            }

            if (showNose) {
                Image(
                    painter = painterResource(id = R.drawable.face_0002),
                    contentDescription = "Nose",
                    modifier = Modifier.size(32.dp).offset(y = 30.dp)
                )
            }

            if (showMouth) {
                Image(
                    painter = painterResource(id = R.drawable.face_0000),
                    contentDescription = "Mouth",
                    modifier = Modifier.size(50.dp).offset(y = 60.dp)
                )
            }
        }

        // Memberi jarak vertikal antara avatar dan checkbox
        Spacer(modifier = Modifier.height(32.dp))

        // 3. Row untuk menata Checkbox secara horizontal.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly // Memberi jarak yang sama antar checkbox
        ) {
            // Checkbox untuk Alis
            CheckboxWithLabel(
                label = "Brow",
                checked = showBrow,
                onCheckedChange = { showBrow = it }
            )

            // Checkbox untuk Mata
            CheckboxWithLabel(
                label = "Eye",
                checked = showEyes,
                onCheckedChange = { showEyes = it }
            )

            // Checkbox untuk Hidung
            CheckboxWithLabel(
                label = "Nose",
                checked = showNose,
                onCheckedChange = { showNose = it }
            )

            // Checkbox untuk Mulut
            CheckboxWithLabel(
                label = "Mouth",
                checked = showMouth,
                onCheckedChange = { showMouth = it }
            )
        }
    }
}

/**
 * Composable tambahan untuk menggabungkan Checkbox dengan Text (Label).
 * Ini membantu menghindari pengulangan kode.
 */
@Composable
fun CheckboxWithLabel(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(text = label)
    }
}

// 4. Fungsi Preview untuk melihat hasilnya di Android Studio tanpa menjalankan aplikasi.
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // Pastikan Anda memiliki tema yang terdefinisi, atau gunakan Surface sederhana.
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AvatarPage()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Panggil fungsi composable utama Anda di sini
            AvatarPage()
        }
    }
}