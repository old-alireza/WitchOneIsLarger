package alireza.sn.witchoneislarger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // onClick button
    public void startGame(View view) {
        GetNameDialog dialog = new GetNameDialog(this, new OnSetNameListener() {

            @Override
            public void onSetName(String name) {
                FragmentGame fragmentGame = new FragmentGame();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                fragmentGame.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().
                        add(R.id.frame_container, fragmentGame).addToBackStack(null).commit();
            }
        });
        dialog.show();
    }

    //onClick button
    public void highScore(View view) {
        HighScoreFragment highScoreFragment = new HighScoreFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, highScoreFragment)
                .addToBackStack(null).commit();
    }

}