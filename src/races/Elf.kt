package races

class Elf(
    override val name: String = "Elf",
    override val modifiers: Map<String, Int> = mapOf(
        "Dexterity" to 2
    ),
    override val abilities: Array<String> = arrayOf(
        "Darkvision",
        "Keen Senses",
        "Fey Acestry",
        "Trance"
    )
) : Race