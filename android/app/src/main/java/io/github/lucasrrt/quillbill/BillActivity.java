package io.github.lucasrrt.quillbill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BillActivity extends AppCompatActivity {
    TextView hello;
    String billName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

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
    }
}
