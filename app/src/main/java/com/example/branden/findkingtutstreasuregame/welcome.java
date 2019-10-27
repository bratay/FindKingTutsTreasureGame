package com.example.branden.findkingtutstreasuregame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.IDNA;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void newGame(View v)
    {
        Intent creation = new Intent(this, CharacterCreation.class);

        //finish();
        startActivity(creation);
    }

    public void loadGame(View v)
    {
        //getting saved data from old game
        SharedPreferences load = getSharedPreferences("Saved progress", MODE_PRIVATE);

        if(load.getString("player name", "")== null){//check to see if a game was saved
            toaster("There is not a game to load",1500);
        }else {

            String charName = load.getString("player name", "");
            int charNum = load.getInt("character number", 0);
            int playerHP = load.getInt("Health", 0);
            int playerHunger = load.getInt("player hunger", 0);
            int actionCounter = load.getInt("action counter", 0);
            int playerAttack = load.getInt("player attack", 0);
            int playerdefense = load.getInt("player defense", 0);
            //getting inventory array
            boolean[] inventory = new boolean[6];
            for (int x = 0; x < 6; x += 1) {
                inventory[x] = load.getBoolean("boolitems" + "_" + x, true);
            }
            boolean inBattle = load.getBoolean("in battle", true);
            int enemyHealth = load.getInt("enemy health", 0);
            int enemyAttack = load.getInt("enemy attack", 0);
            int enemyNum = load.getInt("enemy number", 0);
            int enemysKilled = load.getInt("enemys killed", 0);
            int bubble = load.getInt("bubble", 0);
            int backgroundCounter = load.getInt("background counter",0);
            int repel = load.getInt("repel", 0);
            int goldMine = load.getInt("goldmine", 0);

            //send data to and open main Game
            Intent open = new Intent(welcome.this, MainGame.class);

            open.putExtra("The name", charName);
            open.putExtra("hunger", playerHunger);
            open.putExtra("attack power", playerAttack);
            open.putExtra("player HP", playerHP);
            open.putExtra("enemys killed", enemysKilled);
            open.putExtra("character number", charNum);
            open.putExtra("action counter", actionCounter);
            open.putExtra("player defence", playerdefense);
            open.putExtra("Inventory", inventory);
            for (int x = 0; x < 6; x += 1) {
                open.putExtra("inventory" + "_" + x, inventory[x]);
                //
                // inventory[x] = load.getBoolean("boolitems"+"_" + x,true);
            }
            open.putExtra("in battle", inBattle);
            open.putExtra("enemy attack", enemyAttack);
            open.putExtra("enemy health", enemyHealth);
            open.putExtra("enemy number", enemyNum);
            open.putExtra("bubble", bubble);
            open.putExtra("background counter", backgroundCounter);
            open.putExtra("repel", repel);
            open.putExtra("goldmine", goldMine);

            startActivity(open);
        }
    }

    public void toaster(String message, int length)
    {

        final Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, length);
    }
}