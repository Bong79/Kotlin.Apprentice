import kotlin.system.exitProcess

val letters= listOf("a", "b", "c", "d",
"e", "f", "g", "h", "i", "j", "k", "l", "m",
"n", "o", "p", "q", "r", "s", "t", "u", "v",
"w", "x", "y", "z")

fun rotate(string: String, byChar: Int): String {

    val rotLetters= string.map { char ->
        val isUpper = char.isUpperCase()
        var stringToFind = char.toString()
        if (isUpper){
            stringToFind = char.toLowerCase().toString()
        }
        val index= letters.indexOf(stringToFind)
        if (index == -1){
            return@map char.toString()
        }
        val rotIndex= (index+ byChar) % letters.size
        val letter = letters[rotIndex]
        if (isUpper) {
            letter.toUpperCase()
        }else letter
    }
    return rotLetters.joinToString("")
}

val starting = "aZ"
val rotation= 13
val expected = "dC"

val actual =rotate(starting, rotation)

if (actual== expected) {
    println("SUCCESS")
}else println("FAIL! got $actual")

fun valueFromArgsPrefix(prefix: String): String? {
    val arg = args.firstOrNull { it.startsWith(prefix) }

    if (arg == null) return null

        val pieces = arg.split("=")
        return if (pieces.size == 2) {
            pieces[1]
        } else null

    }

//val stringToRotPrefix= "mM"
    val stringToRotPrefix = "rotate="
    val numToRotPre = "places="
//val numToRotPre ="13"

    val stringToRot = valueFromArgsPrefix(stringToRotPrefix)
    if (stringToRot == null) {
        println("No str to rotate given")
        exitProcess(0)
    } else {
        val numToRot = valueFromArgsPrefix(numToRotPre)?.toInt()

        if (numToRot == null) {
            println("No num to rotate given")
            println("Result: $stringToRot")
            exitProcess(0)
        } else {
            val rotated = rotate(stringToRot, numToRot)

            println("$stringToRot rotated $numToRot places is $rotated")

            val rerotated = rotate(rotated, numToRot)
            println("$rotated rotated $numToRot places is $rerotated")

            if (rotated == rerotated && numToRot != letters.size) {
                println("You cracked encript & decript")
            } else {
                println("try again")
            }
        }
    }
