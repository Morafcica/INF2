import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Okienko {
    private final JFrame okno;
    private final DefaultListModel<Osoba> model;
    private JPanel panel;
    private JList osobyList;
    private JButton pridajButton;
    private JButton Zmaz;
    private boolean klikolNaUloz;
    public Okienko() {
        this.okno = new JFrame();
        this.okno.setContentPane(this.panel);

        this.model = new DefaultListModel<Osoba>();
        model.addElement(new Osoba("Ferko", "Magec"));
        model.addElement(new Osoba("Jozef", "Lopa"));
        model.addElement(new Osoba("Ferko", "Igorik"));
        this.osobyList.setModel(model);

        this.osobyList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    var n = new OsobaEdit((Osoba) Okienko.this.osobyList.getSelectedValue(), Okienko.this.okno);
                    System.out.println("Vyber: " + ((Osoba) Okienko.this.osobyList.getSelectedValue()).toString());
                }

                Okienko.this.okno.revalidate();
            }
        });

        this.pridajButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var o = new Osoba("Novy", "novy");
                var n = new OsobaEdit(o, Okienko.this.okno);

                if (n.isKlikolNaUloz()) {
                    Okienko.this.model.addElement(o);
                    Okienko.this.osobyList.revalidate();
                }
            }
        });

        this.Zmaz.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var o = ((Osoba) Okienko.this.osobyList.getSelectedValue());
                Okienko.this.model.removeElement(o);
                Okienko.this.osobyList.revalidate();
            }
        });


        this.okno.pack();
        this.okno.setVisible(true);
    }
}
