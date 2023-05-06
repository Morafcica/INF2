import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OsobaEdit {
    private JPanel panel1;
    private JButton zrusitButton;
    private JButton ulozitButton;
    private JTextField priezviskoTextField;
    private JTextField menoTextField;
    private boolean klikolNaUloz;

    public OsobaEdit(Osoba osoba, JFrame frame) {
        var okno = new JDialog(frame, "dsfsdf", true);
        okno.setContentPane(this.panel1);


        this.zrusitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                okno.dispose();
            }
        });

        this.ulozitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                osoba.setMeno(OsobaEdit.this.menoTextField.getText());
                osoba.setPriezvisko(OsobaEdit.this.priezviskoTextField.getText());
                OsobaEdit.this.klikolNaUloz = true;
                okno.dispose();
            }
        });

        this.menoTextField.setText(osoba.getMeno());
        this.priezviskoTextField.setText(osoba.getPriezvisko());
        okno.pack();
        okno.setVisible(true);

    }

    public boolean isKlikolNaUloz() {
        return klikolNaUloz;
    }
}
