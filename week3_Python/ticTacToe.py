# TicTacToe Game
import random

# reusables: ["", "", ""]
opponent_symbol = "X"
computer_symbol = "O"
vacant_symbol = "-"

diagonal_1 = ["1", "5", "9"]
diagonal_2 = ["3", "5", "7"]

row_1 = ["1", "2", "3"]
row_2 = ["4", "5", "6"]
row_3 = ["7", "8", "9"]

column_1 = ["1", "4", "7"]
column_2 = ["2", "5", "8"]
column_3 = ["3", "6", "9"]

diagonals = [diagonal_1, diagonal_2]
columns = [column_1, column_2, column_3]
rows = [row_1, row_2, row_3]

tic_tac_toe_grid = {
    "1": vacant_symbol,
    "2": vacant_symbol,
    "3": vacant_symbol,
    "4": vacant_symbol,
    "5": vacant_symbol,
    "6": vacant_symbol,
    "7": vacant_symbol,
    "8": vacant_symbol,
    "9": vacant_symbol
}

moves = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
moves_set = set(moves)
available_moves = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
played_moves = []
winning_combinations = [*rows, *columns, *diagonals]
blocked_winning_combinations = []

# UI options
welcome_screen_options = ["1", "2", "3", "4", "5"]
welcome_message = "\n<<<<<<<<<<<<<<<<<<<TICTACTOE>>>>>>>>>>>>>>>>>>> \n"
welcome_prompt = "Make a selection (Enter the selction number) \n"

welcome_options = """
    1. Start game
    2. Know the rules
    3. See previous scores
    4. Learn how the game works
    5. End program
    
    # Press Ctrl + C to end the program at any time \n
"""

print(welcome_message)
print(welcome_prompt)
print(welcome_options)

selected_welcome_screen_option = input()


while selected_welcome_screen_option not in welcome_screen_options:
    print("Invalid selection. \n")
    print("[TRY AGAIN] Make a selection. Valid options are 1, 2, 3, 4, 5 \n")

    selected_welcome_screen_option = input()

# Utility variables

#demo
demo_grid = {
    "1": "1",
    "2": "2",
    "3": "3",
    "4": "4",
    "5": "5",
    "6": "6",
    "7": "7",
    "8": "8",
    "9": "9",
}

# Utility methods

#demo
def display_tic_tac_toe_grid_demo(demo_grid_dictionary):
    print("\n\n\n[Demo Grid] The grid has 9 positions. Select the position you want to make a move on using its correcponding number\n\n\n")
    row_line_demo = ""
    for value in demo_grid_dictionary.values():
        row_line_demo += f"   {value}   "
        if value == "3" or value == "6" or value == "9":
            print(row_line_demo)
            print("\n")
            row_line_demo = ""

    print("[TL;DR] 9 positions from 1 to 9. Select your target position using the numbers \n\n\n")


def display_tic_tac_toe_grid(grid_dictionary):
    print("[TicTacToe Grid]\n\n\n")
    row_line_demo = ""
    for key, value in grid_dictionary.items():
        row_line_demo += f"   {value}   "
        if key == "3" or key == "6" or key == "9":
            print(row_line_demo)
            print("\n")
            row_line_demo = ""

def get_user_move():
    user_move_selection = input()

    while user_move_selection not in moves:
        print("Invalid selection. \n")
        print("[TRY AGAIN] Make a selection. Valid options are 1, 2, 3, 4, 5 \n")
        user_move_selection = input()

    while user_move_selection in played_moves:
        print("This move has already been played. Select another move position \n")
        user_move_selection = input()

    return user_move_selection

def handle_played_move(move):
    # remove move from available pool of moves
    global available_moves, played_moves
    available_moves = [x for x in available_moves if x != move]

    # register move as played
    played_moves.append(move)

def update_grid_with_move(move, symbol, grid):
    global moves
    grid[move] = symbol
    moves = [x for x in moves if x != move] # removing the move from the moves list
    

def update_grid_with_opponent_move(move):
    update_grid_with_move(move, opponent_symbol, tic_tac_toe_grid)

def update_grid_with_computer_move(move):
    update_grid_with_move(move, computer_symbol, tic_tac_toe_grid)

