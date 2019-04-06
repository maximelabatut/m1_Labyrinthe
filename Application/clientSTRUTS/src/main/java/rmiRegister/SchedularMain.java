package rmiRegister;

import java.util.Date;
import java.util.TimerTask;

public class SchedularMain extends TimerTask {

    Date current;
    int cnt = 0;
    boolean fini=false;

    @Override
    public void run() {
        if(!fini) {
            current = new Date();
            System.out.println("REFRESH");
            cnt++;
            if (cnt == 1) {
                System.out.println("Tours terminé");
                fini = true;
            }
        }
    }
}
