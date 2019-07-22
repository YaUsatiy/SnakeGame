import javax.swing.*;

public class MainWindow extends JFrame {

    MainWindow(){
        if (AuxiliaryWindow.easy)
            setTitle("The Snake : easy");
        else if (AuxiliaryWindow.medium)
            setTitle("The Snake : medium");
        else
            setTitle("The Snake : difficult");
        setSize(320,345);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new GamePanel());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        AuxiliaryWindow auxiliaryWindow = new AuxiliaryWindow();
    }
}

