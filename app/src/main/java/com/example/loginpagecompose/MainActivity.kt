package com.example.loginpagecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpagecompose.ui.theme.LoginPageComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginPage()
                }
            }
        }
    }
}

@Composable
fun LoginPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadText()
        LoginBox()
        AlreadyHaveAnAccount()
    }
}

@Composable
fun AlreadyHaveAnAccount() {
    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            )
        ) {
            Text(
                text = "Already have an account?",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun HeadText() {
    Text(
        text = "Sing Up for an account",
        fontSize = 25.sp,
        modifier = Modifier.padding(top = 20.dp, bottom = 30.dp),
        fontWeight = FontWeight(600),
        color = Color.Black,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBox() {
    var email by remember { mutableStateOf(TextFieldValue())}
    var password by remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier
            .padding(15.dp)
            .shadow(elevation = 10.dp)
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(

                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(text = "Email Address")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Email TextField icon"
                        )
                    }
                )
                val revealPassword: MutableState<Boolean> = remember {
                    mutableStateOf(false)
                }
                TextField(
                    value = password,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (revealPassword.value) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        if (revealPassword.value) {
                            IconButton(
                                onClick = {
                                    revealPassword.value = false
                                },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_show_pasword),
                                    contentDescription = null
                                )
                            }
                        } else {
                            IconButton(
                                onClick = {
                                    revealPassword.value = true
                                },
                            ) {

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_show_pasword),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    onValueChange = {
                        password = it
                    },
                    label = {
                        Text(text = "Password")
                    },
                    modifier = Modifier.padding(top = 25.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pasword),
                            contentDescription = "Password TextField icon"
                        )
                    },

                    )
                Box(modifier = Modifier.align(alignment = Alignment.Start)) {
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                    ) {
                        PasswordRequirements(password)
                    }
                }
                Button(
                    shape = RoundedCornerShape(0.dp),
                    enabled = (email.text.isNotEmpty() && password.text.isNotEmpty()),
                    onClick = {},
                    modifier = Modifier
                        .padding(top = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        disabledContainerColor = Color.LightGray,
                        contentColor = Color.White,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Text(text = "Sign Up")
                }
            }
        }
    }
}

@Composable
fun TextWithIcon(
    text: String,
    isActive: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_right_check),
            contentDescription = "", tint = if (isActive) Color.Blue else Color.LightGray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = if (isActive) Color.Blue
            else Color.LightGray
        )
    }
}

@Composable
fun PasswordRequirements(password: TextFieldValue) {
    val regex = Regex("[0-9]+")

    var containsUpper by remember { mutableStateOf(false) }
    var containsDigit by remember { mutableStateOf(false) }
    var isEightLetters by remember { mutableStateOf(false) }

    containsUpper = password.text != password.text.lowercase()

    containsDigit = (regex.containsMatchIn(password.text))

    isEightLetters = (password.text.length >= 8)


    TextWithIcon(
        text = "At least 1 upper-case letter", isActive = containsUpper
    )
    TextWithIcon(
        text = "At least 1 digit", isActive = containsDigit
    )
    TextWithIcon(
        text = "At least 8 characters", isActive = isEightLetters
    )
}