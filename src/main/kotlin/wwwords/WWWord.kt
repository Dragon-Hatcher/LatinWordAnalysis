package wwwords

import com.beust.klaxon.Converter
import com.beust.klaxon.Json
import com.beust.klaxon.JsonValue

data class WWWord(
    val age: CodeValue,
    @Json(name = "base_forms")
    val baseForms: WordList,
    val comparative: WordList = WordList(null),
    val superlative: WordList = WordList(null),
    @Json(name = "class")
    val wordType: CodeValue?,
    val declension: Int? = null,
    val conjugation: Int? = null,
    val freq: CodeValue,
    val gender: CodeValue? = null,
)

data class WordList(val words: List<String?>?)

val wordListConverter = object: Converter {
    override fun canConvert(cls: Class<*>)
            = cls == WordList::class.java

    override fun toJson(value: Any): String
            = TODO()

    override fun fromJson(jv: JsonValue)
            = WordList(jv.inside as List<String?>?)

}