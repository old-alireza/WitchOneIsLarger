package alireza.sn.witchoneislarger;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList =sortingList(userList);
    }

    private List<User> sortingList(List<User> userList) {
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getScore() > o2.getScore()) {
                    return -1;
                } else if (o1.getScore() < o2.getScore()) {
                    return +1;
                } else
                    return 0;
            }
        };
        Collections.sort(userList,comparator);
        return userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.template_user_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.nameTv.setText(userList.get(position).getName());
        holder.scoreTv.setText(String.valueOf(userList.get(position).getScore()));
        holder.rank.setText("" + (position + 1));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public TextView scoreTv;
        public TextView rank;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.user_name);
            scoreTv = itemView.findViewById(R.id.user_score);
            rank = itemView.findViewById(R.id.rank);
        }
    }
}
