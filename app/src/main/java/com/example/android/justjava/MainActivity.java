package com.example.android.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private final static double COFFEE_PRICE = 4.25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void increment(View view) {
        if (this.quantity == Integer.MAX_VALUE) {
            this.quantity = 0;
        }
        displayQuantity(++this.quantity);
    }

    void decrement(View view) {
        if (this.quantity == 0) {
            this.quantity = 0;
            displayQuantity(this.quantity);
        } else {
            displayQuantity(--this.quantity);
        }
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private double calculatePrice(int quantity) {
        int price = quantity * COFFEE_PRICE;
        return price;
    }

    /**
     * This method is called when the order button is clicked.
     */
    void submitOrder(View view) {
        if (this.quantity * COFFEE_PRICE < Double.MAX_VALUE) {
            displayPrice((this.quantity * COFFEE_PRICE));
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(quantity));
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(double price) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        if (price > 0) {
            priceTextView.setText("Total: " + NumberFormat.getCurrencyInstance().format(price) + "\nThank you!");
        } else {
            priceTextView.setText(NumberFormat.getCurrencyInstance().format(0));
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}