import java.util.Scanner
import races.*

var scanner = Scanner(System.`in`)

class PlayerBuilder {

    // Array of available races to choose
    val races: Array<Race> = arrayOf (
        Dragonborn(),
        Dwarf(),
        Elf(),
        Gnome(),
        HalfElf(),
        Halfling(),
        HalfOrc(),
        Human(),
        Tiefling()
    )

    fun create(): Player? {
        var player: Player = Player()

        // Name
        println("Player name:")
        player.name = scanner.nextLine()

        // Race
        println("Choose your race:")
        races.forEachIndexed { index, race ->
            println("${index}: ${race.name}")
        }
        val selectedRace = scanner.nextInt()

        // End function if choosen race is invalid
        if(selectedRace > races.size || selectedRace < 0) {
            println("Invalid race index selected")
            return null;
        }

        setRace(player, races[selectedRace])

        // Abilities
        print("Current player abilities:")


        return player
    }

    /**
     * Set the race and the abilities modifiers
     * */
    fun setRace(player: Player, race: Race) {
        for (modifier in race.modifiers) {
            player.abilities[modifier.key] = (player.abilities[modifier.key] ?: 0) + modifier.value
        }

        player.race = race
    }
}