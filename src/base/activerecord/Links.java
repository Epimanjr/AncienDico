package base.activerecord;

import java.util.ArrayList;

/**
 * Manage a list of Links.
 */
public class Links {

    /**
     * The name of this list.
     */
    private final String name;

    /**
     * List of links.
     */
    private ArrayList<Link> listLinks;

    /**
     * Create a new list with a specific name.
     *
     * @param name .
     */
    public Links(String name) {
        // Init fields
        this.name = name;
        this.listLinks = new ArrayList<>();
    }

    /**
     * Create a new list of links with a specific list.
     *
     * @param name .
     * @param list .
     */
    public Links(String name, ArrayList<Link> list) {
        // Init fields
        this.name = name;
        this.listLinks = list;
    }

    /**
     * Print the list.
     *
     * @return a message (type String)
     */
    @Override
    public String toString() {
        // Init
        String res = "Print list called \"" + this.name + "\" : \n";
        // Loop
        for (Link l : this.listLinks) {
            res += l.toString() + "\n";
        }
        // Return the result
        return res;
    }

    /**
     * Get the name of the list.
     *
     * @return name type String
     */
    public String getName() {
        return this.name;
    }

    public ArrayList<Link> getListLinks() {
        return listLinks;
    }

    public void setListLinks(ArrayList<Link> listLinks) {
        this.listLinks = listLinks;
    }

    /**
     * Set information in all element of the list.
     */
    public void setInformation() {
        System.out.print("Set information in all list's elements ... ");
        for(Link l : this.listLinks) {
            l.setInformations();
        }
    }

}
