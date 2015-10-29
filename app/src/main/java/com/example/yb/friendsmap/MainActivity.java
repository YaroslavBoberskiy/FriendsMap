package com.example.yb.friendsmap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener, DialogInterface.OnClickListener,
        DialogInterface.OnMultiChoiceClickListener, View.OnClickListener {

    private final String[] namesOfPredefinedPersons = {"Mi Salisbury", "Haydee Fogle", "Nidia Hazel",
            "Ernie Mccabe", "Nerissa Gooch", "Anjanette Mcclelland", "Zoila Schafer", "Rey Kimbrell",
            "Karine Peck", "Thi Vest", "Ursula Woodcock", "Jackqueline Stamps", "Reagan Sena",
            "Nestor Case", "Shila Norfleet", "Mariam Mcdonnell", "Rosalva Kessler",
            "Natosha Little", "Dot Goins", "Geri Mcdade", "Kip Cooney", "Margarett Baez",
            "Clair Evers", "Nelia Bagwell", "Dewitt Cecil", "Staci Coons", "Patrina Beavers",
            "Kylie Archie", "Ena Gleason", "Lynell Yount", "Merrill Meadows", "Patrick Shultz",
            "Codi Amos", "Suzette Hefner", "Cuc Turney", "Valentin Jolley", "Katrice Jamison",
            "Tori Ashley", "Sharita Mcnulty", "Millicent Eggleston", "Roxy Mccaffrey",
            "Doreatha Cloud", "Brandee Tabor", "Jetta Samuel", "Maryanna Hoover",
            "Dolly Buffington", "Erlinda Limon", "Sharonda Russ", "Erline Ludwig", "Jaleesa Pinson"};

    private final boolean[] selectedPersons = {false, false, false,
            false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false};

    private ArrayList<String> personNamesList = new ArrayList<String>();
    private ArrayList<String> friendsNamesList = new ArrayList<String>();
    private List<PersonsEntry> entries = new ArrayList<PersonsEntry>();
    private String friendsListString = null;
    EntryAdapter EntryAdapter;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView personsEntryListView = (ListView) findViewById(R.id.coreListView);
        personsEntryListView.setItemsCanFocus(false);
        personsEntryListView.setLongClickable(true);

        EntryAdapter = new EntryAdapter(this, R.layout.list_entry_layout);
        personsEntryListView.setAdapter(EntryAdapter);

        fillListWithEntries();

        personsEntryListView.setOnItemLongClickListener(this);
        personsEntryListView.setOnItemClickListener(this);
    }

    private void addNamesToArrList() {
        for (int i = 0; i < namesOfPredefinedPersons.length; i++) {
            personNamesList.add(namesOfPredefinedPersons[i]);
        }
    }

    private void fillFriendsList() {
        int qttOfFriendsInIteration;
        int friendsNameInArrayPosition;

        Random friendsName = new Random();
        Random qttOfFriends = new Random();

        qttOfFriendsInIteration = qttOfFriends.nextInt(7);
        friendsNamesList.clear();

        for (int i = 0; i < qttOfFriendsInIteration; i++) {
            friendsNameInArrayPosition = friendsName.nextInt(namesOfPredefinedPersons.length);
            friendsNamesList.add(namesOfPredefinedPersons[friendsNameInArrayPosition]);
        }

        friendsListString = "";

        for (String s : friendsNamesList) {
            friendsListString += s + ", ";
        }
    }

    private List<PersonsEntry> getPersonsEntries() {

        String avatarName;
        int avatarResID;
        int personNameInArrayPosition;

        addNamesToArrList();

        for (int i = 0; i < 25; i++) {

            Random avatarRand = new Random();
            Random nameRand = new Random();

            avatarName = "a" + (avatarRand.nextInt(100));
            avatarResID = getResources().getIdentifier(avatarName, "drawable", getPackageName());
            personNameInArrayPosition = nameRand.nextInt(namesOfPredefinedPersons.length);

            fillFriendsList();

            entries.add(
                    new PersonsEntry(
                            personNamesList.get(personNameInArrayPosition),
                            avatarResID,
                            friendsListString
                    )
            );
        }
        return entries;
    }

    private void fillListWithEntries() {

        for (final PersonsEntry entry : getPersonsEntries()) {
            EntryAdapter.add(entry);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "short clicked pos: " + position, Toast.LENGTH_SHORT).show();
        //Log.i("click!", "click!");
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootElement = li.inflate(R.layout.manage_friends_dialog, null);
        ImageButton addFriends = (ImageButton) rootElement.findViewById(R.id.addFriendsIb);
        ImageButton removeFriends = (ImageButton) rootElement.findViewById(R.id.removeFriendsIb);

        addFriends.setImageResource(R.drawable.add_user_icon);
        removeFriends.setImageResource(R.drawable.rem_user_icon);

        addFriends.setOnClickListener(this);
        removeFriends.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(rootElement);
        builder.create().show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "long clicked pos: " + position, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove friend dialog");
        builder.setIcon(R.drawable.a38);
        TextView tv = (TextView) view.findViewById(R.id.personName);
        builder.setMessage("Do you really want to delete "+ tv.getText().toString() + " from the list?");
        builder.setPositiveButton("Yes", this);
        builder.setNegativeButton("No", this);

//        builder.setPositiveButton(android.R.string.ok, this);
//        builder.setNegativeButton(android.R.string.no, this);
//        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
//            }
//        });

        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                Toast.makeText(getApplicationContext(), "Deleting person...", Toast.LENGTH_SHORT).show();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                Toast.makeText(getApplicationContext(), "Ok, let's stay here...", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), namesOfPredefinedPersons[which], Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addFriendsIb:
                Toast.makeText(getApplicationContext(), "Add friends...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.removeFriendsIb:
                Toast.makeText(getApplicationContext(), "Remove friends...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
