package contract;

/**
 * The contract for the Internet connection
 *
 * @author Alex Lozovoy
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class WiredInternetContract extends Contract {
    @XmlElement(name = "speed")
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

    public WiredInternetContract(){
        super();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
