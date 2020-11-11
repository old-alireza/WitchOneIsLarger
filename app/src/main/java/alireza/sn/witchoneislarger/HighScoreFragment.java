package alireza.sn.witchoneislarger;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.high_score_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configureList();
    }

    private void configureList() {
        RankList rankListOb = MyPreferenceManager.getInstance(getActivity()).getRankList();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userAdapter = new UserAdapter(rankListOb.getRankList());
        recyclerView.setAdapter(userAdapter);
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
    }
}
