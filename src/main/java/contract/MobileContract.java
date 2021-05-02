package contract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Mobile phone contract
 *
 * @author Alex Lozovoy
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class MobileContract extends Contract {
    @XmlElement(name = "minutes")
    private int minutes;
    @XmlElement(name = "messages")
    private int messages;
    @XmlElement(name = "trafic")
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

    public MobileContract(){
        super();
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
