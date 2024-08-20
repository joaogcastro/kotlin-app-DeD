package races

class Tiefling(
    override val name: String = "Tiefling",
    override val modifiers: Map<String, Int> = mapOf(
        "Charisma" to 2,
        "Intelligence" to 1
    ),
    override val abilities: Array<String> = arrayOf(
        "Darkvision",
        "Hellish Resistance",
        "Infernal Legacy"
    )
) : Race
