package word_forms

import wwwords.WWWord

fun verbFromWWWord(wwWord: WWWord): Set<String> {
    val conjugation = wwWord.conjugation ?: return setOf()
    val pps = wwWord.baseForms.words!!
    if (pps.size < 2 || pps[0] == null || pps[1] == null) return setOf()
    val presSingFirst = pps[0]
    val infinitive = pps[1]

    val endings = when (conjugation) {
        1 -> listOf(
            "s", "t", "mus", "tis", "nt",
            "bo", "bis", "bit",
            "bam", "bas", "bat", "bant",

        )
        else -> throw IllegalArgumentException("unknown conjugation $conjugation for word $presSingFirst")
    }

    TODO()
}
