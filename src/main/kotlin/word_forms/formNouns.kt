package word_forms

import wwwords.WWWord

fun nounFromWWWord(wwWord: WWWord): Set<String> {
    wwWord.declension ?: return setOf()

    val pps = wwWord.baseForms.words!!
    if (pps.size != 2 || pps.any { it == null }) return setOf()
    val nomSing = pps[0]!!
    val genSing = pps[1]!!
    val declension = wwWord.declension
    val gender = wwWord.gender?.code
    val endings = when (declension) {
        1 -> listOf("ae", "am", "a", "arum", "is", "as")
        2 -> if (gender != "N") listOf("i", "um", "o", "e", "i", "orum", "is", "os") else listOf("i", "um", "o", "a", "orum", "is", "a")
        3 -> if (gender != "N") listOf("is", "i", "em", "e", "es", "um", "ium", "ibus") else listOf("is", "i", "e", "a", "um", "ibus")
        4 -> if (gender != "N") listOf("us", "ui", "um", "u", "uum", "ibus") else listOf("us", "u", "uum", "ibus", "ua")
        5 -> listOf("ei", "em", "e", "es", "erum", "ebus")
        else -> throw IllegalArgumentException("unknown gender declension for word $nomSing: $declension")
    }
    val stem = genSing.removeSuffix(endings[0])
    return (listOf(nomSing) + endings.map { stem + it }).toSet()
}