import com.beust.klaxon.Klaxon
import wikextract.EWord
import wikidata.Word
import wikidata.WordAndForm
import word_forms.nounFromWWWord
import word_forms.verbFromWWWord
import wwwords.WWWord
import wwwords.wordListConverter
import java.io.File
import java.text.Normalizer

import kotlin.system.exitProcess


private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun CharSequence.unaccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}

fun main() {
//    val text = loadFile("all-words-raw-3.txt")
//    val removeAccent = text.unaccent()
//    val matches = "(?m)^([a-z]{5}) \\(\\d+ senses\\)".toRegex().findAll(removeAccent)
//    println(matches.count())
//    val words = matches.map { it.groupValues[1] }.toSet().toList().sorted()

//    val file = File("C:\\Users\\danie\\Downloads\\raw-wiktextract-data.json")

//    val text = loadFile("all-5-letter-words2.txt")
//    val words = wordsIn(text)
//    val nice = words.map { it.unaccent() }.toSet().toList().sorted()

//    val file = File("C:\\Users\\danie\\Downloads\\latest-lexemes.json")
//    val inputStream = file.inputStream()
//
//    var count = 0
//    val allWords = mutableSetOf<String>()
//    inputStream.bufferedReader().forEachLine { it ->
//        if (it.contains("\"word\": \"actor\"") && it.contains("\"lang_code\": \"la\"")) {
//            println(it)
//        }
//        val parsed = Klaxon().parse<EWord>(it)!!
//        if (parsed.lang == "Latin") {
//            println(parsed)
//            println(it)
//            if (parsed.forms != null) {
//                allWords.addAll(parsed.forms.map { it.form })
//            }
//        }
//        if (it.contains("nobis")) println(it)
//        val withoutComma = it.removeSuffix(",")
//        if (count != 0 && withoutComma.contains("\"lemmas\":{\"la\"") && !withoutComma.contains("\"forms\":{}")) {
//            try {
//                val parsed = Klaxon().parse<Word>(withoutComma)!!
//                allWords.addAll(parsed.forms.map { it.toWordAndForm() })
//            } catch (e: Exception) {
//                println(withoutComma)
//            }
//        }
//        count++
//        if (count % 10000 == 0) println(count)
//        if (count++ > 10) exitProcess(0)
//    }
//
//    val fiveLetters = allWords.filter { it.length == 5 }.toSet().toList().sorted()
//    File("all-5-letter-words3.txt").printWriter().use { out ->
//        words.forEach { out.println(it) }
//    }

    val words1Text = loadFile("text/text1.txt")
    val words2Text = loadFile("text/text2.txt")
    val allWords1Text = loadFile("all-5-letter-words.txt")
    val allWords3Text = loadFile("all-5-letter-words3.txt")

    val words1Words = wordsIn(words1Text).toSet()
    val words2Words = wordsIn(words2Text).toSet()
    val allWords1 = wordsIn(allWords1Text).toSet()
    val allWords3 = wordsIn(allWords3Text).toSet()
    println((words1Words + words2Words) - allWords1)
    println((words1Words + words2Words) - allWords3)


//    val wWWords = loadFile("wwwords.json")
//
//    val words = Klaxon()
//        .converter(wordListConverter)
//        .parseArray<WWWord>(wWWords) ?: return
//
//    val allWords = mutableSetOf<String>()
//    words.forEach { word ->
//        word.baseForms.words ?: return@forEach
//        if (word.baseForms.words.all { (it?.length ?: 0) > 5 }) return@forEach
//        if (word.baseForms.words.any { it?.first()?.isUpperCase() == true }) return@forEach
//        if (word.age.code !in listOf("X", "C", "D", "E", "F")) return@forEach
//        word.wordType ?: return@forEach
//
//        allWords.addAll(when (word.wordType.code) {
//            "N" -> nounFromWWWord(word)
//            else -> setOf()
//        })
//    }
//    val fiveLetterWords = allWords.filter { it.length == 5 }
//
//    File("5-letter-nouns.txt").printWriter().use { out ->
//        fiveLetterWords.forEach { out.println(it) }
//    }
}

fun wordsIn(string: String): List<String> {
    val matches = "\\b\\w{5}\\b".toRegex().findAll(string).iterator()
    val results = mutableListOf<String>()
    matches.forEach { results.add(it.value) }
    return results
}