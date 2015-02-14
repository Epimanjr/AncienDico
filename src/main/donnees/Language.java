/*
 * Representing a language.
 */
package main.donnees;

/**
 *
 * @author Maxime BLAISE
 */
public class Language {


    /**
     * Abbreviation (example for English : en).
     */
    private String abbr;

    /**
     * Name, for example : English.
     */
    private String name;

    /**
     * Constructor of a language.
     *
     * @param abbr Abbreviation.
     * @param name Complete name.
     */
    public Language(String name, String abbr) {
        this.abbr = abbr;
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
