import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuxiliaryWindow extends JFrame {

    public static boolean easy;
    public static boolean medium;
    public static boolean difficult;

    private final static int MENU_HEIGHT = 20;

    AuxiliaryWindow(){
        setTitle("Choose level of difficulty!");
        setSize(320,345);
        //setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton easyButton =  new JRadioButton("Easy", false);
        JRadioButton mediumButton =  new JRadioButton("Medium", true);
        JRadioButton difficultButton =  new JRadioButton("Difficult", false);
        buttonGroup.add(easyButton);
        buttonGroup.add(mediumButton);
        buttonGroup.add(difficultButton);

        panel.setLayout(null);
        panel.add(easyButton);
        panel.add(mediumButton);
        panel.add(difficultButton);

        easyButton.setBounds(100,15,180,40);
        mediumButton.setBounds(100,65,180,40);
        difficultButton.setBounds(100,115,180,40);

        JButton continueBtn = new JButton("Continue!");
        continueBtn.setBounds(55,180,160,80);

        panel.add(continueBtn);

        /////////////////////////////////menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(100,MENU_HEIGHT));

        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");

        JMenuItem menuItemExit = new JMenuItem("Exit");
        JMenuItem menuItemAbout = new JMenuItem("About us");

        menuFile.add(menuItemExit);
        menuHelp.add(menuItemAbout);

        menuBar.add(menuFile);
        menuBar.add(menuHelp);

        super.setJMenuBar(menuBar);

        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameAbout = new JFrame("About us");
                frameAbout.setSize(400,200);
                JPanel panel1 = new JPanel();
                panel1.setLayout(null);

                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setBounds(40,10,300,140);
                textArea.append("\tDeveloper: Lukyanchik Ivan\n\n");
                textArea.append("\tGroup: 751004\n\n");
                textArea.append("\tSoftware: Net Tic Tac Toe 2019\n");
                textArea.append("--------------------------------------------------------------------------\n\n");
                textArea.append("               BSUIR, POIT, 2019. All rights reserved. \n");

                panel1.add(textArea);

                frameAbout.add(panel1);
                frameAbout.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frameAbout.setResizable(false);
                frameAbout.setLocationRelativeTo(AuxiliaryWindow.this);
                frameAbout.setVisible(true);
            }
        });
                ////////////////////end menu

        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuxiliaryWindow.this.dispose();
                MainWindow mainWindow = new MainWindow();
            }
        });

        this.add(panel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        if (easyButton.isSelected()) easy = true;
        if (mediumButton.isSelected()) medium = true;
        if (difficultButton.isSelected()) difficult = true;
    }
}
