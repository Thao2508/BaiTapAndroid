package com.example.readmessage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ReadSmsActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1;

    private TextView smsTextView;
    private TextView directoryTextView;
    private TextView callLogTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sms);

        smsTextView = findViewById(R.id.smsTextView);
        directoryTextView = findViewById(R.id.directoryTextView);
        callLogTextView = findViewById(R.id.callLogTextView);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CONTACTS},
                    PERMISSION_CODE);
        } else {

            readData();
        }
    }

    private void readData() {
        readSms();
        readCallLog();
        readContacts();
    }


    @SuppressLint("Range")
    private void readSms() {
        ContentResolver contentResolver = getContentResolver();
        Uri smsUri = Telephony.Sms.Inbox.CONTENT_URI;
        Cursor cursor = contentResolver.query(smsUri, null, null, null, null);

        StringBuilder smsBuilder = new StringBuilder();
        if (cursor != null && cursor.moveToFirst()) {
            smsBuilder.append("=== Tin nhắn SMS ===\n");
            do {
                String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
                String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
                smsBuilder.append("Từ: ").append(address).append("\nNội dung: ").append(body).append("\n\n");
            } while (cursor.moveToNext());
            cursor.close();
        }
        smsTextView.setText(smsBuilder.toString());
    }


    @SuppressLint("Range")
    private void readCallLog() {
        ContentResolver contentResolver = getContentResolver();
        Uri callLogUri = CallLog.Calls.CONTENT_URI;
        Cursor cursor = contentResolver.query(callLogUri, null, null, null, null);

        StringBuilder callLogBuilder = new StringBuilder();
        if (cursor != null && cursor.moveToFirst()) {
            callLogBuilder.append("=== Lịch sử Cuộc Gọi ===\n");
            do {
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                String type = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                callLogBuilder.append("Số: ").append(number)
                        .append("\nLoại: ").append(type)
                        .append("\nNgày: ").append(date).append("\n\n");
            } while (cursor.moveToNext());
            cursor.close();
        }
        callLogTextView.setText(callLogBuilder.toString());
    }


    @SuppressLint("Range")
    private void readContacts() {
        ContentResolver contentResolver = getContentResolver();
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = contentResolver.query(contactsUri, null, null, null, null);

        StringBuilder contactsBuilder = new StringBuilder();
        if (cursor != null && cursor.moveToFirst()) {
            contactsBuilder.append("=== Danh Bạ ===\n");
            do {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contactsBuilder.append("Tên: ").append(name).append("\n\n");
            } while (cursor.moveToNext());
            cursor.close();
        }
        directoryTextView.setText(contactsBuilder.toString());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            boolean allPermissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                readData();
            } else {
                smsTextView.setText("Không có quyền truy cập dữ liệu.");
                directoryTextView.setText("Không có quyền truy cập dữ liệu.");
                callLogTextView.setText("Không có quyền truy cập dữ liệu.");
            }
        }
    }
}
