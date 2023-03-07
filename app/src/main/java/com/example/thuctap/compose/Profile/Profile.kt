package com.example.thuctap

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thuctap.ui.theme.ThucTapTheme
import coil.compose.rememberAsyncImagePainter
import com.example.thuctap.ui.theme.hongcanhsen
import com.example.thuctap.ui.theme.line_Gray


@Composable
fun Profile(navHostController: NavHostController) {
    Column(Modifier.fillMaxSize().padding(horizontal = 1.dp),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ){
            Row(Modifier.fillMaxWidth(0.30f)
                ,
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically){
                    Column(Modifier) {
                        Image(painter = painterResource(id = R.drawable.ic_back_1)
                            ,contentDescription = ""
                            ,modifier = Modifier.height(20.dp).width(25.dp)

                        )

                    }
                    Text(text ="Profile", fontWeight = FontWeight.Bold, fontSize = 22.sp )
            }
            Row(Modifier.fillMaxWidth(0.50f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(id = R.drawable.vector__4_)
                    ,contentDescription = ""
                    ,modifier = Modifier.height(20.dp).width(20.dp)

                )
                Image(painter = painterResource(id = R.drawable.ic_back_1)
                    ,contentDescription = ""
                    ,modifier = Modifier.height(20.dp).width(25.dp)

                )
                Image(painter = painterResource(id = R.drawable.shoppingcartoutlined)
                    ,contentDescription = ""
                    ,modifier = Modifier.height(22.dp).width(25.dp)

                )
            }
        }
        Spacer(Modifier.height(35.dp))
        Row(Modifier.fillMaxHeight(0.2f).fillMaxWidth().padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){
            Image(painter = rememberAsyncImagePainter("https://tse2.mm.bing.net/th?id=OIP.XgK18C8qMMhf9KZwMWX-twHaE7&pid=Api&P=0")
                , contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(70.dp).clip(CircleShape)
            )
            Column(Modifier.padding(start = 20.dp)){
                Text(text = "fun E", color = hongcanhsen, fontWeight = FontWeight(400) ,fontSize = 20.sp)
                Spacer(Modifier.height(5.dp))
                Text(text = "voquanhtan360.@gmail.com")
            }
        }
        Spacer(Modifier.height(15.dp))
        Column (Modifier.fillMaxWidth().padding(start = 10.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(text = "Contact Details", fontSize = 16.sp)
        }
        Spacer(Modifier.height(5.dp))
        TapProfile("Edit Profile",R.drawable.ic_profile_male,navHostController)
        TapProfile("Email address",R.drawable.ic_gmail,navHostController)
        TapProfile("Phone number",R.drawable.ic_phone,navHostController)
        TapProfile("Residenttial addresses",R.drawable.ic_map,navHostController)
        TapProfile("Sign In",R.drawable.ic_sign_out,navHostController)
        Spacer(Modifier.height(1.dp).fillMaxWidth().background(line_Gray))
    }
}
@Composable
fun TapProfile (text:String,idIcon:Int,navHostController: NavHostController){
    Column(Modifier.fillMaxWidth().clickable {
        when (text){
            "Edit Profile"->{
                navHostController.navigate("account/editAccount")
            }
            "Sign In"->{
                navHostController.navigate("account/Signin")
            }
        }
    }){
        Spacer(Modifier.height(1.dp).fillMaxWidth().background(line_Gray))
        Spacer(Modifier.height(5.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = idIcon)
                ,contentDescription = ""
                ,modifier = Modifier.height(25.dp).width(25.dp)

            )
            Spacer(Modifier.width(15.dp))
            Text(text=text, fontSize = 17.sp, fontWeight = FontWeight(400))
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
                ){
                Image(painter = painterResource(id = R.drawable.ic_next)
                    ,contentDescription = ""
                    ,modifier = Modifier.height(10.dp).width(10.dp)

                )
            }
            Spacer(Modifier.width(5.dp))

        }
        Spacer(Modifier.height(5.dp))

    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ThucTapTheme {
//        val navController = rememberNavController()
//        Profile(navController)
//    }
//}