package com.deto.notes.ui.components
import com.deto.notes.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomAlertDialog(showDialog: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit, size: Int, title: String) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                ) },
            text = {
                Text(
                    text =
                        if(size > 1)
                            "¿Eliminar $size elementos?"
                        else {
                            "¿Eliminar elemento?"
                        },
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        modifier = Modifier.padding(10.dp).weight(1f),
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {

                        Text(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                            text = stringResource(R.string.customAlertDialog_cancelar),
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }


                    TextButton(
                        modifier = Modifier.padding(10.dp).weight(1f),
                        onClick = {
                            onConfirm()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                            text = stringResource(R.string.customAlertDialog_eliminar),
                            color = Color.Red,
                            fontSize = 16.sp
                        )
                    }
                }
            },

        )
    }
}