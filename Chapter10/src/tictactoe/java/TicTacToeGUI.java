package tictactoe.java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private String[][] board = new String[3][3];
    private String currentPlayer = "X";
    private JLabel status = new JLabel("Player X turn");

    public TicTacToeGUI() {
        super("Tic Tac Toe");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridLayout(3, 3));
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c] = new JButton("");
                buttons[r][c].setFont(f);
                buttons[r][c].addActionListener(this);
                grid.add(buttons[r][c]);
                board[r][c] = "";
            }
        }

        add(grid, BorderLayout.CENTER);
        status.setHorizontalAlignment(SwingConstants.CENTER);
        add(status, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        // Find row and column
        int row = -1, col = -1;
        outer: for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (buttons[r][c] == b) {
                    row = r; col = c; break outer;
                }
            }
        }

        // Ignore click if cell taken or game ended
        if (!board[row][col].isEmpty() || isGameOver()) return;

        board[row][col] = currentPlayer;
        b.setText(currentPlayer);

        String winner = checkWinner();
        if (!winner.isEmpty()) {
            status.setText("Winner: " + winner);
            disableBoard();
        } else if (isFull()) {
            status.setText("Draw game");
        } else {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            status.setText("Player " + currentPlayer + " turn");
        }
    }

    private void disableBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setEnabled(false);
            }
        }
    }

    private boolean isGameOver() {
        return !checkWinner().isEmpty() || isFull();
    }

    private boolean isFull() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c].isEmpty()) return false;
        return true;
    }

    private String checkWinner() {
        // Rows
        for (int r = 0; r < 3; r++) {
            if (!board[r][0].isEmpty() && board[r][0].equals(board[r][1]) && board[r][0].equals(board[r][2]))
                return board[r][0];
        }
        // Columns
        for (int c = 0; c < 3; c++) {
            if (!board[0][c].isEmpty() && board[0][c].equals(board[1][c]) && board[0][c].equals(board[2][c]))
                return board[0][c];
        }
        // Diagonals
        if (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]))
            return board[0][0];
        if (!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]))
            return board[0][2];

        return "";
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignore) {}
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }
}
