package com.example.thuctap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebaseauthyt.navigation.Navigation
import com.example.thuctap.compose.Login.RegisterActivity
import com.example.thuctap.compose.Login.SigInScreen
import com.example.thuctap.ui.theme.ThucTapTheme
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThucTapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    EditProfile()
//                    CertificateImageUpload(onCheck={})
//                    UloadImage(onCheck={})
//                    RegisterActivity()
                    val firebaseDatabase = FirebaseDatabase.getInstance();
                    val databaseReference = firebaseDatabase.getReference("EmployeeInfo");
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ThucTapTheme {
//        Greeting("Android")
//    }
//}