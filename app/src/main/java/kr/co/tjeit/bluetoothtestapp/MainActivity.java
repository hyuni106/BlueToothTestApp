package kr.co.tjeit.bluetoothtestapp;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private Button btnConnect;
    private TextView txtResult;

    final int REQUEST_ENABLE_BT = 1;
    BeaconManager beaconManager;
    Region region;
    boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtResult = (TextView) findViewById(R.id.txt_Result);
        this.btnConnect = (Button) findViewById(R.id.btn_Connect);


//        beaconManager = new BeaconManager(this);
//
//        // add this below:
//        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
//            @Override
//            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
//                if (!list.isEmpty()) {
//                    Beacon nearestBeacon = list.get(0);
//                    Log.d("Airport", "Nearest places: " + nearestBeacon.getRssi());
//                    tvId.setText(nearestBeacon.getRssi() + "");
//                }
//            }
//        });
//
//        region = new Region("ranged region",
//                UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"), null, null); // 본인이 연결할 Beacon의 ID와 Major / Minor Code를 알아야 한다.

//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT)
//                        .show();
//            }
//
//
//        };
//
//
//        TedPermission.with(this)
//                .setPermissionListener(permissionlistener)
//                .setRationaleTitle("권한")
//                .setRationaleMessage("권한설정")
//                .setDeniedTitle("Permission denied")
//                .setDeniedMessage(
//                        "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                .setGotoSettingButtonText("bla bla")
//                .setPermissions(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN)
//                .check();


//        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        if (mBluetoothAdapter == null) {
//            //장치가 블루투스를 지원하지 않는 경우.
//            Toast.makeText(this, "지원하지 않는 기기", Toast.LENGTH_SHORT).show();
//        } else {
//            // 장치가 블루투스를 지원하는 경우.
//            if (!mBluetoothAdapter.isEnabled()) {
//                Toast.makeText(this, "비활성 : " + mBluetoothAdapter.isEnabled(), Toast.LENGTH_SHORT).show();
//                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//            } else {
//                Toast.makeText(this, "활성 : " + mBluetoothAdapter.isEnabled(), Toast.LENGTH_SHORT).show();
//            }
//        }

//        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
//            @Override
//            public void onBeaconsDiscovered(Region region, List list) {
//                if (!list.isEmpty()) {
//                    Beacon nearestBeacon = (Beacon) list.get(0);
//                    Log.d("Airport", "Nearest places: " + nearestBeacon.getRssi());
//                    Toast.makeText(MainActivity.this, "연결", Toast.LENGTH_SHORT).show();
////                    tvId.setText(nearestBeacon.getRssi() + "");
//                } else {
//                    Toast.makeText(MainActivity.this, "연결되지 않음", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        region = new Region("ranged region", UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"), null, null);
//        // 본인이 연결할 Beacon의 ID와 Major / Minor Code를 알아야 한다.


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (region != null) {
                    beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
                        @Override
                        public void onEnteredRegion(Region region, List<Beacon> list) {
                            Toast.makeText(MainActivity.this, "비콘 연결", Toast.LENGTH_SHORT).show();
//                showNotification("들어옴", "비콘 연결됨" + list.get(0).getRssi());
                            // getApplicationContext().startActivity(new Intent(getApplicationContext(), PopupActivity.class).putExtra("uuid", String.valueOf(list.get(0).getProximityUUID()) ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                        @Override
                        public void onExitedRegion(Region region) {
                            Toast.makeText(MainActivity.this, "연결 끊김", Toast.LENGTH_SHORT).show();
//                    showNotification("나감", "비콘 연결끊김");
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "비콘 없음", Toast.LENGTH_SHORT).show();
                }

//                beaconManager.setRangingListener(new BeaconManager.RangingListener() {
//                    @Override
//                    public void onBeaconsDiscovered(Region region, List list) {
//                        if (!list.isEmpty()) {
//                            Beacon nearestBeacon = (Beacon) list.get(0);
//                            Log.d("Airport", "Nearest places: " + nearestBeacon.getRssi());
//
//                            Toast.makeText(MainActivity.this, "연결", Toast.LENGTH_SHORT).show();
////                    Toast.makeText(mContext, "수신 강도 : " + nearestBeacon.getRssi(), Toast.LENGTH_SHORT).show();
////                            floorTxt.setText(nearestBeacon.getRssi() + "");
//
//                            // 수신강도가 -70 이상일때 알림창을 띄운다.
//                            if (!isConnected && nearestBeacon.getRssi() > -70) {
//                                isConnected = true;
//                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//                                dialog.setTitle("알림").setMessage("비콘이 연결되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                    }
//                                }).create().show();
//                            } else if (isConnected && nearestBeacon.getRssi() < -70) {
//                                Toast.makeText(MainActivity.this, "연결이 끊어졌습니다.", Toast.LENGTH_SHORT).show();
//                                isConnected = false;
//                            } else {
//                                Toast.makeText(MainActivity.this, "연결되지 않음", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//                region = new Region("ranged region", UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"), null, null);


//                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
//                if(pairedDevices.size() > 0) {
//                    // 페어링 된 장치가 있는 경우.
//                    Toast.makeText(MainActivity.this, "연결된 장치 존재", Toast.LENGTH_SHORT).show();
//                }
//
//                else {
//                    // 페어링 된 장치가 없는 경우.
//                    Toast.makeText(MainActivity.this, "장치 검색", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 블루투스 권한 승낙 및 블루투스 활성화
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

//        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                beaconManager.startRanging(region);
//            }
//        });
    }

    @Override
    protected void onPause() {
        //beaconManager.stopRanging(region);

        super.onPause();
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case REQUEST_ENABLE_BT:
//                if (resultCode == RESULT_OK) {
//                    // 블루투스가 활성 상태로 변경됨
//                    Toast.makeText(this, "블루투스 활성 상태로 변경", Toast.LENGTH_SHORT).show();
//                } else if (resultCode == RESULT_CANCELED) {
//                    // 블루투스가 비활성 상태임
//                    Toast.makeText(this, "블루투스 비활성 상태", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
