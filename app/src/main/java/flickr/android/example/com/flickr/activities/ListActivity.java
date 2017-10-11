package flickr.android.example.com.flickr.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import flickr.android.example.com.flickr.R;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mActionBar;

    private FragmentListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        setSupportActionBar(mActionBar);

        addListFragment();
    }

    private void addListFragment() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ListFragment fragment = (ListFragment) manager.findFragmentByTag("list_fragment");

        if (fragment == null) {

            fragment = new ListFragment();
            transaction.add(R.id.fragment_container, fragment, "list_fragment");
            transaction.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) {

            FragmentManager manager = getSupportFragmentManager();

            ListFragment fragment = (ListFragment) manager.findFragmentByTag("list_fragment");

            manager.beginTransaction().remove(fragment).commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        searchView.setQueryHint(getResources().getString(R.string.search_info));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ListActivity.this.mListener.clearFragmentList();
                ListActivity.this.mListener.updateFragmentList(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void setFragmentListener(FragmentListener listener) {

        this.mListener = listener;
    }

    public interface FragmentListener {

        void updateFragmentList(String query, int page);
        void clearFragmentList();

    }
}
