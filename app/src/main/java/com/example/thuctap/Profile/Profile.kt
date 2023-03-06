package com.example.thuctap

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.thuctap.ui.theme.ThucTapTheme
import coil.compose.rememberAsyncImagePainter


@Composable
fun Profile() {
    Column(Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally){
        Row(Modifier.fillMaxWidth().fillMaxHeight(0.1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            ){
            Row(Modifier.fillMaxWidth(0.25f)
                .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically){
                    Column(Modifier) {
                        Image(painter = painterResource(id = R.drawable.ic_back_1)
                            ,contentDescription = ""
                            ,modifier = Modifier.height(20.dp).width(25.dp)

                        )

                    }
                    Text(text ="Profile", fontWeight = FontWeight.Bold, fontSize = 20.sp )
            }
            Row(Modifier.fillMaxWidth(0.50f).fillMaxHeight(),
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
        Row(Modifier.fillMaxHeight(0.2f).fillMaxWidth().padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){
            Image(painter = rememberAsyncImagePainter("https://tse2.mm.bing.net/th?id=OIP.XgK18C8qMMhf9KZwMWX-twHaE7&pid=Api&P=0")
                , contentDescription = "",
                modifier = Modifier.size(80.dp).clip(CircleShape),contentScale = ContentScale.FillBounds
            )
            Column(Modifier.padding(start = 20.dp)){
                Text(text = "Vo Quang Tan", color = Color.Red, fontSize = 20.sp)
                Text(text = "sdfsdfsdfsdfsdf")
            }
        }
        Column (Modifier.fillMaxWidth().padding(start = 20.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(text = "Contact Details", fontSize = 16.sp)
        }
        TapProfile("Edit Profile",R.drawable.ic_profile_male)
        TapProfile("Email address",R.drawable.ic_gmail)
        TapProfile("Phone number",R.drawable.ic_phone)
        TapProfile("Residenttial addresses",R.drawable.ic_map)
        TapProfile("Sign out",R.drawable.ic_sign_out)
        Spacer(Modifier.height(1.dp).fillMaxWidth().background(Color.Gray))
    }
}
@Composable
fun TapProfile (text:String,idIcon:Int){
    Column(Modifier.fillMaxWidth()){
        Spacer(Modifier.height(1.dp).fillMaxWidth().background(Color.Gray))
        Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 15.dp)){
            Image(painter = painterResource(id = idIcon)
                ,contentDescription = ""
                ,modifier = Modifier.height(25.dp).width(25.dp)

            )
            Spacer(Modifier.width(10.dp))
            Text(text=text)
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
                ){
                Image(painter = painterResource(id = R.drawable.ic_next)
                    ,contentDescription = ""
                    ,modifier = Modifier.height(15.dp).width(15.dp)

                )
            }

        }

    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ThucTapTheme {
//        Profile()
//    }
//}