package ro.pub.cs.systems.eim.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import kotlinx.coroutines.channels.Send;

public class MainActivity extends AppCompatActivity {
    private EditText pokemonName;
    private Button requestButton;
    private ImageView image;
    private TextView ability;

    private ServerThread serverThread = null;
    private ClientThread clientThread = null;

    private SendRequestButtonClickListener sendRequestButtonClickListener = new SendRequestButtonClickListener();

    private class SendRequestButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {

            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = pokemonName.getText().toString();
            if (name == null || name.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Parameter from client (pokemonName) should be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            ability.setText("");

            clientThread = new ClientThread(name, ability);
            clientThread.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        pokemonName = (EditText)findViewById(R.id.pokemonName);
        requestButton = (Button)findViewById(R.id.request);
        image = (ImageView)findViewById(R.id.image);
        ability = findViewById(R.id.ability);

        requestButton.setOnClickListener(sendRequestButtonClickListener);

        serverThread = new ServerThread();
        if (serverThread.getServerSocket() == null) {
            Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not create server thread!");
            return;
        }
        serverThread.start();
    }

    @Override
    protected void onDestroy() {
        Log.i(Constants.TAG, "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
    }

}




