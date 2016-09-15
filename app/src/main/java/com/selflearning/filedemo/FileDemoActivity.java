package com.selflearning.filedemo;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.selflearning.PermissionUtil;
import com.selflearning.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileDemoActivity extends AppCompatActivity {

    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_demo);
        PermissionUtil permissionUtil = new PermissionUtil(this);
        permissionUtil.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionUtil.OnPermissionGranted() {
            @Override
            public void permissionGranted() {
                Toast.makeText(FileDemoActivity.this, "Write permission granted. Click write button", Toast.LENGTH_SHORT).show();
            }
        }, "Need external storage permission.");
        initComponents();
    }

    private void initComponents() {
        etMessage = (EditText) findViewById(R.id.etMessage);
    }

    public void onClickWriteButton(View view) {
        if (isExternalStorageAvailable()) {
            File file = getFile();
            String message = etMessage.getText().toString();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(message.getBytes());
                fileOutputStream.close();
                etMessage.setText("");
                Toast.makeText(FileDemoActivity.this, "Message saved",
                        Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "External storage is not available",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    private File getFile() {
        String directoryName = "/FileExample/";
        String fileName = "message1.txt";
        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File directory = new File(root.getAbsolutePath() + directoryName);
        if (!directory.exists()) {
            directory.mkdir();
            Toast.makeText(this, "Created new directory",
                    Toast.LENGTH_SHORT).show();
        }
        return new File(Environment.getExternalStoragePublicDirectory(Environment
                .DIRECTORY_DCIM).getAbsolutePath() + directoryName + fileName);
    }

    /**
     * Function is called when user click on read button
     *
     * @param view View
     */
    public void onClickReadButton(View view) {
        File file = getFile();
        String message;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((message = bufferedReader.readLine()) != null) {
                stringBuffer.append(message + "\n");
            }
            etMessage.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if external storage is available for read and write
     *
     * @return boolean
     */
    public boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Function to insert string at the top of the file
     *
     * @param view View
     */
    public void onClickInsertAtTopButton(View view) {
        try {
            int offset = 0;
            RandomAccessFile randomAccessFile = new RandomAccessFile(getFile(), "rw");
            RandomAccessFile randomAccessFileTemp = new RandomAccessFile(getFile() + "temp", "rw");
            long fileSize = randomAccessFile.length();
            FileChannel sourceChannel = randomAccessFile.getChannel();
            FileChannel targetChannel = randomAccessFileTemp.getChannel();
            sourceChannel.transferTo(offset, fileSize, targetChannel);
            sourceChannel.truncate(offset);
            randomAccessFile.seek(offset);
            randomAccessFile.write(etMessage.getText().toString().getBytes());
            long newOffset = randomAccessFile.getFilePointer();
            targetChannel.position(0L);
            sourceChannel.transferFrom(targetChannel, newOffset, (fileSize - offset));
            sourceChannel.close();
            targetChannel.close();
            randomAccessFileTemp.close();
            randomAccessFile.close();
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
            etMessage.setText("");
//            deleteTempFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTempFile() {
        File file = getFile();
        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(this, "File deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "File not deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}