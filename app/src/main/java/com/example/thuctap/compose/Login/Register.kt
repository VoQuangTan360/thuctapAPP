package com.example.thuctap.compose.Login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thuctap.ui.theme.Blue300
import com.example.thuctap.ui.theme.SecondaryColor
import com.example.thuctap.ui.theme.ThucTapTheme
import com.example.thuctap.ui.theme.white
import com.example.thuctap.view_model.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@Composable
fun RegisterActivity() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SecondaryColor)
            //contentAlignment = Alignment.TopCenter
        )
        view()
    }
}
@Composable
fun view(
            registerViewModel: SignUpViewModel = hiltViewModel()
    ){


    val selectedValue = remember { mutableStateOf("") }
    val nameValue = remember { mutableStateOf("") }
    val usernameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val IDloading = remember { mutableStateOf(false) }

    val checkloading = remember { mutableStateOf(false) }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    val items = listOf("Student ", "Teacher")

    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }
//    val scope = CoroutineScope(Dispatchers.IO + Job())
    val scope= rememberCoroutineScope()
    val state= registerViewModel.signUpState.collectAsState(initial = null)
    val context = LocalContext.current
    if(IDloading.value){
//        CircularIndeterminateProgressBar(isDisplayed = true, verticalBias = 0.3f)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(0.90f)
            .clip(shape = RoundedCornerShape(topStart=30.dp, topEnd=30.dp))
            .background(Color.White)
            .padding(8.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",style = MaterialTheme.typography.h1,

                )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = nameValue.value,
                    onValueChange = { nameValue.value = it },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = usernameValue.value,
                    onValueChange = { usernameValue.value = it },
                    label = { Text(text = "User name") },
                    placeholder = { Text(text = "User name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    trailingIcon = {
                        IconButton(onClick = {

                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(imageVector = if(confirmPasswordVisibility.value) Icons.Default.Lock else Icons.Default.Lock,
                                contentDescription ="Password Toggle" )
                        }
                    },
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = confirmPasswordValue.value,
                    onValueChange = { confirmPasswordValue.value = it
                    },
                    label = { Text(text = "Confirm Password") },
                    placeholder = { Text(text = "Confirm Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),

                    trailingIcon = {
                        IconButton(onClick = {
                            confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                        }) {
                            Icon(imageVector = if(confirmPasswordVisibility.value) Icons.Default.Lock else Icons.Default.Lock,
                                contentDescription ="Password Toggle" )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Button(onClick = {
                    IDloading.value=true
                    val texterror= valiPassword( passwordValue.value)
                    val gmailerror= valiGmail(emailValue.value)

                    scope.launch {
                        registerViewModel.RegisterUser(emailValue.value, passwordValue.value)
                    }


//                    if(gmailerror!= null){
//                        Toast.makeText(context, gmailerror, Toast.LENGTH_SHORT).show()
//                    }else{
//                        if(texterror != null){
//                            Toast.makeText(context, texterror, Toast.LENGTH_SHORT).show()
//                        }
//                        else{
//                            if( passwordValue.value != (confirmPasswordValue.value)){
//                                Toast.makeText(context, "confirm PasswordValue error", Toast.LENGTH_SHORT).show()
//                            }
//                            else{
//
//                            }
//                            }
//
//                    }
                },

                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)) {
                    Text(text="Sign Up",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "Login Instead",
                    modifier = Modifier.clickable(onClick = {
//                            navController.navigate("login_page"){
//                                popUpTo = navController.graph.startDestination
//                                launchSingleTop = true
//                            }
                    })
                )
                if(nameValue.value.isNotEmpty()){
                    Spacer(modifier = Modifier.padding(150.dp))
                }else{
                    Spacer(modifier = Modifier.padding(20.dp))
                }



            }

        }

    }
    var checkSigIn by remember{ mutableStateOf(false) }
    if (checkSigIn){
        AlertDialogStatus()
    }
    LaunchedEffect(key1 = state.value?.isSuccess){
        scope.launch {
            if(state.value?.isSuccess?.isNotEmpty()==true){
                val success=state.value?.isSuccess
                checkSigIn=true
                Toast.makeText(context,"${success}",Toast.LENGTH_SHORT).show()
            }
        }
    }

}
@Composable
fun AlertDialogStatus(){
    /**open the Dialog box*/
    val openDialog = remember{ mutableStateOf(true) }


    /**set Dialog */
    if (openDialog.value){
        AlertDialog(modifier = Modifier.fillMaxWidth()
            .padding(15.dp)
            ,
            onDismissRequest = { openDialog.value = true },
            title = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(25.dp)),
                    Arrangement.Center
                ){
                    Text(
                        text = "thông báo",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            },
            text = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(25.dp)),
                    Arrangement.Center
                ){
                    Text(
                        text = "thành công",
                        style = TextStyle(
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            },
            confirmButton = {
                Row(Modifier
                    .fillMaxWidth(),
                    Arrangement.Center)
                {
                    TextButton(modifier = Modifier
                        .padding(
                            start = 5.dp,
                            end = 5.dp,
                            top = 5.dp,
                            bottom = 5.dp
                        )
                        .fillMaxWidth(0.8f)
                        .background(
                            color = Blue300,
                            shape = RoundedCornerShape(10.dp)
                        )
                        ,onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text(text = "OK",
                            color = Color.White
                        )
                    }
                }

            },

            backgroundColor = white,
            contentColor = Blue300,
            shape = RoundedCornerShape(25.dp)

        )
    }

}
private fun valiPassword(pass: String): String? {
    if(pass.length < 8){
        return "Minimum 8 character"
    }
    if(!pass.matches(".*[A-Z].*".toRegex())){
        return "Must contain 1 upper-case character"
    }
    if(!pass.matches(".*[a-].*".toRegex())){
        return "Must contain 1 lower-case character"
    }
    if(!pass.matches(".*[@#$%^*&+=].*".toRegex())){
        return "Must contain 1 special character (@#\$%^*&+=)"
    }
    return null
}
private fun valiGmail(gmail: String):String?{
    if(gmail.equals("@gmail.com")){
        return "Gmail erro"
    }
    return null
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ThucTapTheme {
//        view()
//    }
//}