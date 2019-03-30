package harelchuk.maxim.quizwithmoxy.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static harelchuk.maxim.quizwithmoxy.model.STRINGS.*;

public class UserDataSingleton {

    private SharedPreferences sharedPreferencesMoney;

    //private

    private final int MY_USER_ID = 1;

    private int l_1_cost_cp;
    private int l_2_cost_cp;
    private int l_3_cost_cp;
    private int l_4_cost_ad;
    private int l_5_cost_ad;
    private int l_6_cost_ad;
    private int l_7_cost_gd;
    private int l_8_cost_gd;
    private int l_9_cost_gd;
    private int l_10_cost_gd;

    private int l_1_reward_cp;
    private int l_2_reward_cp;
    private int l_3_reward_cp;
    private int l_4_reward_ad;
    private int l_5_reward_ad;
    private int l_6_reward_ad;
    private int l_7_reward_gd;
    private int l_8_reward_gd;
    private int l_9_reward_gd;
    private int l_10_reward_gd;

    private int l_1_lose_cp;
    private int l_2_lose_cp;
    private int l_3_lose_cp;
    private int l_4_lose_cp;
    private int l_5_lose_ad;
    private int l_6_lose_ad;
    private int l_7_lose_ad;
    private int l_8_lose_gd;
    private int l_9_lose_gd;
    private int l_10_lose_gd;


    //private SharedPreferences sharedPreferencesUser;

    private int user_id;
    private long user_uuid;
    private String user_name;

    private long user_money;

    private int number_easy_games;
    private int number_medium_games;
    private int number_hard_games;

    private int number_easy_winnings;
    private int number_medium_winnings;
    private int number_hard_winnings;

    private boolean is_adv;

    private boolean is_books;
    private boolean is_films;

    private int current_theme;
    private boolean is_skin_targar;
    private boolean is_skin_stark;
    private boolean is_skin_lann;
    private boolean is_skin_night;

    private boolean is_credit;
    private long credit_time;
    private long credit_sum;

    private boolean is_debit;
    private long debit_time;
    private long debit_sum;

    private int chosen_level;

    private static final UserDataSingleton ourInstance = new UserDataSingleton();

    public static UserDataSingleton getInstance() {
        return ourInstance;
    }

    private UserDataSingleton() {
        setSharedPreferencesMoneyIfNotExists();
        getUserById();
    }

