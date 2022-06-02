package model;

public class Caregiver extends Person {
    private long cid;
    private String telephoneNr;

    /**
     * constructs a caregiver from the given params.
     * @param firstName
     * @param surname
     * @param telephoneNr
     */
    public Caregiver (String firstName, String surname, String telephoneNr){
        super(firstName, surname);
        this.telephoneNr = telephoneNr;
    }

    /**
     * constructs a caregiver from the given params.
     * @param firstName
     * @param surname
     * @param telephoneNr
     */
    public Caregiver (long cid, String firstName, String surname, String telephoneNr){
        super(firstName, surname);
        this.cid = cid;
        this.telephoneNr = telephoneNr;
    }

    /**
     * @return caregiver id
     */
    public long getCid() {
        return cid;
    }

    public String getTelephoneNr(){
        return telephoneNr;
    }

    public void setTelephoneNr(String telephoneNr){
        this.telephoneNr = telephoneNr;
    }

    /**
     * @return string-representation of the caregiver
     */
    public String toString() {
        return "Caregiver" + "\nID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelephone-Nr.: " + this.telephoneNr +
                "\n";
    }
}
