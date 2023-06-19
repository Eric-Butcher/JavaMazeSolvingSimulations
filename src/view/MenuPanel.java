package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(int menuLength, int menuHeight)
    {
        this.setBackground(Color.BLUE);
        this.setVisible(true);
//        this.setBounds(0, 0, menuLength, menuHeight);
        this.setPreferredSize(new Dimension(menuLength, menuHeight));

    }
}