    private void getUserById() {
        final User[] loadingUser = new User[1];
        NetworkService.getInstance().getJSONApi().getUserInfo(MY_USER_ID).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                User user = response.body();
                assert user != null;
                getVariables(user);
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                //              LOAD    BAD         SCREEN
            }
        });
    }

    private void executeVoid(Call<Void> call) {
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.d("myLogs", "USER INFO UPDATED");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.d("myLogs", "USER INFO NOT UPDATED");
            }
        });
    }


    private void getVariables(User user) {
        this.l_1_cost_cp = sharedPreferencesMoney.getInt(L_1_COST_CP, 0);
        this.l_2_cost_cp = sharedPreferencesMoney.getInt(L_2_COST_CP, 0);
        this.l_3_cost_cp = sharedPreferencesMoney.getInt(L_3_COST_CP, 0);
        this.l_4_cost_ad = sharedPreferencesMoney.getInt(L_4_COST_AD, 0);
        this.l_5_cost_ad = sharedPreferencesMoney.getInt(L_5_COST_AD, 0);
        this.l_6_cost_ad = sharedPreferencesMoney.getInt(L_6_COST_AD, 0);
        this.l_7_cost_gd = sharedPreferencesMoney.getInt(L_7_COST_GD, 0);
        this.l_8_cost_gd = sharedPreferencesMoney.getInt(L_8_COST_GD, 0);
        this.l_9_cost_gd = sharedPreferencesMoney.getInt(L_9_COST_GD, 0);
        this.l_10_cost_gd = sharedPreferencesMoney.getInt(L_10_COST_GD, 0);

        this.l_1_reward_cp = sharedPreferencesMoney.getInt(L_1_REWARD_CP, 0);
        this.l_2_reward_cp = sharedPreferencesMoney.getInt(L_2_REWARD_CP, 0);
        this.l_3_reward_cp = sharedPreferencesMoney.getInt(L_3_REWARD_CP, 0);
        this.l_4_reward_ad = sharedPreferencesMoney.getInt(L_4_REWARD_AD, 0);
        this.l_5_reward_ad = sharedPreferencesMoney.getInt(L_5_REWARD_AD, 0);
        this.l_6_reward_ad = sharedPreferencesMoney.getInt(L_6_REWARD_AD, 0);
        this.l_7_reward_gd = sharedPreferencesMoney.getInt(L_7_REWARD_GD, 0);
        this.l_8_reward_gd = sharedPreferencesMoney.getInt(L_8_REWARD_GD, 0);
        this.l_9_reward_gd = sharedPreferencesMoney.getInt(L_9_REWARD_GD, 0);
        this.l_10_reward_gd = sharedPreferencesMoney.getInt(L_10_REWARD_GD, 0);

        this.l_1_lose_cp = sharedPreferencesMoney.getInt(L_1_LOSE_CP, 0);
        this.l_2_lose_cp = sharedPreferencesMoney.getInt(L_2_LOSE_CP, 0);
        this.l_3_lose_cp = sharedPreferencesMoney.getInt(L_3_LOSE_CP, 0);
        this.l_4_lose_cp = sharedPreferencesMoney.getInt(L_4_LOSE_CP, 0);
        this.l_5_lose_ad = sharedPreferencesMoney.getInt(L_5_LOSE_AD, 0);
        this.l_6_lose_ad = sharedPreferencesMoney.getInt(L_6_LOSE_AD, 0);
        this.l_7_lose_ad = sharedPreferencesMoney.getInt(L_7_LOSE_AD, 0);
        this.l_8_lose_gd = sharedPreferencesMoney.getInt(L_8_LOSE_GD, 0);
        this.l_9_lose_gd = sharedPreferencesMoney.getInt(L_9_LOSE_GD, 0);
        this.l_10_lose_gd = sharedPreferencesMoney.getInt(L_10_LOSE_GD, 0);

        this.user_id = user.getId_user();
        this.user_uuid = user.getUser_uuid();
        this.user_name = user.getUser_name();

        this.user_money = user.getUser_money();

        this.number_easy_games = user.getEasy_games();
        this.number_medium_games = user.getMedium_games();
        this.number_hard_games = user.getHard_games();

        this.number_easy_winnings = user.getEasy_winnings();
        this.number_medium_winnings = user.getMedium_winnings();
        this.number_hard_winnings = user.getHard_winnings();

        this.is_adv = user.isIs_adv();

        this.is_books = user.isIs_books();
        this.is_films = user.isIs_films();

        this.current_theme = user.getCurrent_theme();
        this.is_skin_targar = user.isIs_skin_targar();
        this.is_skin_stark = user.isIs_skin_stark();
        this.is_skin_lann = user.isIs_skin_lann();
        this.is_skin_night = user.isIs_skin_night();

        this.is_credit = user.isIs_credit();
        this.credit_time = user.getCredit_time();
        this.credit_sum = user.getCredit_sum();

        this.is_debit = user.isIs_debit();
        this.debit_time = user.getDebit_time();
        this.debit_sum = user.getDebit_sum();

        this.chosen_level = 0;
    }

    private void setSharedPreferencesMoneyIfNotExists() {

        this.sharedPreferencesMoney = AppForContext.getContext()
                .getSharedPreferences(SHARED_PREFERENCES_MONEY_AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor;

        if (!sharedPreferencesMoney.contains("initialized")) {

            Log.d("myLogs", "SP M CREATED");

            editor = sharedPreferencesMoney.edit();

            editor.putBoolean("initialized", true);

            editor.putInt(L_1_COST_CP, 1);
            editor.putInt(L_2_COST_CP, 5);
            editor.putInt(L_3_COST_CP, 15);
            editor.putInt(L_4_COST_AD, 1);
            editor.putInt(L_5_COST_AD, 8);
            editor.putInt(L_6_COST_AD, 25);
            editor.putInt(L_7_COST_GD, 1);
            editor.putInt(L_8_COST_GD, 7);
            editor.putInt(L_9_COST_GD, 21);
            editor.putInt(L_10_COST_GD, 150);

            editor.putInt(L_1_REWARD_CP, 11);
            editor.putInt(L_2_REWARD_CP, 35);
            editor.putInt(L_3_REWARD_CP, 127);
            editor.putInt(L_4_REWARD_AD, 25);
            editor.putInt(L_5_REWARD_AD, 83);
            editor.putInt(L_6_REWARD_AD, 655);
            editor.putInt(L_7_REWARD_GD, 29);
            editor.putInt(L_8_REWARD_GD, 91);
            editor.putInt(L_9_REWARD_GD, 321);
            editor.putInt(L_10_REWARD_GD, 1000);

            editor.putInt(L_1_LOSE_CP, 1);
            editor.putInt(L_2_LOSE_CP, 2);
            editor.putInt(L_3_LOSE_CP, 10);
            editor.putInt(L_4_LOSE_CP, 30);
            editor.putInt(L_5_LOSE_AD, 3);
            editor.putInt(L_6_LOSE_AD, 24);
            editor.putInt(L_7_LOSE_AD, 75);
            editor.putInt(L_8_LOSE_GD, 4);
            editor.putInt(L_9_LOSE_GD, 28);
            editor.putInt(L_10_LOSE_GD, 63);

            editor.commit();
        }

    }

    public int getL_1_cost_cp() {
        return l_1_cost_cp;
    }

    public int getL_2_cost_cp() {
        return l_2_cost_cp;
    }

    public int getL_3_cost_cp() {
        return l_3_cost_cp;
    }

    public int getL_4_cost_ad() {
        return l_4_cost_ad;
    }

    public int getL_5_cost_ad() {
        return l_5_cost_ad;
    }

    public int getL_6_cost_ad() {
        return l_6_cost_ad;
    }

    public int getL_7_cost_gd() {
        return l_7_cost_gd;
    }

    public int getL_8_cost_gd() {
        return l_8_cost_gd;
    }

    public int getL_9_cost_gd() {
        return l_9_cost_gd;
    }

    public int getL_10_cost_gd() {
        return l_10_cost_gd;
    }

    public int getL_1_reward_cp() {
        return l_1_reward_cp;
    }

    public int getL_2_reward_cp() {
        return l_2_reward_cp;
    }

    public int getL_3_reward_cp() {
        return l_3_reward_cp;
    }

    public int getL_4_reward_ad() {
        return l_4_reward_ad;
    }

    public int getL_5_reward_ad() {
        return l_5_reward_ad;
    }

    public int getL_6_reward_ad() {
        return l_6_reward_ad;
    }

    public int getL_7_reward_gd() {
        return l_7_reward_gd;
    }

    public int getL_8_reward_gd() {
        return l_8_reward_gd;
    }

    public int getL_9_reward_gd() {
        return l_9_reward_gd;
    }

    public int getL_10_reward_gd() {
        return l_10_reward_gd;
    }

    public int getL_1_lose_cp() {
        return l_1_lose_cp;
    }

    public int getL_2_lose_cp() {
        return l_2_lose_cp;
    }

    public int getL_3_lose_cp() {
        return l_3_lose_cp;
    }

    public int getL_4_lose_cp() {
        return l_4_lose_cp;
    }

    public int getL_5_lose_ad() {
        return l_5_lose_ad;
    }

    public int getL_6_lose_ad() {
        return l_6_lose_ad;
    }

    public int getL_7_lose_ad() {
        return l_7_lose_ad;
    }

    public int getL_8_lose_gd() {
        return l_8_lose_gd;
    }

    public int getL_9_lose_gd() {
        return l_9_lose_gd;
    }

    public int getL_10_lose_gd() {
        return l_10_lose_gd;
    }

    public long getUser_uuid() {
        return user_uuid;
    }

    public String getUser_name() {
        return user_name;
    }

    public long getUser_money() {
        return user_money;
    }

    public int getNumber_easy_games() {
        return number_easy_games;
    }

    public int getNumber_medium_games() {
        return number_medium_games;
    }

    public int getNumber_hard_games() {
        return number_hard_games;
    }

    public int getNumber_easy_winnings() {
        return number_easy_winnings;
    }

    public int getNumber_medium_winnings() {
        return number_medium_winnings;
    }

    public int getNumber_hard_winnings() {
        return number_hard_winnings;
    }

    public boolean isIs_adv() {
        return is_adv;
    }

    public boolean isIs_books() {
        return is_books;
    }

    public boolean isIs_films() {
        return is_films;
    }

    public int getCurrent_theme() {
        return current_theme;
    }

    public boolean isIs_skin_targar() {
        return is_skin_targar;
    }

    public boolean isIs_skin_lann() {
        return is_skin_lann;
    }

    public boolean isIs_skin_stark() {
        return is_skin_stark;
    }

    public boolean isIs_skin_night() {
        return is_skin_night;
    }

    public boolean isIs_credit() {
        return is_credit;
    }

    public long getCredit_time() {
        return credit_time;
    }

    public long getCredit_sum() {
        return credit_sum;
    }

    public boolean isIs_debit() {
        return is_debit;
    }

    public long getDebit_time() {
        return debit_time;
    }

    public long getDebit_sum() {
        return debit_sum;
    }

    public int getChosen_level() {
        return chosen_level;
    }

    public void setChosen_level(int chosen_level) {
        this.chosen_level = chosen_level;
        //return chosen_level;
    }

    public void setUser_uuid(long user_uuid) {
        this.user_uuid = user_uuid;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateUser_uuid(MY_USER_ID, user_uuid);
        executeVoid(call);
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateUser_name(MY_USER_ID, user_name);
        executeVoid(call);
    }

    public void addUserMoney(long adding_money) {
        this.user_money += adding_money;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateUser_money(MY_USER_ID, user_money);
        executeVoid(call);
    }

    public void setUserMoney(long user_money) {
        this.user_money = user_money;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateUser_money(MY_USER_ID, user_money);
        executeVoid(call);
    }

    public void incrementNumber_easy_games() {
        this.number_easy_games++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateEasy_games(MY_USER_ID, number_easy_games);
        executeVoid(call);
    }

    public void incrementNumber_medium_games() {
        this.number_medium_games++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateMedium_games(MY_USER_ID, number_medium_games);
        executeVoid(call);
    }

    public void incrementNumber_hard_games() {
        this.number_hard_games++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateHard_games(MY_USER_ID, number_hard_games);
        executeVoid(call);
    }

    public void incrementNumber_easy_winnings() {
        this.number_easy_winnings++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateEasy_winnings(MY_USER_ID, number_easy_winnings);
        executeVoid(call);
    }

    public void incrementNumber_medium_winnings() {
        this.number_medium_winnings++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateMedium_winnings(MY_USER_ID, number_medium_winnings);
        executeVoid(call);
    }

    public void incrementNumber_hard_winnings() {
        this.number_hard_winnings++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateHard_winnings(MY_USER_ID, number_hard_winnings);
        executeVoid(call);
    }
/*
    public void setIs_adv(boolean is_adv) {
        this.is_adv = is_adv;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_adv(MY_USER_ID, is_adv);
        executeVoid(call);
    }
*/
    public void setIs_books(boolean is_books) {
        this.is_books = is_books;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_books(MY_USER_ID, is_books);
        executeVoid(call);
    }

    public void setIs_films(boolean is_films) {
        this.is_films = is_films;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_films(MY_USER_ID, is_films);
        executeVoid(call);
    }

    public void setCurrent_theme(int current_theme) {
        this.current_theme = current_theme;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateCurrent_theme(MY_USER_ID, current_theme);
        executeVoid(call);
    }

    public void setIs_skin_targar(boolean is_skin_targar) {
        this.is_skin_targar = is_skin_targar;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_skin_targar(MY_USER_ID, is_skin_targar);
        executeVoid(call);
    }

    public void setIs_skin_lann(boolean is_skin_lann) {
        this.is_skin_lann = is_skin_lann;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_skin_lann(MY_USER_ID, is_skin_lann);
        executeVoid(call);
    }

    public void setIs_skin_stark(boolean is_skin_stark) {
        this.is_skin_stark = is_skin_stark;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_skin_stark(MY_USER_ID, is_skin_stark);
        executeVoid(call);
    }

    public void setIs_skin_night(boolean is_skin_night) {
        this.is_skin_night = is_skin_night;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_skin_night(MY_USER_ID, is_skin_night);
        executeVoid(call);
    }

    public void setIs_credit(boolean is_credit) {
        this.is_credit = is_credit;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_credit(MY_USER_ID, is_credit);
        executeVoid(call);
    }

    public void setCredit_time(long credit_time) {
        this.credit_time = credit_time;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateCredit_time(MY_USER_ID, credit_time);
        executeVoid(call);
    }


    public void setCredit_sum(long credit_sum) {
        this.credit_sum = credit_sum;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateCredit_sum(MY_USER_ID, credit_sum);
        executeVoid(call);
    }

    public void setIs_debit(boolean is_debit) {
        this.is_debit = is_debit;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateIs_debit(MY_USER_ID, is_debit);
        executeVoid(call);
    }


    public void setDebit_time(long debit_time) {
        this.debit_time = debit_time;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateDebit_time(MY_USER_ID, debit_time);
        executeVoid(call);
    }

    /*
        public void addDebit_sum(long adding_debit) {
            this.debit_sum += adding_debit;
            changeSharedPreferenceUserLong(DEBIT_SUM, debit_sum);
        }
    */
    public void setDebit_sum(long debit_sum) {
        this.debit_sum = debit_sum;
        Call<Void> call = NetworkService.getInstance().getJSONApi().updateDebit_sum(MY_USER_ID, debit_sum);
        executeVoid(call);
    }
}
