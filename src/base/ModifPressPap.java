package base;

/**
 *
 * @author Maxime
 */
import java.awt.datatransfer.*;
import java.awt.*;
import java.io.*;

/**
 * 
 * @author Maxime BLAISE
 */
public final class ModifPressPap implements ClipboardOwner {
    /*public static void main (String[] args) {
     ModifPressPap mpp = new ModifPressPap();
     System.out.println("Contenu actuel du presse-papiers : " + mpp.getClipboardContents() );
     mpp.setClipboardContents("Modification du presse-papiers par Java");
     System.out.println("Nouveau contenu du presse-papiers : " + mpp.getClipboardContents() );
     }*/


    @Override
    public void lostOwnership(Clipboard cb, Transferable t) {
        System.out.println("Contenu modifié");
    }

    public void setClipboardContents(String s) {
        StringSelection ss = new StringSelection(s);
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(ss, this);
    }

    public String getClipboardContents() {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

        try {
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String text = (String) t.getTransferData(DataFlavor.stringFlavor);
                return text;
            }
        } catch (UnsupportedFlavorException | IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
