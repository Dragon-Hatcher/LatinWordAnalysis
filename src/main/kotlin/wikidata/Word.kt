package wikidata

import com.beust.klaxon.Json

data class Word(
    val forms: List<Form>
)

data class Form(
    val representations: Representation,
    val grammaticalFeatures: List<String>
) {
    fun toWordAndForm() = WordAndForm(representations.la.value, grammaticalFeatures)

}

data class Representation(
    val la: Value
)

data class Value(
    val value: String
)

data class WordAndForm(
    val word: String,
    val grammaticalFeatures: List<String>
)