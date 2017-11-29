package com.example.android.justjava;

import java.text.NumberFormat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private EditText nameText;
    private boolean addWhippedCream = false;
    private boolean addChocolate = false;

    private final static double BASE_PRICE = 4.25;
    private final static double WHIPPED_CREAM_PRICE = 1.0;
    private final static double CHOCOLATE_PRICE = 4.25;
    private final static int MAX_QUANTITY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nameText = ((EditText) findViewById(R.id.customer_edit_text_view));
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (this.quantity != this.MAX_QUANTITY) {
            displayQuantity(++this.quantity);
        }else{
            Toast.makeText(this, this.getString(R.string.over), Toast.LENGTH_SHORT ).show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (this.quantity - 1 >= 0) {
            displayQuantity(--this.quantity);
        }else{
            Toast.makeText(this, this.getString(R.string.negative), Toast.LENGTH_SHORT ).show();
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        this.addWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants whipped cream topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        this.addChocolate = chocolateCheckBox.isChecked();

        // Calculate the price
        double price = calculatePrice();

        // Display the order summary on the screen
        if (price > 0) {
            String message = createOrderSummary(price, addWhippedCream, addChocolate);
            displayMessage(message);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private double calculatePrice() {
        double total = this.BASE_PRICE;
        if(this.addWhippedCream){
            total += this.WHIPPED_CREAM_PRICE;
        }
        if(this.addChocolate){
            total += this.CHOCOLATE_PRICE;
        }
        return quantity * total;
    }

    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(double price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = this.getString(R.string.order_summary_name) + this.nameText.getText().toString();
        priceMessage += "\n" + this.getString(R.string.order_summary_whipped_cream) + addWhippedCream;
        priceMessage += "\n" + this.getString(R.string.order_summary_chocolate) + addChocolate;
        priceMessage += "\n" + this.getString(R.string.order_summary_quantity) + quantity;
        priceMessage += "\n" + this.getString(R.string.order_summary_price) + NumberFormat.getCurrencyInstance().format(price);
        priceMessage += "\n" + this.getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfCoffees));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.order_summary_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}