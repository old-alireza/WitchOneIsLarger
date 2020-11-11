package alireza.sn.witchoneislarger;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentGame extends Fragment {

    private final int LEFT_BUTTON = 0;
    private final int RIGHT_BUTTON = 1;
    private final int EQUAL_BUTTON = 2;

    private int scoredInt = 0;
    private static boolean gameProceed = false;
    private static int leftNum;
    private static int rightNum;
    private int countdownNumber = 3;
    private String player;


    private Button leftButton;
    private Button rightButton;
    private Button equalButton;
    private TextView timer;
    private TextView scoredGame;
    private TextView countDownTv;
    private LinearLayout layoutGame;

    private CountDownTimer countDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getArg();
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    private void getArg() {
        player = getArguments().getString("name", null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        initViews();
        animations();
        pressesStartGame();
    }

    private void animations() {
        ObjectAnimator alphaAnimate = ObjectAnimator.ofFloat(countDownTv, "Alpha", 1, 0);
        ObjectAnimator scaleXAnimate = ObjectAnimator.ofFloat(countDownTv, "ScaleX", 1f, 4f);
        ObjectAnimator scaleYAnimate = ObjectAnimator.ofFloat(countDownTv, "ScaleY", 1f, 4f);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimate, scaleXAnimate, scaleYAnimate);
        animatorSet.setDuration(1000);

        
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (countdownNumber == 0) {
                    layoutGame.setVisibility(View.VISIBLE);
                    pressesStartGame();

                } else
                    animations();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                countDownTv.setText(String.valueOf(countdownNumber));
                countdownNumber--;
            }
        });
        animatorSet.start();
    }

    private void pressesStartGame() {
        countDownTimer = new CountDownTimer(11000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("tag", "countdown timer : " + millisUntilFinished);
                timer.setText(getString(R.string.timer, (int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                Log.i("tag", "countdown timer onFinish.");
                gameProceed = false;
                timer.setText(getString(R.string.finish_game));
                upgradeHighScore();
            }
        };

        gameProceed = true;
        updateRoundGame();
        countDownTimer.start();
    }

    private void upgradeHighScore() {
        RankList rankList = MyPreferenceManager.getInstance(getActivity()).getRankList();
        User user = new User();
        user.setScore(scoredInt);
        user.setName(player);
        rankList.addUserToList(user);
        MyPreferenceManager.getInstance(getActivity()).putRankList(rankList);
    }

    @Override
    public void onPause() {
        super.onPause();
        countDownTimer.cancel();

    }

    private void initViews() {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkScored(LEFT_BUTTON);
                updateRoundGame();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkScored(RIGHT_BUTTON);
                updateRoundGame();
            }
        });

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkScored(EQUAL_BUTTON);
                updateRoundGame();
            }
        });
    }

    private void checkScored(int whatPressed) {

        if (!gameProceed)
            return;

        if (whatPressed == LEFT_BUTTON) {

            if (leftNum > rightNum)
                scoredInt++;

        } else if (whatPressed == RIGHT_BUTTON) {

            if (leftNum < rightNum)
                scoredInt++;

        } else if (whatPressed == EQUAL_BUTTON) {

            if (leftNum == rightNum)
                scoredInt++;
        }

        scoredGame.setText(getString(R.string.score, scoredInt));
    }

    private void updateRoundGame() {

        if (!gameProceed)
            return;

        leftNum = generateRandomInt();
        rightNum = generateRandomInt();
        leftButton.setText(String.valueOf(leftNum));
        rightButton.setText(String.valueOf(rightNum));
    }

    private int generateRandomInt() {
        Random random = new Random();
        return random.nextInt(98) + 1;
    }

    private void findViews(View view) {
        leftButton = view.findViewById(R.id.left_number);
        rightButton = view.findViewById(R.id.right_number);
        equalButton = view.findViewById(R.id.equal);
        timer = view.findViewById(R.id.timer);
        scoredGame = view.findViewById(R.id.score);
        countDownTv = view.findViewById(R.id.count_down);
        layoutGame = view.findViewById(R.id.layout_game);
    }
}
