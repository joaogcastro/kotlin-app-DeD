package races

interface Race {
    val name: String
    val modifiers: Map<String, Int>
    val abilities: Array<String>
}