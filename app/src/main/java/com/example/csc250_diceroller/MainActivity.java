package com.example.csc250_diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView qtyTV;
    private TextView selectedDieTV;
    private TextView rollsTV;
    private TextView totalTV;
    private String currentQtyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.qtyTV = this.findViewById(R.id.qtyTV);
        this.selectedDieTV = this.findViewById(R.id.selectedDieTV);
        this.qtyTV.setText("");
        this.currentQtyText = "";
        this.rollsTV = this.findViewById(R.id.rollsTV);
        this.totalTV = this.findViewById(R.id.totalTV);
    }

    private String extractNumberOfSides(String diceType) {
        //take something that looks like "D20" and returns "4"
        String answer = "";
        for (int i = 1; i < diceType.length(); i++) {
            answer += diceType.charAt(i);
        }
        return answer;
    }

    public void onRollButtonPressed(View v) {
        //do we have everything we need to roll?
        String qtyString = this.qtyTV.getText().toString();
        String fullDiceString = this.selectedDieTV.getText().toString(); //like "D4" or "D6"
        //get the qty as an int
        String errorMsg = "";
        if (qtyString.length() == 0)
        {
            errorMsg = "You must enter a quantity before rolling";
        }
        else if(fullDiceString.length()== 0)
        {
            errorMsg= "You must select a die before rolling";
        }

        if (errorMsg.length()>0)
        {
            Toast t = Toast.makeText(this,errorMsg,Toast.LENGTH_LONG);
            t.show(); //show is an instance method
            //makeText is a class method because it's being named by a class
            // class names usually start with a capital
            //if we look up Toast.makeText we will see static by it
            // LENGTH_LONG is a variable and a field
            // constructors initially create instances
            return; //immediately end this method, don't try to do any rolling
        }
        int qtyInt = Integer.parseInt(qtyString);
        int[] theRolls = new int[qtyInt];

        //get the number of sides as an int

        String trimmedDiceString = this.extractNumberOfSides(fullDiceString);
        //String trimmerDiceString = fullDiceString.substring(1);
        int numberOfSidesInt = Integer.parseInt(trimmedDiceString);
        Random r = new Random();

        //I want to roll the dice qtyInt number of times and store
        //each roll in a different bucket of theRolls and set our
        //textView on the interface for the individual rolls approprately
        //as well as keep a running total and set that textView appropriately
        //as well.
        //FINISH HW HERE!

        int total = 0;
        String individualRolls = "";
        for(int i = 0; i < theRolls.length; i++)
        {
            theRolls[i] = r.nextInt(numberOfSidesInt)+1;
            total = total + theRolls[i];
            if(individualRolls.length() == 0)
            {
                individualRolls = "" + theRolls[i];
            }
            else
            {
                individualRolls = individualRolls + " + " + theRolls[i];
            }
        }

        this.rollsTV.setText(individualRolls);
        this.totalTV.setText("" + total);
    }



    public void diceButtonPressed(View v) {
        this.selectedDieTV.setText(v.getTag().toString());
    }

    public void clearButtonPressed(View v) {
        this.currentQtyText = "";
        this.qtyTV.setText(this.currentQtyText);
        this.totalTV.setText(this.currentQtyText);
        this.selectedDieTV.setText(this.currentQtyText);
        this.rollsTV.setText(this.currentQtyText);
    }

    public void qtyButtonPressed(View v) {
        Button b = (Button) v;

        if (this.currentQtyText.length() == 0 && b.getText().equals("0")) {
            return;
        }
        this.currentQtyText += b.getText();
        this.qtyTV.setText(this.currentQtyText);
    }
}