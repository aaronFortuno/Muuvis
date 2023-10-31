package aaronfortuno.ioc.muuvis.ui.view.dialogs

import aaronfortuno.ioc.muuvis.ui.theme.MuuvisTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DialogTest() {
    var expanded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = { /*TODO*/ },
                text = {
                    Text(text = "Refresh")
                })
            DropdownMenuItem(
                onClick = { /*TODO*/ },
                text = {
                    Text(text = "Settings")
                })
            Divider()
            DropdownMenuItem(
                onClick = { /*TODO*/ },
                text = {
                    Text(text = "Send Feedback")
                })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DialogTestPreview() {
    MuuvisTheme {
        DialogTest()
    }
}