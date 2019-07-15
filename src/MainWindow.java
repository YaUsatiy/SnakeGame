import javax.swing.*;

public class MainWindow extends JFrame {

    MainWindow(){
        setTitle("Vanya's Snake 2019 v.1.1");
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

