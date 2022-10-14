package hu.petrik.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView dobasok, gyozelem, vereseg;
    private Button iras, fej;

    //0 - fej
    private int tipp = 0;
    private int dobott = 0;

    private Random r = new Random();
    private ImageView kep;
    private int dobasokSzama = 0;
    private int win = 0;
    private int lose = 0;
    private int korok = 0;
    private AlertDialog.Builder vege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        fej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp = 0;
                dobas();
            }
        });

        iras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp = 1;
                dobas();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void dobas() {
        dobott = r.nextInt(2);
        if (dobott == 0) {
            kep.setImageResource(R.drawable.heads);
            Toast.makeText(MainActivity.this, "Dobás eredménye: Fej", Toast.LENGTH_SHORT).show();
            dobasokSzama++;
            dobasok.setText("Dobások: " + dobasokSzama);

        } else {
            kep.setImageResource(R.drawable.tails);
            Toast.makeText(MainActivity.this, "Dobás eredménye: Írás", Toast.LENGTH_SHORT).show();
            dobasokSzama++;
            dobasok.setText("Dobások: " + dobasokSzama);
        }
        if (tipp == dobott) {
            win++;
            gyozelem.setText("Győzelem: " + win);
        } else {
            lose++;
            vereseg.setText("Vereség: " + lose);
        }
        korok++;
        if (korok == 5) {
            vege = new AlertDialog.Builder(MainActivity.this);
            if (lose > win) {
                vege.setTitle("Vereség");
            } else {
                vege.setTitle("Győzelem");
            }
            vege.setMessage("Szeretne új játékot játszani?");
            vege.setCancelable(false);
            vege.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            vege.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    lose = 0;
                    win = 0;
                    kep.setImageResource(R.drawable.heads);
                    vereseg.setText("Vereség: " + lose);
                    gyozelem.setText("Győzelem: " + win);
                    korok = 0;
                    dobasokSzama = 0;
                    dobasok.setText("Dobások: " + dobasokSzama);
                }
            });
            vege.create().show();
        }
    }

    private void init() {
        dobasok = findViewById(R.id.dobasok);
        gyozelem = findViewById(R.id.gyozelem);
        vereseg = findViewById(R.id.vereseg);
        fej = findViewById(R.id.fej);
        iras = findViewById(R.id.iras);
        kep = findViewById(R.id.kep);
    }
}