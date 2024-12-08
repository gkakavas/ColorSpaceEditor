package duth.dip.cse.action;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

public class MenuActionListener implements ActionListener {

    private final Runnable actionToBePerformed;

    public MenuActionListener(Runnable runnable) {
        this.actionToBePerformed = runnable;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(!(actionEvent.getSource() instanceof JButton button)){
            CompletableFuture.runAsync(actionToBePerformed);
            return;
        }
        button.setEnabled(false);
        CompletableFuture
                .runAsync(actionToBePerformed)
                .whenComplete((voi, throwable) ->{
                    if(throwable != null){
                        throw new RuntimeException();//ActionFailedException();
                    }
                    button.setEnabled(true);
                });
    }
}
