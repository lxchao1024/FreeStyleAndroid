package com.lxchao.app.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.lxchao.app.R;
import com.lxchao.app.utils.excel.ReadExcelPoi;

/**
 * 权限请求+Excel解析
 */
public class GiftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestPermissions(new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1000);
        }
    }

    private void initExcel() {
        try {
            ReadExcelPoi.copy(this, "hell.xlsx", getExternalCacheDir().getPath() + "/hell.xlsx");
            Thread.sleep(1400);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ReadExcelPoi.readXLSX(getExternalCacheDir().getPath() + "/hell.xlsx");

//        ReadExcelPoi readExcelPoi = new ReadExcelPoi(getExternalCacheDir().getPath() + "/hell.xlsx", (isTrue, listsContent) -> {
//            Log.e("sss", "content === " + listsContent.toString());
//        });
//        readExcelPoi.run();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initExcel();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
