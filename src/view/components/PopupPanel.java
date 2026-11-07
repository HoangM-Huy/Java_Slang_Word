package view.components;

public class PopupPanel {
    private String title;
    private String message;

    public PopupPanel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void createPopup() {
        javax.swing.JOptionPane.showMessageDialog(null, message, title, javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}
