import java.io.File

//println("alo, scripting")
//if (args.isEmpty()) {
//    println("[no args]")
//} else println("Args:\n ${args.joinToString("\n ")}")

fun currentFolder(): File {
    return File("").absoluteFile
}

fun File.contents(includHidden: Boolean): List<File> {
    val fileList =this.listFiles().toList()
    return if (includHidden ) {
        fileList
    }else fileList.filter { !it.isHidden }
}
//fun File.contents(): List<File> {
//    return this.listFiles().toList()
//}

fun File.folders(includHidden: Boolean): List<File> {
    return this.contents(includHidden).filter { it.isDirectory }
}

fun File.files(includHidden: Boolean): List<File> {
    return this.contents(includHidden).filter { it.isFile }
}


fun File.fileNames(includHidden: Boolean): List<String> {
    return this.files(includHidden).map { it.name }
}

fun File.folderNames(includHidden: Boolean): List<String> {
    return this.folders(includHidden).map { it.name }
}

fun File.printFolderInfo(includHidden: Boolean) {
    println("contents of `${this.name}` :")

    if (this.folders(includHidden).isNotEmpty()) {
        println("- Folders:\n ${this.folderNames(includHidden).joinToString("\n  ")}")
    }
    if (this.files(includHidden).isNotEmpty()) {
        println("- Files:\n ${this.fileNames(includHidden).joinToString("\n  ")}")
    }
    println("Parent: ${this.parentFile?.name}")
}

//println("Cur folder contents:\n ${current.fileNames().joinToString("\n")}")
fun valueFromArgsPrefix(prefix: String): String? {
    val arg = args.firstOrNull { it.startsWith(prefix) }

    if (arg== null  ) return null

    val pieces= arg.split("=")
    return if (pieces.size ==2){
        pieces[1]
    }else null
}
val folderPre ="folder="
val folderVal = valueFromArgsPrefix(folderPre)

val hiddenPre = "showHidden="
val hiddenStringVal = valueFromArgsPrefix(hiddenPre )
val hiddenVal = hiddenStringVal?.toBoolean() ?: false

val current = currentFolder()
current.printFolderInfo(hiddenVal)

if (folderVal != null) {
    val folder = File(folderVal).absoluteFile
    folder.printFolderInfo(hiddenVal)
}else println("No path provided, printing work dir")
currentFolder().printFolderInfo(hiddenVal)
