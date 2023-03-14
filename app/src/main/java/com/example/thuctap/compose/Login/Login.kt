package com.example.thuctap.compose.Login

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.thuctap.R
import com.example.thuctap.ui.theme.ThucTapTheme
import com.example.thuctap.ui.theme.blackBTN
import com.example.thuctap.view_model.SiginViewModel
import com.example.thuctap.view_model.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SigInScreen(navHostController: NavHostController) {

    val context = LocalContext.current
    var username by remember{ mutableStateOf("") }
    var checkStatus by remember{ mutableStateOf(false) }
    var colorSpaceErro by remember{ mutableStateOf(Color.White) }


    var password by remember{ mutableStateOf("") }
    var offset by remember { mutableStateOf(0) }
    val (focusUsername,focusPassword) = remember { FocusRequester.createRefs()}
    val keyboardController =  LocalSoftwareKeyboardController.current
    var isPasswordVisible by remember{ mutableStateOf(false) }


    Scaffold() {
        colorSpaceErro = if(!checkStatus){
            Color.White
        }else{
            Color.Red
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable { offset = 0 }
                .offset { IntOffset(offset, offset) }
                .padding(horizontal = 40.dp)) {
                if(username.isEmpty()){
                    Row(
                        Modifier.fillMaxWidth()
                        .height(150.dp), Arrangement.Center
                    ){
                        Image(painter = painterResource(id = R.drawable.anh1), contentDescription = "",
                            modifier = Modifier.height(150.dp).width(150.dp).clip(RectangleShape),contentScale = ContentScale.FillBounds
                        )
                    }
                }


                Text(text = "Login", style = MaterialTheme.typography.h5)

                OutlinedTextField(value = username, onValueChange = {username = it
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusUsername),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {focusPassword.requestFocus()}),
                    singleLine = true,
                    label = { Text(text = "Username") },
                    colors= TextFieldDefaults.outlinedTextFieldColors(textColor = blackBTN),
                )

                Spacer(modifier = Modifier
                    .height(5.dp).fillMaxWidth()
                    .background(colorSpaceErro))
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusPassword),
                    value = password,
                    onValueChange ={password = it},
                    label = { Text(text = "Password") },
                    colors= TextFieldDefaults.outlinedTextFieldColors(textColor = blackBTN),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions (onDone = {keyboardController?.hide()}),
                    visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {isPasswordVisible = !isPasswordVisible}) {
                            Icon(imageVector = if(isPasswordVisible) Icons.Default.Lock else Icons.Default.Lock,
                                contentDescription ="Password Toggle" )

                        }
                    }
                )
                Spacer(modifier = Modifier
                    .height(5.dp).fillMaxWidth()
                    .background(colorSpaceErro))
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange = {})
                        Text(text = "Remember me",fontSize = 12.sp)
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Forgot Password",fontSize = 12.sp)
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                login(username,password, onchanecheckStatus = {checkStatus=true})

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), Arrangement.Center
                ){
                    Text(text = "Or log in with", fontSize =14.sp)
                }
                Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceAround){
                    Button(onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(45.dp)
                            .width(45.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_fb), contentDescription = "Facebook logo",
                            modifier = Modifier
                                .weight(1f)
                                .size(40.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                        )
                    }
                    Button(onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(45.dp)
                            .width(45.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "Google logo",
                            modifier = Modifier
                                .weight(1f)
                                .size(40.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                        )
                    }
                    Button(onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(45.dp)
                            .width(45.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_twitter), contentDescription = "Twitter logo",
                            modifier = Modifier
                                .weight(1f)
                                .size(40.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center,verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Don't have account?",fontSize = 14.sp)
                    TextButton(onClick = {
                        navHostController.navigate("account/Signin/SignUp")

                    }) {
                        Text(text = "Register")
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }


    }

}
@Composable
fun login(
          username:String,
          password:String,
          onchanecheckStatus:(Boolean)-> Unit,
          LoginViewModel: SiginViewModel = hiltViewModel()
        ){
    var checkSigIn by remember{ mutableStateOf(false) }
    val state= LoginViewModel.signInState.collectAsState(initial = null)
    var checkloading by rememberSaveable() {
        mutableStateOf<Boolean>(false)
    }
    val context = LocalContext.current
    val scope = CoroutineScope( Job() + Dispatchers.IO)

    if (checkSigIn){
        AlertDialogStatus()
    }

    if(checkloading){
//        CircularIndeterminateProgressBar(isDisplayed = true, verticalBias = 0.3f)
    }
    Button(onClick = {
        checkloading= true

        scope.launch {
            LoginViewModel.loginUser(username.trim(),password.trim())
//            LoginViewModel.loginUser("thoa123@gmail.com","123123")

        }
    },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "Log in")
    }

    LaunchedEffect(key1 = state.value?.isSuccess){
        scope.launch {
            if(state.value?.isSuccess?.isNotEmpty()==true){
                val success=state.value?.isSuccess
                checkSigIn=true
                Log.d(TAG,"thanh cong danh nhap: ${success}")
//                Toast.makeText(context,"${success}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThucTapTheme {
        view()
    }
}