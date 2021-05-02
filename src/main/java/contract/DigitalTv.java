package contract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * A contract for digital TV
 *
 * @author Alex Lozovoy
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DigitalTv extends Contract {
    @XmlElement(name = "channelPack")
    private int channelPackage;

    /**
     * @param id             ID
     * @param startDate      Contract start date
     * @param endDate        The end date of the contract
     * @param numberContract Number contract
     * @param client         Client
     * @param channelPackage Channel package
     */
    public DigitalTv(int id, long startDate, long endDate, int numberContract, Client client, int channelPackage) {
        super(id, startDate, endDate, numberContract, client);
        this.channelPackage = channelPackage;
    }

    public DigitalTv(){
        super();
    }

    public int getChannelPackage() {
        return channelPackage;
    }

    public void setChannelPackage(int channelPackage) {
        this.channelPackage = channelPackage;
    }


}
