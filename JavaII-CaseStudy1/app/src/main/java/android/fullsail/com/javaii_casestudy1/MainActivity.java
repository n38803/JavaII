package android.fullsail.com.javaii_casestudy1;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;


public class MainActivity extends Activity implements ActionBar.OnNavigationListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private String[] tArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ArrayList<String> teamOne;
        teamOne = new ArrayList();
        teamOne.add("teamOne Player 1");
        teamOne.add("teamOne Player 2");
        teamOne.add("teamOne Player 3");
        teamOne.add("teamOne Player 4");
        teamOne.add("teamOne Player 5");
        teamOne.add("teamOne Player 6");
        teamOne.add("teamOne Player 7");
        teamOne.add("teamOne Player 8");
        teamOne.add("teamOne Player 9");

        ArrayList<String> teamTwo;
        teamTwo = new ArrayList();
        teamTwo.add("teamTwo Player 1");
        teamTwo.add("teamTwo Player 2");
        teamTwo.add("teamTwo Player 3");
        teamTwo.add("teamTwo Player 4");
        teamTwo.add("teamTwo Player 5");
        teamTwo.add("teamTwo Player 6");
        teamTwo.add("teamTwo Player 7");
        teamTwo.add("teamTwo Player 8");
        teamTwo.add("teamTwo Player 9");

        ArrayList<ArrayList> allTeams;
        allTeams = new ArrayList<ArrayList>();
        allTeams.add(teamOne);
        allTeams.add(teamTwo);



        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[] {
                                getString(R.string.team1),
                                getString(R.string.team2),
                                getString(R.string.team3),
                                getString(R.string.team4),
                                getString(R.string.team5),
                                getString(R.string.team6),
                        }),
                this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();






        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.

        Log.i("TEST", "Position: " + position);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, TeamFragment.newInstance(position + 1))
                .commit();
        return true;
    }



    public static class TeamFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        public static int tPosition;

        public static TeamFragment newInstance(int sectionNumber) {
            TeamFragment fragment = new TeamFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            tPosition = sectionNumber;
            return fragment;
        }

        public TeamFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);





            String[] teamOne = new String[9];
            teamOne[0] = ("teamOne Player 1");
            teamOne[1] = ("teamOne Player 2");
            teamOne[2] = ("teamOne Player 3");
            teamOne[3] = ("teamOne Player 4");
            teamOne[4] = ("teamOne Player 5");
            teamOne[5] = ("teamOne Player 6");
            teamOne[6] = ("teamOne Player 7");
            teamOne[7] = ("teamOne Player 8");
            teamOne[8] = ("teamOne Player 9");

            String[] teamTwo = new String[9];
            teamTwo[0] = ("teamTwo Player 1");
            teamTwo[1] = ("teamTwo Player 2");
            teamTwo[2] = ("teamTwo Player 3");
            teamTwo[3] = ("teamTwo Player 4");
            teamTwo[4] = ("teamTwo Player 5");
            teamTwo[5] = ("teamTwo Player 6");
            teamTwo[6] = ("teamTwo Player 7");
            teamTwo[7] = ("teamTwo Player 8");
            teamTwo[8] = ("teamTwo Player 9");

            String[] teamThree = new String[9];
            teamThree[0] = ("teamThree Player 1");
            teamThree[1] = ("teamThree Player 2");
            teamThree[2] = ("teamThree Player 3");
            teamThree[3] = ("teamThree Player 4");
            teamThree[4] = ("teamThree Player 5");
            teamThree[5] = ("teamThree Player 6");
            teamThree[6] = ("teamThree Player 7");
            teamThree[7] = ("teamThree Player 8");
            teamThree[8] = ("teamThree Player 9");

            String[] teamFour = new String[9];
            teamFour[0] = ("teamFour Player 1");
            teamFour[1] = ("teamFour Player 2");
            teamFour[2] = ("teamFour Player 3");
            teamFour[3] = ("teamFour Player 4");
            teamFour[4] = ("teamFour Player 5");
            teamFour[5] = ("teamFour Player 6");
            teamFour[6] = ("teamFour Player 7");
            teamFour[7] = ("teamFour Player 8");
            teamFour[8] = ("teamFour Player 9");

            String[] teamFive = new String[9];
            teamFive[0] = ("teamFive Player 1");
            teamFive[1] = ("teamFive Player 2");
            teamFive[2] = ("teamFive Player 3");
            teamFive[3] = ("teamFive Player 4");
            teamFive[4] = ("teamFive Player 5");
            teamFive[5] = ("teamFive Player 6");
            teamFive[6] = ("teamFive Player 7");
            teamFive[7] = ("teamFive Player 8");
            teamFive[8] = ("teamFive Player 9");

            String[] teamSix = new String[9];
            teamSix[0] = ("teamSix Player 1");
            teamSix[1] = ("teamSix Player 2");
            teamSix[2] = ("teamSix Player 3");
            teamSix[3] = ("teamSix Player 4");
            teamSix[4] = ("teamSix Player 5");
            teamSix[5] = ("teamSix Player 6");
            teamSix[6] = ("teamSix Player 7");
            teamSix[7] = ("teamSix Player 8");
            teamSix[8] = ("teamSix Player 9");



            Object[] tObject = new Object[]{teamOne, teamTwo, teamThree, teamFour, teamFive, teamSix};

            int arrayPosition = (tPosition - 1);

            TextView tName = (TextView) rootView.findViewById(R.id.teamName);
            tName.setText("Team " + tPosition);


            ListView pList = (ListView) rootView.findViewById(R.id.playerList);

            String[] tArray = (String[])tObject[arrayPosition];

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tArray);
            pList.setAdapter(adapter);



            return rootView;


        }
    }

}
