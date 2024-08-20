package races

class Dwarf(
    override val name: String = "Dwarf",
    override val modifiers: Map<String, Int> = mapOf(
        "Constitution" to 2
    ),
    override val abilities: Array<String> = arrayOf(
        "Darkvision",
        "Dwarven Resilience",
        "Stonecunning",
        "Dwarven Combat Training"
    )
) : Race
