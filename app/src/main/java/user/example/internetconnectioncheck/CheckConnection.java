package user.example.internetconnectioncheck;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LifecycleRegistryOwner;
import androidx.lifecycle.Observer;

import user.example.internetconnectioncheck.common.internetconnectionchk.ConnectionLiveData;
import user.example.internetconnectioncheck.common.internetconnectionchk.model.ConnectionModel;

public class CheckConnection extends AppCompatActivity implements LifecycleRegistryOwner {

    public static final int MobileData = 2;
    public static final int WifiData = 1;

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Live data object and setting an oberser on it */
        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(this, new Observer<ConnectionModel>() {
            @Override
            public void onChanged(@Nullable ConnectionModel connection) {
                /* every time connection state changes, we'll be notified and can perform action accordingly */
                if (connection.getIsConnected()) {
                    switch (connection.getType()) {
                        case WifiData:
                            Toast.makeText(CheckConnection.this, String.format("Wifi turned ON"), Toast.LENGTH_SHORT).show();
                            break;
                        case MobileData:
                            Toast.makeText(CheckConnection.this, String.format("Mobile data turned ON"), Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {
                    Toast.makeText(CheckConnection.this, String.format("Connection turned OFF"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /* required to make activity life cycle owner */
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}