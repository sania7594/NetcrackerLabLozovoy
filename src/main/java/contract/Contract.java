package contract;

/**
 * The class contract
 *
 * @author Alex Lozovoy
 */
public class Contract {
    private int id;
    private long startOfContract;
    private long endOfContract;
    private int numberContract;
    private Client client;

    /**
     * Constructor for creating a contract
     *
     * @param id              ID contract
     * @param startOfContract Contract start date
     * @param endOfContract   The end date of the contract
     * @param numberContract  Number contract
     * @param client          Client
     */
    public Contract(int id, long startOfContract, long endOfContract, int numberContract, Client client) {
        this.id = id;
        this.startOfContract = startOfContract;
        this.endOfContract = endOfContract;
        this.numberContract = numberContract;
        this.client = client;
    }

    /**
     * Returning the contract Id
     *
     * @return id contract
     */
    public int getId() {
        return id;
    }

    /**
     * Returning tContract start date
     *
     * @return start date contract
     */
    public long getStartOfContract() {
        return startOfContract;
    }

    /**
     * Returning end date of the contract
     *
     * @return start date contract
     */
    public long getEndOfContract() {
        return endOfContract;
    }

    /**
     * Returning Number contract
     *
     * @return start date contract
     */
    public int getNumberContract() {
        return numberContract;
    }

    /**
     * Returning client
     *
     * @return start date contract
     */
    public Client getClient() {
        return client;
    }


}
