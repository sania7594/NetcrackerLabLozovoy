package contract;

/**
 * The contract for the Internet connection
 *
 * @author Alex Lozovoy
 */

public class WiredInternetContract extends Contract {
    private float speed;

    /**
     * @param id             ID
     * @param startDate      Contract start date
     * @param endDate        The end date of the contract
     * @param numberContract Number contract
     * @param client         Client
     * @param speed          Сonnection speed
     */
    public WiredInternetContract(int id, long startDate, long endDate, int numberContract, Client client, float speed) {
        super(id, startDate, endDate, numberContract, client);
        this.speed = speed;
    }

}
