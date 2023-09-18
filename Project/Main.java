package Project;


import javax.swing.SwingUtilities;

import Project.File.*;

class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new POSGUI();
            }
        });
    }
}
