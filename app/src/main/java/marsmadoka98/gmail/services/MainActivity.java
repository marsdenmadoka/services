package marsmadoka98.gmail.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
//starting the service
        Intent intent = new Intent(this, DelayedMessageService.class);//Create the intent.
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE,
                getResources().getString(R.string.button_response));//Add text to the intent the text to be displayed as the notification.
         startService(intent);//Start the service
    }
}
