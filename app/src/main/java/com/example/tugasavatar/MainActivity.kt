package com.example.tugasavatar // <-- Pastikan ini sesuai dengan nama paket Anda

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AvatarPage()
            }
        }
    }
}

@Composable
fun AvatarPage() {
    // State untuk mengontrol visibilitas setiap bagian wajah
    var showBrow by remember { mutableStateOf(true) }
    var showEyes by remember { mutableStateOf(true) }
    var showNose by remember { mutableStateOf(true) }
    var showMouth by remember { mutableStateOf(true) }

    // Mendeteksi konfigurasi layar saat ini (termasuk orientasi)
    val configuration = LocalConfiguration.current

    // Memilih tata letak berdasarkan orientasi layar
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeLayout(
                showBrow = showBrow, onBrowChange = { showBrow = it },
                showEyes = showEyes, onEyesChange = { showEyes = it },
                showNose = showNose, onNoseChange = { showNose = it },
                showMouth = showMouth, onMouthChange = { showMouth = it }
            )
        }
        else -> { // ORIENTATION_PORTRAIT
            PortraitLayout(
                showBrow = showBrow, onBrowChange = { showBrow = it },
                showEyes = showEyes, onEyesChange = { showEyes = it },
                showNose = showNose, onNoseChange = { showNose = it },
                showMouth = showMouth, onMouthChange = { showMouth = it }
            )
        }
    }
}

@Composable
fun PortraitLayout(
    showBrow: Boolean, onBrowChange: (Boolean) -> Unit,
    showEyes: Boolean, onEyesChange: (Boolean) -> Unit,
    showNose: Boolean, onNoseChange: (Boolean) -> Unit,
    showMouth: Boolean, onMouthChange: (Boolean) -> Unit
) {
    // Tata letak vertikal untuk mode potret
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Avatar(
            modifier = Modifier.weight(1f, fill = false),
            showBrow = showBrow,
            showEyes = showEyes,
            showNose = showNose,
            showMouth = showMouth
        )
        Spacer(modifier = Modifier.height(32.dp))
        Controls(
            showBrow = showBrow, onBrowChange = onBrowChange,
            showEyes = showEyes, onEyesChange = onEyesChange,
            showNose = showNose, onNoseChange = onNoseChange,
            showMouth = showMouth, onMouthChange = onMouthChange
        )
    }
}

@Composable
fun LandscapeLayout(
    showBrow: Boolean, onBrowChange: (Boolean) -> Unit,
    showEyes: Boolean, onEyesChange: (Boolean) -> Unit,
    showNose: Boolean, onNoseChange: (Boolean) -> Unit,
    showMouth: Boolean, onMouthChange: (Boolean) -> Unit
) {
    // Tata letak horizontal untuk mode landscape
    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Avatar(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            showBrow = showBrow,
            showEyes = showEyes,
            showNose = showNose,
            showMouth = showMouth
        )
        Spacer(modifier = Modifier.width(32.dp))
        // Controls diletakkan dalam Column di sisi kanan
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Controls(
                showBrow = showBrow, onBrowChange = onBrowChange,
                showEyes = showEyes, onEyesChange = onEyesChange,
                showNose = showNose, onNoseChange = onNoseChange,
                showMouth = showMouth, onMouthChange = onMouthChange,
                useHorizontalLayout = false // Gunakan layout vertikal untuk controls
            )
        }
    }
}

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    showBrow: Boolean,
    showEyes: Boolean,
    showNose: Boolean,
    showMouth: Boolean
) {
    Box(
        modifier = modifier.aspectRatio(1f), // Menjaga rasio aspek avatar
        contentAlignment = Alignment.Center
    ) {
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
                modifier = Modifier
                    .size(150.dp)
                    .offset(y = (-30).dp)
            )
        }
        if (showEyes) {
            Image(
                painter = painterResource(id = R.drawable.face_0003),
                contentDescription = "Eyes",
                modifier = Modifier.size(135.dp)
            )
        }
        if (showNose) {
            Image(
                painter = painterResource(id = R.drawable.face_0002),
                contentDescription = "Nose",
                modifier = Modifier
                    .size(32.dp)
                    .offset(y = 33.dp)
            )
        }
        if (showMouth) {
            Image(
                painter = painterResource(id = R.drawable.face_0000),
                contentDescription = "Mouth",
                modifier = Modifier
                    .size(60.dp)
                    .offset(y = 70.dp)
            )
        }
    }
}

@Composable
fun Controls(
    showBrow: Boolean, onBrowChange: (Boolean) -> Unit,
    showEyes: Boolean, onEyesChange: (Boolean) -> Unit,
    showNose: Boolean, onNoseChange: (Boolean) -> Unit,
    showMouth: Boolean, onMouthChange: (Boolean) -> Unit,
    useHorizontalLayout: Boolean = true // Default horizontal
) {
    val arrangement = if (useHorizontalLayout) Arrangement.SpaceEvenly else Arrangement.spacedBy(16.dp)

    if (useHorizontalLayout) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = arrangement
        ) {
            CheckboxWithLabel("Brow", showBrow, onBrowChange)
            CheckboxWithLabel("Eye", showEyes, onEyesChange)
            CheckboxWithLabel("Nose", showNose, onNoseChange)
            CheckboxWithLabel("Mouth", showMouth, onMouthChange)
        }
    } else {
        Column(
            verticalArrangement = arrangement,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CheckboxWithLabel("Brow", showBrow, onBrowChange)
            CheckboxWithLabel("Eye", showEyes, onEyesChange)
            CheckboxWithLabel("Nose", showNose, onNoseChange)
            CheckboxWithLabel("Mouth", showMouth, onMouthChange)
        }
    }
}

@Composable
fun CheckboxWithLabel(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = label)
    }
}

@Preview(showBackground = true, name = "Portrait Mode")
@Composable
fun DefaultPreview() {
    AvatarPage()
}

@Preview(showBackground = true, name = "Landscape Mode", widthDp = 800, heightDp = 390)
@Composable
fun LandscapePreview() {
    AvatarPage()
}
