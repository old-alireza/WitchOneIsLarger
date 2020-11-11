package alireza.sn.witchoneislarger;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

class MyPreferenceManager {
    private static MyPreferenceManager instance = null;
    private SharedPreferences preferences =null;
    private SharedPreferences.Editor editor =null;

    private MyPreferenceManager(Context context) {
        preferences = context.getSharedPreferences("preference",Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public static MyPreferenceManager getInstance(Context context) {

        if (instance==null)
            instance = new MyPreferenceManager(context);

        return instance;
    }

    public void putRankList (RankList rankList){
        Gson gson = new Gson();

        String strRankList = gson.toJson(rankList,RankList.class);
        editor.putString("rank_list",strRankList);
        editor.apply();
    }

    public RankList getRankList (){
        Gson gson = new Gson();
        String rankListStr = preferences.getString("rank_list",null);

        if (rankListStr == null){
            return new RankList();
        }
        return gson.fromJson(rankListStr,RankList.class);
    }
}
