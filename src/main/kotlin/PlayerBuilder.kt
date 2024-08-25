import races.*
import kotlin.math.floor

class PlayerBuilder (
    private val races: Array<Race> = arrayOf (
        Dragonborn(),
        Dwarf(),
        Elf(),
        Gnome(),
        HalfElf(),
        Halfling(),
        HalfOrc(),
        Human(),
        Tiefling()
    ),
    private val abilitiesSample: Array<String> = arrayOf(
        "Strength",
        "Dexterity",
        "Constitution",
        "Intelligence",
        "Wisdom",
        "Charisma"
    ),
    private val pointCost: Map<Int, Int> = mapOf(
        8 to 0,
        9 to 1,
        10 to 2,
        11 to 3,
        12 to 4,
        13 to 5,
        14 to 7,
        15 to 9
    ),
    private var pointBuyBalance: Int = 27
){

    /******************************************************************************************
     * Public functions
     ******************************************************************************************/

    /**
     * Create player
     */
    fun create(): Player {
        println("\n-------- Player creation --------")
        val player = Player()

        // Name
        println("\n\nPlayer name:")
        player.name = readlnOrNull().toString()

        // Race
        println("\n\nChoose your race")
        selectRace(player)

        // Abilities
        println("\n\nChoose your abilities")
        selectAbilities(player)
        setRaceModifiers(player)

        // HP
        setHealthPoints(player)

        println("\n-------- Player created successfully --------")
        return player
    }

    /**
     * Show player status
     */
    fun status(player: Player) {
        println("\n\n\n")
        println("-------- Player Status --------")
        println("Name: ${player.name}")
        println("Race: ${player.race?.name}")
        println("Abilities:")
        showAbilities(player)
        println("\nHP: ${player.healthPoints}")
        println("-------------------------------")
    }


    /******************************************************************************************
     * Private functions
     ******************************************************************************************/

    /**
     * Select player race
     */
    private fun selectRace(player: Player) {
        println("Available races:")
        races.forEachIndexed { index, race ->
            println("${index}: ${race.name}")
        }

        var validInput = false
        while (!validInput) {
            println("Select your race (0-8):")
            val selectedRace = readlnOrNull()?.toIntOrNull()

            if (selectedRace in 0..8) {
                println("Selected race ${races[selectedRace!!].name}")
                player.race = races[selectedRace]
                validInput = true
            } else {
                println("\nInvalid input. Please enter a value between 0 and 8.")
            }
        }
    }

    /**
     * Select player abilities using Point Buy system
     */
    private fun selectAbilities(player: Player) {
        val abilities = mutableMapOf<String, Int>()

        println("You have $pointBuyBalance points to distribute among the abilities.")

        abilitiesSample.forEach { ability ->
            var validInput = false
            while (!validInput) {
                println("Enter the value for $ability (8-15):")
                val abilityValue = readLine()?.toIntOrNull()

                if (abilityValue in 8..15) {
                    val cost = pointCost[abilityValue] ?: 0

                    if (pointBuyBalance >= cost) {
                        abilities[ability] = abilityValue!!
                        pointBuyBalance -= cost
                        println("$ability set to $abilityValue. Points remaining: $pointBuyBalance")
                        validInput = true
                    } else {
                        println("\nNot enough points remaining for that value. Try a lower value.")
                    }
                } else {
                    println("\nInvalid input. Please enter a value between 8 and 15.")
                }
            }
        }

        // Assign the abilities to the player
        abilities.forEach { (ability, value) ->
            player.abilities[ability] = value
        }

        println("\nFinal player abilities:")
        showAbilities(player)
    }

    /**
     * Set the race and the abilities modifiers
     */
    private fun setRaceModifiers(player: Player) {
        for (modifier in player.race!!.modifiers) {
            player.abilities[modifier.key] = (player.abilities[modifier.key] ?: 0) + modifier.value
        }
        println("\n\nPlayer abilities with race modifiers:")
        showAbilities(player)
    }

    /**
     * Show current player abilities and values
     */
    private fun showAbilities(player: Player) {
        player.abilities.forEach { (key, value) ->
            println("$key: $value")
        }
    }

    /**
     * Set health points based on player's constitution
     */
    private fun setHealthPoints(player: Player) {
        player.healthPoints = player.hitDie + constitutionModifier(player.abilities["Constitution"]!!)
    }

    /**
     * Calculate constitution modifier
     */
    private fun constitutionModifier(constitution: Int): Int {
        val result = (constitution - 10).toDouble() / 2
        return floor(result).toInt()
    }
}
