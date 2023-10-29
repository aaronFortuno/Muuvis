package aaronfortuno.ioc.muuvis.util.image

import java.io.File
import java.security.MessageDigest

fun calculateHash(file: File): String {
    val buffer = ByteArray(1024)
    val digest = MessageDigest.getInstance("SHA-256")
    val fis = file.inputStream()
    var read = fis.read(buffer)
    while (read > 0) {
        digest.update(buffer, 0, read)
        read = fis.read(buffer)
    }
    val hash = digest.digest()
    return hash.joinToString("") { "%02x".format(it) }
}