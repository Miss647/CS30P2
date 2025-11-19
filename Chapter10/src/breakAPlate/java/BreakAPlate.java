package breakAPlate.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class BreakAPlate extends JFrame implements ActionListener {

    private static final String CMD_PLAY = "Play";
    private static final String CMD_PLAY_AGAIN = "Play Again";

    private final GameBooth game = new GameBooth();

    private JLabel platesImage;
    private JButton playButton;
    private JLabel prizeLabel;

    public BreakAPlate() {
        super("BreakAPlate");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Main container with vertical layout and padding
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        content.setBackground(Color.white);

        // Start image shows three unbroken plates
        platesImage = new JLabel(loadIcon("plates.gif"));
        platesImage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        platesImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        content.add(platesImage);

        // Play button
        playButton = new JButton(CMD_PLAY);
        playButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        playButton.setActionCommand(CMD_PLAY);
        playButton.addActionListener(this);
        content.add(playButton);

        // Prize label for text and prize icon
        prizeLabel = new JLabel(" ");
        prizeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        prizeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        content.add(prizeLabel);

        setContentPane(content);
        pack();
        setLocationRelativeTo(null);
    }

    private ImageIcon loadIcon(String name) {
        // Loads a resource from the same package as this class
        java.net.URL url = getClass().getResource(name);
        if (url == null) {
            // Fallback placeholder if an image is missing
            return new ImageIcon(new BufferedImage(220, 90, BufferedImage.TYPE_INT_ARGB));
        }
        return new ImageIcon(url);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (CMD_PLAY.equals(cmd)) {
            String prize = game.start();

            if (game.lastGameWon()) {
                platesImage.setIcon(loadIcon("plates_all_broken.gif"));
                prizeLabel.setIcon(loadIcon("tiger_plush.gif"));
                prizeLabel.setText(" You win: " + prize);
            } else {
                platesImage.setIcon(loadIcon("plates_two_broken.gif"));
                prizeLabel.setIcon(loadIcon("sticker.gif"));
                prizeLabel.setText(" You win: " + prize);
            }

            playButton.setText(CMD_PLAY_AGAIN);
            playButton.setActionCommand(CMD_PLAY_AGAIN);

        } else if (CMD_PLAY_AGAIN.equals(cmd)) {
            platesImage.setIcon(loadIcon("plates.gif"));
            prizeLabel.setText(" ");
            prizeLabel.setIcon(null);

            playButton.setText(CMD_PLAY);
            playButton.setActionCommand(CMD_PLAY);
        }
    }

    public static void main(String[] args) {
        // Use system look and feel and start on the event thread
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignore) {}
        SwingUtilities.invokeLater(() -> new BreakAPlate().setVisible(true));
    }
}

class GameBooth {
    private static final String FIRST_PRIZE = "tiger plush";
    private static final String CONSOLATION_PRIZE = "sticker";

    private final java.util.Random rng = new java.util.Random();
    private int lastBrokenCount = 0;

    public String start() {
        int broken = 0;
        for (int i = 0; i < 3; i++) {
            if (rng.nextInt(2) == 1) {
                broken++;
            }
        }
        lastBrokenCount = broken;
        if (broken == 3) {
            return FIRST_PRIZE;
        }
        return CONSOLATION_PRIZE;
    }

    public boolean lastGameWon() {
        return lastBrokenCount == 3;
    }
}
