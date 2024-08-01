package com.example.labsum8910;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.labsum8910.SQLite.Adapters.CongViecAdapter;
import com.example.labsum8910.SQLite.DatabaseConfig.Database;
import com.example.labsum8910.SQLite.Model.CongViec;

import java.util.ArrayList;

public class SQLiteMainActivity extends AppCompatActivity {
    Database database;
    ListView lvCV;
    ArrayList<CongViec> arrayCV;
    CongViecAdapter adapter;
    ImageView iv_Back;

    boolean isValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqlite_main);

        iv_Back = (ImageView) findViewById(R.id.imageViewBackSQLite);
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SQLiteMainActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        lvCV = (ListView) findViewById(R.id.listviewCV);
        arrayCV = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.activity_cong_viec_row, arrayCV);
        lvCV.setAdapter(adapter);

        database = new Database(this, "Lab9.sqlite", null, 1);

        database.QueryData("Create table if not exists CongViec(id Integer Primary Key Autoincrement," + "TenCV nvarchar(200))");

//        database.QueryData("Insert into CongViec values(null , 'Project Android')");
//        database.QueryData("Insert into CongViec values(null , 'Design App')");

        GetDataCongViec();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_cv, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAdd){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_cv);

        EditText edtCV = (EditText) dialog.findViewById(R.id.edittextTenCV);
        Button btnAdd = (Button) dialog.findViewById(R.id.buttonThem);
        Button btnCancel = (Button) dialog.findViewById(R.id.buttonHuy);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenCV = edtCV.getText().toString().trim();

                if (TextUtils.isEmpty(tenCV)) {
                    Toast.makeText(SQLiteMainActivity.this, "Vui lòng nhập lại tên công việc", Toast.LENGTH_SHORT).show();
                }else{
                    database.QueryData("Insert into CongViec values(null, '"+tenCV+"')");
                    Toast.makeText(SQLiteMainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void GetDataCongViec(){
        Cursor dataCongViec = database.GetData("Select * from CongViec");
        arrayCV.clear();

        while (dataCongViec.moveToNext()){
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            arrayCV.add(new CongViec(id,ten));
        }

        adapter.notifyDataSetChanged();
    }

    public void DialogSuaCongViec(String name, int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edtCV = (EditText) dialog.findViewById(R.id.edittextTenCVMoi);
        Button btnEdit = (Button) dialog.findViewById(R.id.buttonSua);
        Button btnCancel = (Button) dialog.findViewById(R.id.buttonHuySua);

        edtCV.setText(name);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenCVMoi = edtCV.getText().toString().trim();

                if (TextUtils.isEmpty(tenCVMoi)) {
                    Toast.makeText(SQLiteMainActivity.this, "Vui lòng nhập lại tên công việc", Toast.LENGTH_SHORT).show();
                }else{
                    database.QueryData("Update CongViec set TenCV = '" + tenCVMoi +"' Where id = '" + id + "'");
                    Toast.makeText(SQLiteMainActivity.this, "Đã sửa", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogXoaCongViec(String name, int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa công việc "+ name +"không ?");
        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("Delete from CongViec Where id ='"+id+"'");
                Toast.makeText(SQLiteMainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });

        dialogXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}
