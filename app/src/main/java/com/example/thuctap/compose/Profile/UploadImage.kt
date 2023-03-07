package com.example.thuctap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.thuctap.ui.theme.GrayText100
import com.example.thuctap.ui.theme.ThucTapTheme
import com.example.thuctap.ui.theme.blackBTN
import com.example.thuctap.ui.theme.white
import com.example.thuctap.view_model.CertificatesViewModel

@Composable
fun UloadImage(
    certificatesViewModel: CertificatesViewModel =hiltViewModel(),
    onCheck: (Boolean)->Unit,
    checkUpdate:Boolean
){

    var filePath by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) {
            uri: Uri? -> filePath = uri
    }
    val context = LocalContext.current.applicationContext
    val bitmap = rememberSaveable {
        mutableStateOf<Bitmap?>(null)
    }
    if(checkUpdate){
        certificatesViewModel.stateLoadingImage = true
//                            CoroutineScope(Dispatchers.Main + Job()).launch {
        onUpload(filePath!!, context, certificatesViewModel)
//                                Log.d("abc", "abc")
//                            }
        onCheck(false)

    }
    Column (Modifier.fillMaxSize()){
        Text(text="Update Cover Media",fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(5.dp))
        Column(
            Modifier.fillMaxWidth()
                .dashedBorder(1.dp, Color.Red, 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painter = painterResource(id = R.drawable.ic_upload), contentDescription = "",
                modifier = Modifier.size(100.dp)

            )
            Spacer(Modifier.height(20.dp))
            Text(text="Let's upload photos and video", color = GrayText100)
            Spacer(Modifier.height(20.dp))
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceAround){
                Row(
                    Modifier.height(35.dp)
                        .clip(shape = RoundedCornerShape(topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomStart = 5.dp,
                            bottomEnd = 5.dp)
                        ).background(blackBTN).clickable {
                            launcher.launch("image/jpeg")
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement= Arrangement.Center,

                    ){
                    Spacer(Modifier.width(3.dp))
                    Image(painter = painterResource(id = R.drawable.ic_upload), contentDescription = "",
                        modifier = Modifier.size(20.dp)

                    )
                    Text(text = "OPEN GALLERY",  fontWeight = FontWeight.Bold ,color = white)
                    Spacer(Modifier.width(3.dp))
                }
                Row(
                    Modifier.height(35.dp)
                        .clip(shape = RoundedCornerShape(topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomStart = 5.dp,
                            bottomEnd = 5.dp)
                        ).background(blackBTN)
                        ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement= Arrangement.Center){
                    Spacer(Modifier.width(3.dp))
                    Image(painter = painterResource(id = R.drawable.ic_upload), contentDescription = "",
                        modifier = Modifier.size(20.dp)

                    )
                    Text(text = "OPEN CAMERA", fontWeight = FontWeight.Bold, color = white)
                    Spacer(Modifier.width(3.dp))
                }
            }
            Spacer(Modifier.height(5.dp))
            Row(
                Modifier.fillMaxWidth()
                    .padding(horizontal = 30.dp)
                ,
                horizontalArrangement = Arrangement.Center
            ){
                filePath?.let {
                    if(Build.VERSION.SDK_INT < 28) {
                        bitmap.value = MediaStore.Images
                            .Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder
                            .createSource(context.contentResolver, it)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    bitmap.value?.let {
                        Column {
                            Image(bitmap = it.asImageBitmap(),
                                contentDescription = "Uploaded Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(16.dp)))
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
                if(filePath==null){
                    Image(painter = painterResource(id = R.drawable.anh1),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(16.dp)))
                }

                //                        modifier = Modifier.size(200.dp)
//                            .border(width = 1.dp, color = blackBTN)
//                            .clip(shape = RoundedCornerShape(topStart = 10.dp,
//                            topEnd = 10.dp,
//                            bottomStart = 10.dp,
//                            bottomEnd = 10.dp))
//                             )
            }
            Spacer(Modifier.height(10.dp))


        }
    }
    }


@Composable
fun CertificateImageUpload(
    certificatesViewModel: CertificatesViewModel =hiltViewModel(),
    onCheck: ()->Unit
) {

    var filePath by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) {
            uri: Uri? -> filePath = uri
    }
    val context = LocalContext.current.applicationContext
    val bitmap = rememberSaveable {
        mutableStateOf<Bitmap?>(null)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                top = 5.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Column (
            modifier = Modifier
                .width(500.dp)
                .align(Alignment.Center)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            filePath?.let {
                if(Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let {
                    Column {
                        Image(bitmap = it.asImageBitmap(),
                            contentDescription = "Uploaded Image",
                            modifier = Modifier.size(400.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
            if(filePath==null) {
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { launcher.launch("image/jpeg") }) {
                    Icon(
                        modifier = Modifier.size(200.dp),
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Image icon")
                }
            }

            Row {
                if(filePath!=null) {
                    if(certificatesViewModel.stateLoadingImage) {
                        Button(onClick = {}, enabled = false)
                        {
                            CircularProgressIndicator(
                                modifier = Modifier.size(17.dp),
                                color = Color.White
                            )
                        }
                    } else {
                        Button(onClick = {
                            certificatesViewModel.stateLoadingImage = true
//                            CoroutineScope(Dispatchers.Main + Job()).launch {
                            onUpload(filePath!!, context, certificatesViewModel)
//                                Log.d("abc", "abc")
//                            }
                        }) {
                            Text(
                                text = "Add",
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                }
                Button(onClick = { certificatesViewModel.stateUpload = false }) {
                    Text(
                        text = "Cancel"
                    )
                }
            }
        }
    }
}



fun onUpload(filePath: Uri, context: Context, certificatesViewModel: CertificatesViewModel) {
    val TAG = "Cloudinary log: "
    MediaManager.get().upload(filePath).callback(object : UploadCallback {
        override fun onStart(requestId: String) {
            Log.d(TAG, "onStart: " + "started")
        }

        override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
            Log.d(TAG, "onStart: " + "uploading")
        }

        override fun onSuccess(requestId: String, resultData: Map<*, *>?) {
            Log.d(TAG, "onStart: " + "usuccess")
            Log.d(TAG, "onStart: link" + resultData!!.get("secure_url").toString())
            certificatesViewModel.addImageCertificate(resultData!!.get("secure_url").toString())
//            onCheck()
            certificatesViewModel.stateLoadingImage = false
        }

        override fun onError(requestId: String?, error: ErrorInfo) {
            Log.d(TAG, "onStart: $error")
        }

        override fun onReschedule(requestId: String?, error: ErrorInfo) {
            Log.d(TAG, "onStart: $error")
        }
    }).dispatch()
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ThucTapTheme {
//        UloadImage(onCheck={}, checkUpdate = false)
//    }
//}