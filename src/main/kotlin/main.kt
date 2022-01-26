import com.beust.klaxon.Klaxon
import wwwords.WWWord
import wwwords.wordListConverter

fun main() {
    val ciceroWords = loadFile("cicero5letters.txt")
    val wWWords = loadFile("wwwords.json")

    val words = Klaxon()
        .converter(wordListConverter)
        .parseArray<WWWord>(wWWords) ?: return
    println(words[417])
}