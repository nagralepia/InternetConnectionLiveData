package user.example.internetconnectioncheck;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.Toast;

import user.example.internetconnectioncheck.common.internetconnectionchk.ConnectionLiveData;
import user.example.internetconnectioncheck.common.internetconnectionchk.model.ConnectionModel;

public class MainActivity extends AppCompatActivity {
    public static final int MobileData = 2;
    public static final int WifiData = 1;

//    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(this, new Observer<ConnectionModel>() {
            @Override
            public void onChanged(@Nullable ConnectionModel connection) {
                if (connection.getIsConnected()) {
                    switch (connection.getType()) {
                        case WifiData:
                            Toast.makeText(MainActivity.this, String.format("Wifi turned ON"),Toast.LENGTH_SHORT).show();
                            break;
                        case MobileData:
                            Toast.makeText(MainActivity.this, String.format("Mobile data turned ON"), Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {
                    Toast.makeText(MainActivity.this, String.format("Connection turned OFF"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /* required to make activity life cycle owner */
//    @Override
//    public LifecycleRegistry getLifecycle() {
//        return lifecycleRegistry;
//    }



}