package aaronfortuno.ioc.muuvis

import aaronfortuno.ioc.muuvis.data.auth.AuthManager
import aaronfortuno.ioc.muuvis.ui.theme.MuuvisTheme
import aaronfortuno.ioc.muuvis.ui.theme.aclonica
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuuvisTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()
                }
            }
        }
    }
}

val authManager = AuthManager()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login() {
    // val imageResource = painterResource(id = R.drawable.film1_davinci)

    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(R.drawable.film2_picasso),
            contentScale = ContentScale.Crop,
            alpha = 0.5f,
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply { setToSaturation(0.2f) }
            )
        )
    ) {
        if (!isLandscape) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(Color.Black.copy(alpha = 0.4F)),
                    contentAlignment = Alignment.Center
                ) {
                    MuuvisTitle()
                }
                Spacer(modifier = Modifier.height(32.dp))
                LoginForm(email, setEmail, password, setPassword, context)
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .background(Color.Black.copy(alpha = 0.4F)),
                    contentAlignment = Alignment.Center
                ) {
                    MuuvisTitle()
                }
                LoginForm(email, setEmail, password, setPassword, context)
            }
        }
    }
}

@Composable
private fun MuuvisTitle() {
    Text(
        text = "muuvis",
        fontFamily = aclonica,
        fontWeight = FontWeight.Bold,
        fontSize = 80.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .padding(top = 40.dp, bottom = 40.dp)
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginForm(
    email: String,
    setEmail: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    context: Context
) {
    Column {
        TextField(
            value = email,
            onValueChange = { setEmail(it) },
            label = { Text("your email") },
            singleLine = true,
            textStyle = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .alpha(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { setPassword(it) },
            label = { Text("your password") },
            singleLine = true,
            textStyle = MaterialTheme.typography.headlineLarge,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .alpha(0.8f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authManager.login(email = email, password = password) { success ->
                    if (success) {
                        val intent = Intent(
                            context, MainActivity::class.java
                        )
                        context.startActivity(intent)
                        (context as Activity).finish()
                    } else {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(50.dp)
                .alpha(0.9f)
        ) {
            Text(text = "login")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                authManager.register(email = email, password = password) { success ->
                    if (success) {
                        Toast.makeText(
                            context,
                            "Successful registration! Now you can login!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(50.dp)
                .alpha(0.9f)
        ) {
            Text(text = "register")
        }
    }
}