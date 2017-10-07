package kodluyoruz.androidedu.com.sqllitesample.viewmodel;

/**
 * Created by Gökhan ÖZTÜRK
 * 19.08.2017
 * CodeProjectG@gmail.com
 */
public class Contact {

    private int id;
    private String name;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
