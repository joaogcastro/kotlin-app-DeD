package races

class Gnome(
    override val name: String = "Gnome",
    override val modifiers: Map<String, Int> = mapOf(
        "Intelligence" to 2
    ),
    override val abilities: Array<String> = arrayOf(
        "Darkvision",
        "Gnome Cunning"
    )
) : Race
