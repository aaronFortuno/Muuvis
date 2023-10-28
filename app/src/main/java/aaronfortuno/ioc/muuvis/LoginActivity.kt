package aaronfortuno.ioc.muuvis

import aaronfortuno.ioc.muuvis.data.auth.AuthManager
import aaronfortuno.ioc.muuvis.ui.theme.MuuvisTheme
import aaronfortuno.ioc.muuvis.ui.theme.aclonica
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
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
    val imageResource = painterResource(id = R.drawable.film1_davinci)

    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "muuvis",
            fontFamily = aclonica,
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp, bottom = 40.dp)
        )

        Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .shadow(3.dp)
                ) {
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxSize()
            ) {
                Image(
                    painter = imageResource,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()
                        .alpha(0.3f)
                )
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                ) {
                    TextField(
                        value = email,
                        onValueChange = { setEmail(it)},
                        label = { Text("your email") },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.8f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        onValueChange = { setPassword(it) },
                        label = { Text( "your password") },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.headlineLarge,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.8f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {

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
                    .weight(1f)
                    .height(50.dp)
                    .alpha(0.9f)
            ) {
                Text(text = "login")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    authManager.register(email = email, password = password) { success ->
                        if (success) {
                            Toast.makeText(context, "Successful registration! Now you can login!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .alpha(0.9f)
            ) {
                Text(text = "register")
            }
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginActivityPreview() {
    MuuvisTheme {
        Login()
    }
}