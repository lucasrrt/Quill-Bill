package io.github.lucasrrt.quillbill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BillActivity extends AppCompatActivity {
    TextView hello, balance;
    EditText expenseValue, expenseName;
    String billName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        balance = (TextView) findViewById(R.id.balance);
        expenseName = (EditText) findViewById(R.id.expenseName);
        expenseValue = (EditText) findViewById(R.id.expenseValue);

        hello = (TextView) findViewById(R.id.hello);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                billName = "Null";
            } else {
                billName = extras.getString("BILL");
            }
        } else {
            billName = (String) savedInstanceState.getSerializable("BILL");
        }
        hello.setText("Bill: "+billName);

        getBalance();

    }

    public void getBalance(){
        //TODO get total balance
        double balanceAmount = 20.00;
        balance.setText("R$ "+String.format("%.2f", balanceAmount));
    }

    public void addExpense(View view){
        //TODO AJAXCall to expenses
        String name = expenseName.getText().toString();
        double value = Double.parseDouble(expenseValue.getText().toString());
        Toast.makeText(this, name+" : "+value, Toast.LENGTH_SHORT).show();
    }

    public void showExpenses(View view){
        //TODO Show all expenses of a bill
    }
}
