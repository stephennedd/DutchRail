package utils.visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text); // Call the constructor of the JButton class

        // Set the appearance and behavior
        setForeground(Color.WHITE);
        setBackground(new Color(0x0063D3));
        Font customFont = new Font("Arial", Font.BOLD, 14);
        setFont(customFont);

        // Add rounded corners
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        // Add a hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(0x0056BB));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(0x0063D3));
            }
        });
    }
}
