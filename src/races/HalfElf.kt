package races

class HalfElf(
    override val name: String = "Half-Elf",
    override val modifiers: Map<String, Int> = mapOf(
        "Charisma" to 2,
        "Dexterity" to 1, // Pode ser ajustado conforme o jogador escolher
        "Wisdom" to 1     // Pode ser ajustado conforme o jogador escolher
    ),
    override val abilities: Array<String> = arrayOf(
        "Darkvision",
        "Fey Ancestry",
        "Skill Versatility"
    )
) : Race
