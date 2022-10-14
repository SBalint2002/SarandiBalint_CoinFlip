package hu.petrik.coinflip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private TextView dobasok, gyozelem, vereseg;
    private Button iras, fej;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        dobasok = findViewById(R.id.dobas);
        gyozelem = findViewById(R.id.gyozelem);
        vereseg = findViewById(R.id.vereseg);
        fej = findViewById(R.id.fej);
        iras = findViewById(R.id.iras);
    }
}