package com.example.thuctap

import androidx.compose.foundation.*

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.thuctap.Profile.DatePickerUI
import com.example.thuctap.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditProfile() {
    var textFun= remember { mutableStateOf( "") }
    var checkUpdate=remember { mutableStateOf( false) }
    var CheckGender= remember { mutableStateOf( false) }
    var CheckDate= remember { mutableStateOf( false) }
    var devText= remember { mutableStateOf( "Dev") }
    val (focusUsername,focusPassword) = remember { FocusRequester.createRefs()}
    var username by remember{ mutableStateOf("") }
    Column(Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())

    ) {
        Row(
            Modifier.fillMaxWidth().fillMaxHeight(0.1f),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Row(
                Modifier.fillMaxWidth(0.40f)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically){
                Column(Modifier) {
                    Image(painter = painterResource(id = R.drawable.ic_back_1)
                        ,contentDescription = ""
                        ,modifier = Modifier.height(20.dp).width(25.dp)

                    )

                }
                Text(text ="Edit Profile", fontWeight = FontWeight.Bold, fontSize = 20.sp )
            }

        }
        Box(Modifier.fillMaxWidth().fillMaxHeight(0.2f)){
            Row(Modifier.fillMaxWidth().padding(vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement=Arrangement.Center
                ){
                Image(painter = rememberAsyncImagePainter("https://tse2.mm.bing.net/th?id=OIP.XgK18C8qMMhf9KZwMWX-twHaE7&pid=Api&P=0")
                    , contentDescription = "",
//                Image(painter =painterResource(id = R.drawable.untitled), contentDescription = "",
                    modifier = Modifier.size(110.dp)
                        .clip(shape = RoundedCornerShape(topStart = 25.dp,
                            topEnd = 25.dp,
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp)
                        ),contentScale = ContentScale.FillBounds
                )
            }
            Row(Modifier.fillMaxWidth().padding(vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement=Arrangement.Center
                ) {
                Row(Modifier.width(120.dp).height(110.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement=Arrangement.End){
                    Image(painter =painterResource(id = R.drawable.ic_map), contentDescription = "",
                        modifier = Modifier.size(25.dp)

                    )
                }

            }

        }
        Column(Modifier.fillMaxSize().padding(horizontal = 10.dp)) {
           UloadImage(onCheck = {checkUpdate.value=it},checkUpdate= checkUpdate.value)
            Text(text = "Add description",  fontWeight = FontWeight.Bold )
            TextField(value = username, onValueChange = {username = it
            },
                modifier = Modifier
                    .fillMaxWidth().height(120.dp)
                    .border(width=1.dp, brush = Brush.horizontalGradient(listOf(blackBTN, blackBTN)) ,shape = RoundedCornerShape(10.dp))
                    .focusRequester(focusUsername),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {focusPassword.requestFocus()}),

                label = {Text(text = "Abc")},
                colors= TextFieldDefaults.outlinedTextFieldColors(textColor = blackBTN),
            )
            Spacer(Modifier.height(15.dp))
            Column (Modifier.fillMaxWidth()
                .border(width = 1.dp,
                    brush = Brush.horizontalGradient(listOf(blackBTN, blackBTN)),shape = RoundedCornerShape(10.dp))

                    ){
                Row(
                    Modifier.padding(horizontal = 10.dp).height(60.dp),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Image(painter =painterResource(id = R.drawable.ic_gmail), contentDescription = "",
                        modifier = Modifier.size(20.dp)

                    )
                    Spacer(Modifier.width(5.dp))
                    Text(text="fun E", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.width(5.dp))
                    TextField(
                        value =textFun.value,
                        textStyle = TextStyle( fontWeight =FontWeight.Bold ),
                        onValueChange = { textFun.value=it},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = white,
                            unfocusedBorderColor = white,
                            focusedLabelColor = Color.Black,
                            cursorColor = Color(0xFFCFDCFF)
                        ),
                    )

                }
                Spacer(Modifier.height(1.dp).fillMaxWidth().background(blackBTN))
                Row(
                    Modifier.padding(horizontal = 10.dp).height(60.dp),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Image(painter =painterResource(id = R.drawable.ic_date), contentDescription = "",
                        modifier = Modifier.size(20.dp)

                    )
                    Spacer(Modifier.width(5.dp))
                    Text(text="Date of brith")
                    Spacer(Modifier.width(5.dp))
                    Image(painter =painterResource(id = R.drawable.ic_down), contentDescription = "",
                        modifier = Modifier.size(20.dp)
                            .clickable { CheckDate.value=!CheckDate.value }

                    )

                }
                if(CheckDate.value){
                    Spacer(Modifier.height(1.dp).fillMaxWidth().background(blackBTN))
                    DatePickerUI("test", onDismissRequest={})
                }
                Spacer(Modifier.height(1.dp).fillMaxWidth().background(blackBTN))
                Row(
                    Modifier.padding(horizontal = 10.dp).height(60.dp),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Image(painter =painterResource(id = R.drawable.bi_gender_ambiguous), contentDescription = "",
                        modifier = Modifier.size(20.dp)

                    )
                    Spacer(Modifier.width(5.dp))
                    Text(text="Gender")
                    Spacer(Modifier.width(5.dp))
                    Image(painter =painterResource(id = R.drawable.ic_down), contentDescription = "",
                        modifier = Modifier.size(20.dp).clickable { CheckGender.value=!CheckGender.value }

                    )

                }
                Spacer(Modifier.height(1.dp).fillMaxWidth().background(blackBTN))
                ChooseGender(CheckGender.value)
                Spacer(Modifier.height(1.dp).fillMaxWidth().background(blackBTN))
                Row(
                    Modifier.padding(horizontal = 10.dp).height(60.dp),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value =devText.value,
                        textStyle = TextStyle( fontWeight =FontWeight.Bold ),
                        onValueChange = {
                            devText.value=it},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = white,
                            unfocusedBorderColor = white,
                            focusedLabelColor = Color.Black,
                            cursorColor = Color(0xFFCFDCFF)
                        ),
                    )

                }
            }
            Spacer(Modifier.height(15.dp))
            Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            ){
                Button(onClick = {
                    checkUpdate.value=true

                },Modifier.fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(15.dp,15.dp,15.dp,15.dp))
                    .background(hongcanhsen),


                ){
                    Text(text = "UPDATE", fontWeight = FontWeight.Bold, color = white)
                }

            }
            Spacer(Modifier.height(25.dp))
        }

    }

}
fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ThucTapTheme {
//        EditProfile()
//    }
//}