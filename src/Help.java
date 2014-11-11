import java.awt.*;
import javax.swing.*;

public class Help extends JFrame{
	private static final long serialVersionUID = 1L;
	JLabel jl = new JLabel("<html><head><font size=\"5\">Hilfe</font><hr></head><body>" +
			"<font size=\"4\"> Eine Rasterdatei erstellen</font>" +
			"<br><br>- Wählen sie die Registerkarte \"createRAS\" aus." +
			"<br><br>- Mit dem DropDownMenu Extension wählen sie<br>   die zu benutzenden Dateien aus." +
			"<br><br>- Als nächstes wählen sie ein Verzeichnis,<br> in dem sich die zu lesenden Dateien befinden,<br> mit dem Button \"Browse\"." +
			"<br><br>- Nachdem sie ihr Verzeichnis gewählt haben,<br> erscheint eine Tabelle mit den entsprechenden<br> Werten der einzelnen Dateien." +
			"<br><br>- In den Nachfolgenden Feldern können sie die<br> Kriterien für die zu erstellende Datei definieren." +
			"<br>  (Die Kriterien müssen innerhalb der<br> Extremwerte aus den zu lesenden<br> Dateien liegen)" +
			"<br><br>-minY/X: Startwert der neuen Rasterdatei" +
			"<br><br>-abstandY/X: Startwert+Abstand ergeben <br>den Zielwert" +
			"<br><br>-rasY/X: Gibt die Rasterweite<br> zwischen 2 Punkten an" +
			"<br><br>- Wenn alle Werte Felder ausgefüllt<br> sind, betätigen sie den Button \"Go\"" +
			"<br><br>- Wählen sie einen Namen für die zu <br>erstellende Rasterdatei" +
			"<br><br>- Sollten sie kein Verzeichnis RAS in<br> dem selben Verzeichnis,<br> in dem sich die Anwendung befindet,<br> haben, wird dies automatisch erstellt." +
			"<br><br>" +
			"<font size=\"4\"> Konvertierung (*.ras zu *.wrl)</font>" +
			"<br><br>- Wählen sie die Registerkarte \"convert\" aus." +
			"<br><br>- mit Browse wählen sie nun die zu <br>konvertierende Rasterdatei aus." +
			"<br><br>- Betätigen sie den Button \"to *.wrl\"." +
			"<br><br>- Wählen sie einen Namen für die zu <br>erstellende Datei." +
			"<br><br>- Sollten sie kein Verzeichnis WRL in<br> dem selben Verzeichnis,<br> in dem sich die Anwendung befindet,<br> haben, wird dies automatisch erstellt." +
			"<br><br>- Die Konsolenausgabe gibt den<br> Fortschritt der Konvertierung an." +
			"<br><br>- Sobald die Datei erstellt wurde,<br> erhalten sie eine Bestätigungsmeldung." +
			"<br><br>" +
			"<font size=\"4\"> Konvertierung (*.ras zu *.scr)</font>" +
			"<br>- Wählen sie die Registerkarte \"convert\" aus." +
			"<br><br>- mit Browse wählen sie nun die zu <br>konvertierende Rasterdatei aus." +
			"<br><br>- Betätigen sie den Button \"to *.scr\"." +
			"<br><br>- Wählen sie einen Namen für die zu <br>erstellende Datei." +
			"<br><br>- Sollten sie kein Verzeichnis SCR in<br> dem selben Verzeichnis,<br> in dem sich die Anwendung befindet,<br> haben, wird dies automatisch erstellt." +
			"<br><br>- Die Konsolenausgabe gibt den<br> Fortschritt der Konvertierung an." +
			"<br><br>- Sobald die Datei erstellt wurde,<br> erhalten sie eine Bestätigungsmeldung." +
			"</body></html>");
		JScrollPane scroll= new JScrollPane();
	public Help() {
		this.setResizable(false);
		this.setTitle("Help");
	    //this.setLocation(this.getX()+200, this.getY());
		this.setPreferredSize(new Dimension(300,400));
		this.setLayout(new BorderLayout());
		add(scroll);
		scroll.setViewportView(jl);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.pack();
	}
}
