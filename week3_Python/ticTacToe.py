def make_empty_board():
    return [""] * 9

def print_board(board):
    def cell(v):
        return v if v in ("X", "O") else " "
    print(f" {cell(board[0])} | {cell(board[1])} | {cell(board[2])} ")
    print("---+---+---")
    print(f" {cell(board[3])} | {cell(board[4])} | {cell(board[5])} ")
    print("---+---+---")
    print(f" {cell(board[6])} | {cell(board[7])} | {cell(board[8])} ")

def wins(board, player):
    win_lines = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],  # rows
        [0, 3, 6], [1, 4, 7], [2, 5, 8],  # cols
        [0, 4, 8], [2, 4, 6]              # diagonals
    ]
    for a, b, c in win_lines:
        if board[a] == board[b] == board[c] == player:
            return True
    return False

def is_full(board):
    return all(cell in ("X", "O") for cell in board)

def play():
    board = make_empty_board()
    turn = "X"

    while True:
        print_board(board)

        # Read a valid move without consuming the turn on invalid input
        move = None
        while True:
            try:
                raw = input(f"Player {turn}, enter your move (0-8): ").strip()
                move = int(raw)
                if not (0 <= move <= 8):
                    print("Out of range. Please enter a number from 0 to 8.")
                    continue
                if board[move] != "":
                    print("That square is taken. Choose another.")
                    continue
                break
            except ValueError:
                print("Invalid input. Please enter a number from 0 to 8.")

        # Apply the move
        board[move] = turn

        # Check for win
        if wins(board, turn):
            print_board(board)
            print(f"Player {turn} wins!")
            break

        # Check for draw
        if is_full(board):
            print_board(board)
            print("It's a draw!")
            break

        # Switch turns
        turn = "O" if turn == "X" else "X"

if __name__ == "__main__":
    play()
