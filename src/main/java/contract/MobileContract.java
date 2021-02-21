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
}
