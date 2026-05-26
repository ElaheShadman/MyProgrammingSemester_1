board = [""] * 9

def print_board():
    print(board[0], " |", board[1], " |", board[2])
    print("---+----+---")
    print(board[3], " |", board[4], " |", board[5])
    print("---+----+--")
    print(board[6], " |", board[7], " |", board[8])

def wins(player):
    wins = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],
        [0, 3, 6], [1, 4, 7], [2, 5, 8],
        [0, 4, 8], [2, 4, 6]
    ]
    for line in wins:
        if board[line[0]] == board[line[1]] == board[line[2]] == player:
            return True

def play():
    turn = "X"
    for i in range(9):
        print_board()
        move = int(input(f"Player {turn}, enter your move (0-8): "))

        if board[move] == "":
            board[move] = turn
            if wins(turn):
                print_board()
                print(f"Player {turn} wins!")
                return
            turn = "O" if turn == "X" else "X"
        else:
            print("Invalid move. Try again.")

    print_board()
play()

