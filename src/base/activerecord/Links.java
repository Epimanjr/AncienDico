package base.activerecord;

import java.util.ArrayList;

/**
 * Manage a list of Links.
 */
public class Links {

    /**
     * The name of this list.
     */
    private String name;

    /**
     * List of links.
     */
    private ArrayList<Liaison> listLinks;

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
    public Links(String name, ArrayList<Liaison> list) {
        // Init fields
        this.name = name;
        this.listLinks = list;
    }

    /**
     * Print the list.
     *
     * @return a message (type String)
     */
    public String printList() {
        // Init
        String res = "";
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

    public ArrayList<Liaison> getListLinks() {
        return listLinks;
    }

    public void setListLinks(ArrayList<Liaison> listLinks) {
        this.listLinks = listLinks;
    }

    
    
}
