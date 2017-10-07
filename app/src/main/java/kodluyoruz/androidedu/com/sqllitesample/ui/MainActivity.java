package kodluyoruz.androidedu.com.sqllitesample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import kodluyoruz.androidedu.com.sqllitesample.R;
import kodluyoruz.androidedu.com.sqllitesample.db.DatabaseHelper;
import kodluyoruz.androidedu.com.sqllitesample.viewmodel.Contact;

public class MainActivity extends AppCompatActivity {

    //variable
    private int allContactCount = 0;

    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        insterData();

        databaseHelper.getAllContacts();
    }

    private void insterData() {

        ArrayList<Contact> contactList = new ArrayList<>();
        Contact contact = new Contact();
        contact.setId(90);
        contact.setName("Gökhan Öztürk");
        contact.setPhoneNumber("0531 626 69 02");

        contactList.add(contact);
        databaseHelper.addContact(contactList);
    }
}
