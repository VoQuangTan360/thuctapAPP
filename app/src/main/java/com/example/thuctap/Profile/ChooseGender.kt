package com.example.thuctap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thuctap.ui.theme.ThucTapTheme
import com.example.thuctap.ui.theme.blackBTN
import com.example.thuctap.ui.theme.hongcanhsen
import com.example.thuctap.ui.theme.white

@Composable
fun ChooseGender(EdittextStatus:Boolean) {
    val items = listOf("Male", "Female")
//    Row(Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween){
//        Row(Modifier.height(40.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically){
//
//        }
//
//    }
    val selectedValue = remember { mutableStateOf("") }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    if(EdittextStatus){
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),

            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            items.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = isSelectedItem(item),
                        onClick = { onChangeState(item) },
                        role = androidx.compose.ui.semantics.Role.RadioButton
                    ).clip(shape = RoundedCornerShape(topStart = 5.dp,
                        topEnd = 5.dp,
                        bottomStart = 5.dp,
                        bottomEnd = 5.dp))
                        .background(hongcanhsen)
                        .padding(end = 5.dp)
                        .width(110.dp),



                ) {
                    RadioButton(
                        selected = isSelectedItem(item),
                        onClick = {
                            selectedValue.value = item
                        },
                        colors= RadioButtonDefaults.colors(white,white,white)
                    )
                    Text(
                        text = item,

                        modifier = Modifier.padding(top = 13.dp),
                        color= white,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        if(selectedValue.value =="Male"){

        }
        if(selectedValue.value==""){

        }
    }else{


    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ThucTapTheme {
//        ChooseGender(true)
//    }
//}