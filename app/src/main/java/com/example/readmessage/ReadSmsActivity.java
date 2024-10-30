package com.example.readmessage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ReadSmsActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_CODE = 1;
    private TextView smsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sms);

        smsTextView = findViewById(R.id.smsTextView);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS}, SMS_PERMISSION_CODE);
        } else {

            readSms();
        }
    }

    // Hàm đọc tin nhắn SMS từ thiết bị
    private void readSms() {
        ContentResolver contentResolver = getContentResolver();
        Uri smsUri = Telephony.Sms.Inbox.CONTENT_URI;
        Cursor cursor = contentResolver.query(smsUri, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder smsBuilder = new StringBuilder();
            do {

                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
                @SuppressLint("Range") String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
                smsBuilder.append("Từ: ").append(address).append("\nNội dung: ").append(body).append("\n\n");
            } while (cursor.moveToNext());

            smsTextView.setText(smsBuilder.toString());
            cursor.close();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                readSms();
            } else {
                smsTextView.setText("Không có quyền truy cập tin nhắn SMS.");
            }
        }
    }
}
