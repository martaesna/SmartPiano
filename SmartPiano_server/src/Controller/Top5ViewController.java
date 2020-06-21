package Controller;

import View.CançonsView;
import View.MainView;
import View.Top5View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Top5ViewController implements ActionListener {
    private Top5View v;

    public Top5ViewController(Top5View v) {
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Tornar")) {
            System.out.println("has apretat tornar");
            v.setVisible(false);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainView v = new MainView();
                    MainViewController controller = new MainViewController(v);
                    v.mainController(controller);
                    v.setVisible(true);
                }
            });
        }
    }
}
