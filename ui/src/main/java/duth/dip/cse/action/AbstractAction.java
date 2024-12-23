package duth.dip.cse.action;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public abstract class AbstractAction implements ActionListener {

    protected final Executor executor;

    public AbstractAction() {
        this.executor = SwingUtilities::invokeLater;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CompletableFuture.runAsync(() -> onActionEvent(actionEvent), executor)
                .whenComplete((result, throwable) -> {
                    afterActionEvent(actionEvent);
                    handleException(throwable);
                });
    }

    protected void handleException(Throwable throwable){
        if (throwable != null) {
            JOptionPane.showMessageDialog(null,
                    "An error occurred: " + throwable.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected abstract void onActionEvent(ActionEvent event);

    protected abstract void afterActionEvent(ActionEvent event);
}
