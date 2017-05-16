package pm12016g3.tln.univ.fr.vot.features.network;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.network.
 * File NetworkActivity.java.
 * Created by Ysee on 15/05/2017 - 09:41.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

public class NetworkFragment extends Fragment{

    EditText research;
    ListView friendListView;
    FloatingActionButton fabAdd;

    List<String> allFriends = new ArrayList<>();
    List<String> filteredFriends = new ArrayList<>();
    ArrayAdapter<String> adapter;
    public NetworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.network_network_fragment, container, false);
        allFriends.add("Donut");
        allFriends.add("Eclair");
        allFriends.add("Lollipop");
        filteredFriends.addAll(allFriends);

        research = (EditText) view.findViewById(R.id.network_input_research);
        friendListView = (ListView) view.findViewById(R.id.network_friend_list);
        fabAdd = (FloatingActionButton) view.findViewById(R.id.fabAdd);

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.network_friend_list_item,filteredFriends);
        friendListView.setAdapter(adapter);

        research.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filteredFriends.clear();
                for (String name: allFriends) {
                    if(charSequence.length()!=0 && name.startsWith(charSequence.toString())){
                        filteredFriends.add(name);
                        Log.i("TAG",name);
                    }

                    Log.i("TAG",filteredFriends.toString());
                }
                // Log.i("TAG",filteredNames.toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

}
