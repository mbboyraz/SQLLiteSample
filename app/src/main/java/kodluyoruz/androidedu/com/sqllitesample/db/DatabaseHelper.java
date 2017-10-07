package kodluyoruz.androidedu.com.sqllitesample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import kodluyoruz.androidedu.com.sqllitesample.viewmodel.Contact;

/**
 * Created by Gökhan ÖZTÜRK
 * 19.08.2017
 * CodeProjectG@gmail.com
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //db fields
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "KodluyoruzContacts";
    private static final String TABLE_CONTACTS = "Contact";

    //db column
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phoneNumber";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE TABLE Contact(id INTEGER PRIMARY KEY, name TEXT, phoneNumber TEXT)
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void addContact(ArrayList<Contact> contactList) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, 90);
        values.put(KEY_NAME, "Gökhan");
        values.put(KEY_PH_NO, "398057340857637");

//        for(Contact contact1 : contactList){
//
//            values.put(KEY_ID, contact1.getId());
//            values.put(KEY_NAME, contact1.getName());
//            values.put(KEY_PH_NO, contact1.getPhoneNumber());
//        db.insert(TABLE_CONTACTS, null, values);
//        }


        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public Contact getContact(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact();
        contact.setName(cursor.getString(0));
        contact.setPhoneNumber(cursor.getString(1));

        cursor.close();
        db.close();

        return contact;
    }

    public List<Contact> getAllContacts() {

        List<Contact> contactList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int getContactsCount() {

        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        //id = ? 2
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(int ID) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(ID)});
        db.close();
    }
}