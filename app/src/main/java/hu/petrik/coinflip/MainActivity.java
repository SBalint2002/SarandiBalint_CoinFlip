package hu.petrik.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
                /*if (dobott == 0){
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out_fade_out));
                    kep.setImageResource(R.drawable.heads);
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in_fade_in));
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out_fade_out));
                    kep.setImageResource(R.drawable.tails);
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in_fade_in));
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out_fade_out));
                }else{
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out_fade_out));
                    kep.setImageResource(R.drawable.tails);
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in_fade_in));
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out_fade_out));
                    kep.setImageResource(R.drawable.heads);
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in_fade_in));
                    kep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out_fade_out));
                }*/
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
        RotateAnimation rotate = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF, .5f,RotateAnimation.RELATIVE_TO_SELF, .5f);
        rotate.setRepeatCount(1);
        rotate.setDuration(500);
        kep.startAnimation(rotate);
        if (dobott == 0) {
            kep.setImageResource(R.drawable.tails);
            kep.startAnimation(rotate);
            kep.setImageResource(R.drawable.heads);
            Toast.makeText(MainActivity.this, "Dobás eredménye: Fej", Toast.LENGTH_SHORT).show();
            dobasokSzama++;
            dobasok.setText("Dobások: " + dobasokSzama);

        } else {
            kep.setImageResource(R.drawable.heads);
            kep.startAnimation(rotate);
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
        if (korok == 5 || (win == 3 && lose < 2) || (lose == 3 && win < 2)) {
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