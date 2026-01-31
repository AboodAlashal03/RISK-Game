// JavaScript Code for Risk Game Simulation
let currentTurnIndex = 0;
let gamesPlayed = 0;
// Player objects
const players = [
    {
        name: "Player 1",
        strategy: "Aggressive",
        totalTurns: 0,
        wins: 0,
        losses: 0,
        attacks: 0,
        successfulAttacks: 0,
        failedAttacks: 0,
        defenses: 0,
        successfulDefenses: 0,
        failedDefenses: 0,
        territoriesConquered: 14,
        territoriesLost: 0,
        active: true
    },
    {
        name: "Player 2",
        strategy: "Pacifist",
        totalTurns: 0,
        wins: 0,
        losses: 0,
        attacks: 0,
        successfulAttacks: 0,
        failedAttacks: 0,
        defenses: 0,
        successfulDefenses: 0,
        failedDefenses: 0,
        territoriesConquered: 14,
        territoriesLost: 0,
        active: true
    },
    {
        name: "Player 3",
        strategy: "Opportunist",
        totalTurns: 0,
        wins: 0,
        losses: 0,
        attacks: 0,
        successfulAttacks: 0,
        failedAttacks: 0,
        defenses: 0,
        successfulDefenses: 0,
        failedDefenses: 0,
        territoriesConquered: 14,
        territoriesLost: 0,
        active: true
    }
];

// Function to update player stats in the DOM
function updatePlayerStats() {
    players.forEach((player, index) => {
        document.getElementById(`player${index + 1}-turns`).innerText = player.totalTurns;
        document.getElementById(`player${index + 1}-wins`).innerText = player.wins;
        document.getElementById(`player${index + 1}-losses`).innerText = player.losses;
        document.getElementById(`player${index + 1}-attacks`).innerText = player.attacks;
        document.getElementById(`player${index + 1}-successful-attacks`).innerText = player.successfulAttacks;
        document.getElementById(`player${index + 1}-failed-attacks`).innerText = player.failedAttacks;
        document.getElementById(`player${index + 1}-defenses`).innerText = player.defenses;
        document.getElementById(`player${index + 1}-successful-defenses`).innerText = player.successfulDefenses;
        document.getElementById(`player${index + 1}-failed-defenses`).innerText = player.failedDefenses;
        document.getElementById(`player${index + 1}-territories-conquered`).innerText = player.territoriesConquered;
        document.getElementById(`player${index + 1}-territories-lost`).innerText = player.territoriesLost;
    });
    document.getElementById("games-played-counter").innerText = gamesPlayed;
}

// Function to simulate a turn
function simulateTurn() {
    if (players.filter(player => player.active).length < 2) {
        startNewGame();
        return;
    }

    const attackerIndex = currentTurnIndex;

    // Ensure the current player is active
    if (!players[attackerIndex].active) {
        currentTurnIndex = (currentTurnIndex + 1) % players.length;
        return simulateTurn();
    }

    let defenderIndex;
    do {
        defenderIndex = Math.floor(Math.random() * players.length);
    } while (defenderIndex === attackerIndex || !players[defenderIndex].active);

    const attacker = players[attackerIndex];
    const defender = players[defenderIndex];

    // Determine success based on strategy power
    let attackSuccessChance;
    switch (attacker.strategy) {
        case "Opportunist":
            attackSuccessChance = 0.9; // 90% chance of success
            break;
        case "Aggressive":
            attackSuccessChance = 0.5; // 50% chance of success
            break;
        case "Pacifist":
            attackSuccessChance = 0.2; // 20% chance of success
            break;
        default:
            attackSuccessChance = 0.5; // Default 50%
    }

    // Determine if the attack is successful
    const attackSuccessful = Math.random() < attackSuccessChance;

    // Update stats based on attack result
    attacker.totalTurns++;
    attacker.attacks++;
    defender.defenses++;

    if (attackSuccessful) {
        attacker.successfulAttacks++;
        attacker.territoriesConquered++;
        defender.failedDefenses++;
        defender.territoriesLost++;

        // Check if defender loses all territories
        if (defender.territoriesLost - defender.territoriesConquered === 0) {
            defender.active = false;
            defender.losses++;
        }
    } else {
        attacker.failedAttacks++;
        defender.successfulDefenses++;
    }

    // Check if game is over and Player 3 wins
    const activePlayers = players.filter(player => player.active);
    if (activePlayers.length === 1 && activePlayers[0].name === "Player 3") {
        players.forEach(player => {
        });
        players[2].wins++; // Player 3 wins
        startNewGame();
    }

    // Update the DOM with new stats
    updatePlayerStats();

    // Move to the next player's turn
    currentTurnIndex = (currentTurnIndex + 1) % players.length;
}

// Function to start a new game without resetting stats
function startNewGame() {
    gamesPlayed++;
    if (gamesPlayed >= 60) {
        console.log("Simulation complete: 60 games played.");
        return; // Stop if we have completed the required number of games
    }

    players.forEach(player => {
        player.territoriesConquered += 14; // Increment territories conquered by 14 for each player still active
        player.active = true; // Reactivate all players for the next game
    });
    currentTurnIndex = 0;
    updatePlayerStats(); // Update player stats after starting a new game
}

// Function to progressively simulate games
async function simulateGames(count) {
    for (let gameCounter = 0; gameCounter < count && gamesPlayed < count; gameCounter++) {
        let gameOver = false;
        while (!gameOver) {
            simulateTurn();
            gameOver = players.filter(player => player.active).length === 1;

            // Update UI after every few turns to keep UI responsive
            if (gameCounter % 5 === 0) {
                await new Promise(resolve => setTimeout(resolve, 0));
                updatePlayerStats();
            }
        }

        // After every game update stats and yield control
        updatePlayerStats();
        await new Promise(resolve => setTimeout(resolve, 50));
    }
}


// Function to reset all player stats
function resetGame() {
    players.forEach(player => {
        player.totalTurns = 0;
        player.wins = 0;
        player.losses = 0;
        player.attacks = 0;
        player.successfulAttacks = 0;
        player.failedAttacks = 0;
        player.defenses = 0;
        player.successfulDefenses = 0;
        player.failedDefenses = 0;
        player.territoriesConquered = 14; // Reset initial territories
        player.territoriesLost = 0;
        player.active = true;
    });
    gamesPlayed = 0;
    currentTurnIndex = 0;
    updatePlayerStats();
}

// Event listeners for buttons
document.getElementById("next-turn").addEventListener("click", simulateTurn);
document.getElementById("simulate-60-games").addEventListener("click", () => simulateGames(60));
document.getElementById("restart-game").addEventListener("click", resetGame);
