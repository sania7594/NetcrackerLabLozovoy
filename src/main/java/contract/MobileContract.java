package contract;

/**
 * Mobile phone contract
 *
 * @author Alex Lozovoy
 */

public class MobileContract extends Contract {
    private int minutes;
    private int messages;
    private int trafic;

    /**
     * @param id             ID
     * @param startDate      Contract start date
     * @param endDate        The end date of the contract
     * @param numberContract Number contract
     * @param client         Client
     * @param minutes        minutes
     * @param messages       messages
     * @param trafic         trafic
     */
    public MobileContract(int id, long startDate, long endDate, int numberContract, Client client, int minutes, int messages, int trafic) {
        super(id, startDate, endDate, numberContract, client);
        this.minutes = minutes;
        this.messages = messages;
        this.trafic = trafic;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public int getTrafic() {
        return trafic;
    }

    public void setTrafic(int trafic) {
        this.trafic = trafic;
    }
}