def get_activated_combinations(**kwargs):
    """
    Determine which move combinations are 'activated' or can potential be played for a win
    
    :param moves_list: Opponent or computer moves
    :type moves_list: list of str
    :param combinations_list: List of combinations to evaluate. Defaults to []
    :type combinations_list: list[list of str]
    :param similarity_count: Number of matching moves. Defaults to 1
    :type similarity_count: int
    :param mode: Matching mode - 'exact' for exact matches or 'at_least'. Defaults to 'exact'
    :type mode: str
    :return: A 2D list
    :rtype: list[list of str]
    """

    # INPUTS: moves_list, combinations_list, similarity_count, mode
    moves_list = kwargs.get('moves_list', [])
    combinations_list = kwargs.get('combinations_list', [])
    similarity_count = kwargs.get('similarity_count', 1)
    mode = kwargs.get('mode', 'exact')

    set_a = set(moves_list)
    results = []
    
    for sublist in combinations_list:
        common_count = len(set_a.intersection(set(sublist)))
        
        if mode == 'exact':
            if common_count == similarity_count:
                results.append(sublist)
        else:
            if common_count >= similarity_count:
                results.append(sublist)
                
    return results
    

def get_computer_move():
    # Processing rules (Working on refinement before coding; too long and need to be broken into precise statements that can be translated to code.)

    # at the start of the game, we get the current state of the grid after the user makes the first move
    current_grid_state = tic_tac_toe_grid

    # Objective: We should choose a random winning combination from the diagonals set with preference to the center
    opponent_moves = [k for k, v in current_grid_state.items() if v == opponent_symbol]
    computer_moves = [k for k, v in current_grid_state.items() if v == computer_symbol]

    # Check if no computer move has been made
    if not computer_moves:
        random_move = diagonals[random.randint(0, len(diagonals) - 1)][random.randint(0, len(diagonals[0]) - 1)]
        return random_move
    else:
        # computer move was previously made
        # compute how to return a move
        # determine which combinations the user has activated
        opponent_combinations_1_move = get_activated_combinations(moves_list=opponent_moves, combinations_list=winning_combinations, similarity_count=1, mode='exact')
        # determine which combinations the computer has activated
        computer_combinations_1_move = get_activated_combinations(moves_list=computer_moves, combinations_list=winning_combinations, similarity_count=1, mode='exact')
        # filter off shared combinations since they can never lead to a win i.e combination has both a computer move and opponent move
        # therefore, the remaining combinations are those that the opponent can complete
        # determine how many moves the opponent has to make to complete the combination
        # rank the combinations according to the moves left to complete
        
        # determine how the moves to completion we have for the computer activated combinations
        # rank the combinations by moves to completion
        # determine which combination is possible given the available moves
        # determine if an available move can both lead the computer to a win while blocking the opponent move ie, shared move that blocks but is part of our winning combination
        
        # DETERMINING WHICH MOVE TO PLAY: BLOCKING OR ADVANCING, OR BOTH
        # determine if a blocking move must be made given the opponents move to completion
        # randomly select a move combination and return a move

    

    # after the user makes the next move, we determine the winning combinations available for us and those available for the user
    # Combinations where more than one element from opposing sides exist should be filtered off and not considered as candidates for selecting the next move from
    # We then get combinations_available_to_opponent and combinations_available_to_computer
    # We then determine, what are the moves_to_completion per combination per playing side
    # If we can make a move with a higher moves_to_completion than the user, then we make it, otherwise, we block the opponents combinations with a higher than or equal to move_to_completion because the opponent has the advantage of starting first and hence can finish first.
    # When we get a combination to block, we determine if the move can block other opponent combinations (one move to block the many other moves) if not, any blocking move will do.
    # 
    
    # capture current state of the tictactoe grid


    # Which winning combinations contain the user moves?
        # For each user move
            # get the winning combination that lists this move

        possible_combinations_with_opponent_moves = []
        possible_combinations_with_computer_moves = []

    for move in opponent_moves:
        possible_combinations_with_opponent_moves.append(x for x in winning_combinations if move in x)
    
    for move in computer_moves:
        possible_combinations_with_computer_moves.append(x for x in winning_combinations if move in x)
    



    # determine the available winning combinations after the user move


    # determine which combination to block

    # return the blocking move

    # calculate the next best move
    computed_move = "1"
    return computed_move


def start_game():
    display_tic_tac_toe_grid(tic_tac_toe_grid)
    opponent_move = get_user_move()
    update_grid_with_opponent_move(opponent_move)
    computer_move = get_computer_move()
    update_grid_with_computer_move(computer_move)
    

# Program core

display_tic_tac_toe_grid_demo(demo_grid)

while True:
    start_game()
