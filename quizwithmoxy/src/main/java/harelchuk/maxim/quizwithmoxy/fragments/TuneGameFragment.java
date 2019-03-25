package harelchuk.maxim.quizwithmoxy.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import harelchuk.maxim.quizwithmoxy.InPlayActivity;
import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.DataAdapter;
import harelchuk.maxim.quizwithmoxy.presenter.TuneGamePresenter;
import harelchuk.maxim.quizwithmoxy.view.TuneGameView;

public class TuneGameFragment extends MvpAppCompatFragment implements TuneGameView {

    @InjectPresenter
    TuneGamePresenter gamePresenter;

    SharedPreferences sharedPreferences;
    private View tuneGameMenuView;
    private ViewGroup levelListVG;
    private Context context;
    private long[] coinsGAC;
    private int[] level_costs;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        ViewGroup mainContainerVG = getActivity().findViewById(R.id.main_container);
        mainContainerVG.removeAllViews();
        tuneGameMenuView = inflater.inflate(R.layout.tune_game_empty, mainContainerVG, false);
        //Animation animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom_to_center);
        //tuneGameMenuView.startAnimation(animation);
        levelListVG = tuneGameMenuView.findViewById(R.id.level_list_frame);

        coinsGAC = new long[2];
        level_costs = new int[9];
        return tuneGameMenuView;
    }

    @Override
    public void onStart() {
        super.onStart();
        gamePresenter.showUsersMoneyAndBF();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void fillLevelList(int levels[], int[] costs, int[] reward) {

        level_costs = costs;
        View tempV = LayoutInflater.from(context).inflate(R.layout.tune_game_recycle_view, levelListVG, false);
        levelListVG.addView(tempV);
        ArrayList<Map<String, Integer>> data = new ArrayList<>(levels.length);
        Map<String, Integer> map;
        for (int i = 0; i < levels.length; i++) {
            map = new HashMap<>();
            map.put("level", levels[i]);
            map.put("cost", costs[i]);
            map.put("reward", reward[i]);
            if (levels[i] == 1 || levels[i] == 2 || levels[i] == 3) {
                map.put("coin", 2);
            }
            if (levels[i] == 4 || levels[i] == 5 || levels[i] == 6) {
                map.put("coin", 1);
            }
            if (levels[i] == 7 || levels[i] == 8 || levels[i] == 9 || levels[i] == 10) {
                map.put("coin", 0);
            }
            data.add(map);
        }
        RecyclerView recyclerView = tuneGameMenuView.findViewById(R.id.recyclerView);
        DataAdapter adapter = new DataAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DataAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                checkIfAvailable(position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Animation animation = AnimationUtils.loadAnimation(context, R.anim.from_bottom_to_center);
        //recyclerView.startAnimation(animation);

    }

    private void checkIfAvailable(int position) {
        int level = position + 1;
        long cost = level_costs[position];
        if (level == 4 || level == 5 || level == 6) {
            cost *= 56;
        }
        if (level == 7 || level == 8 || level == 9 || level == 10) {
            cost *= 56 * 210;
        }
        long money = coinsGAC[2] + coinsGAC[1] * 56 + coinsGAC[0] * 56 * 210;
        //Toast.makeText(getContext(),money + " must be > then " + cost, Toast.LENGTH_SHORT).show();

        if (money >= cost) {
            gamePresenter.writeOff(cost);
            startGame(position);
        }
    }

    private void startGame(int position) {
        Intent intent = new Intent(context, InPlayActivity.class);
        intent.putExtra("level", position);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("level", position);
        editor.commit();
        startActivity(intent);
    }

    @Override
    public void fillCoins(long[] coins_GAC) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.from_top_to_center);
        coinsGAC = coins_GAC;
        TextView coins_GD = tuneGameMenuView.findViewById(R.id.userGDTV);
        TextView coins_AD = tuneGameMenuView.findViewById(R.id.userADTV);
        TextView coins_CP = tuneGameMenuView.findViewById(R.id.userCPTV);
        //ImageView booksFilms = tuneGameMenuView.findViewById(R.id.tuneBookFilmIconIV);
        ImageView moneyImage = tuneGameMenuView.findViewById(R.id.windowUserMoneyIV);
        coins_AD.startAnimation(animation);
        coins_CP.startAnimation(animation);
        coins_GD.startAnimation(animation);
        coins_GD.setText(String.valueOf(coins_GAC[0]));
        coins_AD.setText(String.valueOf(coins_GAC[1]));
        coins_CP.setText(String.valueOf(coins_GAC[2]));
        moneyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alertdialog_money_describtion, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                Button closeDialogButton = dialogView.findViewById(R.id.alert_dialog_button);
                TextView titleTV = dialogView.findViewById(R.id.alert_dialog_text_title_TV);
                titleTV.setText(getResources().getString(R.string.money));
                TextView textTV = dialogView.findViewById(R.id.alert_dialog_text_TV);
                textTV.setText(getResources().getString(R.string.youHaveMoney));

                TextView coinsGD = dialogView.findViewById(R.id.alertUsersGD);
                coinsGD.setText(String.valueOf(coinsGAC[0]));

                TextView coinsAD = dialogView.findViewById(R.id.alertUsersAD);
                coinsAD.setText(String.valueOf(coinsGAC[0] * 210 + coinsGAC[1]));

                TextView coinsCP = dialogView.findViewById(R.id.alertUsersCP);
                coinsCP.setText(String.valueOf(coinsGAC[0] * 210 * 56 + coinsGAC[1] * 56 + coinsGAC[2]));


                closeDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.getWindow().setDimAmount(0.8f);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                dialog.show();
            }
        });
    }

}
