package wikextract

data class EWord(
    val lang: String? = null,
    val forms: List<Form>? = null
)

data class Form(
    val form: String
)