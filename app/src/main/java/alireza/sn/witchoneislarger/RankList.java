package alireza.sn.witchoneislarger;

import java.util.ArrayList;
import java.util.List;

public class RankList {
    private List<User> rankList;

    public RankList() {
        this.rankList = new ArrayList<>();
    }

    public List<User> getRankList() {
        return rankList;
    }

    public void addUserToList (User user){
        rankList.add(user);
    }
}
