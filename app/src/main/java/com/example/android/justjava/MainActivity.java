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

    public void increment(View view) {
        if (this.quantity == Integer.MAX_VALUE) {
            this.quantity = 0;
        }
        displayQuantity(++this.quantity);
    }

    public void decrement(View view) {
        if (this.quantity == 0) {
            this.quantity = 0;
            displayQuantity(this.quantity);
        } else {
            displayQuantity(--this.quantity);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if (this.quantity * COFFEE_PRICE < Double.MAX_VALUE) {
            displayPrice(calculatePrice());
        }
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private double calculatePrice() {
        return this.quantity * COFFEE_PRICE;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffee) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfCoffee));
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(double price) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        if (price > 0) {
            orderSummaryTextView.setText(createOrderSummary(price));
        } else {
            orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(0));
        }
    }

    private String createOrderSummary(double price){
        String customer = "Kaptain Kunal";
        return "Name: " + customer +
                "\nQuantity: " + this.quantity +
                "\nTotal: " + NumberFormat.getCurrencyInstance().format(price) +
                "\nThank you!";
    }

}