 package com.emrecinar.xxxx.aa.xa.x.restaurantfinderworkbench

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

 class Register : AppCompatActivity() {

     lateinit var RegisterButton : Button;

     lateinit var Username : EditText;
     lateinit var Password : EditText;
     lateinit var Password_Verify : EditText;

     lateinit var UsernameHolder: String;
     lateinit var PasswordHolder: String;

     lateinit var SQLiteDatabaseQueryHolder : String;
     lateinit var sqLiteDatabaseObj : SQLiteDatabase;
     lateinit var sqLiteHelper : SQLiteHelper;

     var EditTextEmptyHolder : Boolean? = null;

     lateinit var cursor : Cursor;

     var F_Result = "Not_Found";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        RegisterButton = findViewById(R.id.RegisterRegister);

        Username = findViewById(R.id.registerUsername);
        Password = findViewById(R.id.registerPassword);
        Password_Verify = findViewById(R.id.registerVerifyPassword);

        sqLiteHelper = SQLiteHelper(this);

        RegisterButton.setOnClickListener {

            SQLiteDataBaseBuild()

            SQLiteTableBuild()

            CheckEditTextStatus()

            CheckingEmailAlreadyExistsOrNot()

            Confirm()

            EmptyEditTextAfterDataInsert()

        }
    }

     fun Confirm() {

     }

     fun SQLiteDataBaseBuild() {

         sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE,null)

     }

     fun SQLiteTableBuild() {

         sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID
          + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Username + " VARCHAR, " + SQLiteHelper.Table_Column_2_Password
          + " VARCHAR);");

     }

     fun InsertDataIntoSQLiteDatabase() {

         if(EditTextEmptyHolder == true) {

             SQLiteDatabaseQueryHolder = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (username,password) VALUES('" + UsernameHolder + "', '" + PasswordHolder + "');";

             sqLiteDatabaseObj.execSQL(SQLiteDatabaseQueryHolder);

             sqLiteDatabaseObj.close();

             Toast.makeText(this, "User Registered Successfully!!", Toast.LENGTH_LONG).show();
         }

         else {

             Toast.makeText(this, "Please Fill All The Fields!!", Toast.LENGTH_LONG).show();

         }

     }

     fun EmptyEditTextAfterDataInsert() {

         Username.text.clear();

         Password.text.clear();

         Password_Verify.text.clear();

     }

     fun CheckEditTextStatus() {

         UsernameHolder = Username.text.toString()

         PasswordHolder = Password.text.toString()

         EditTextEmptyHolder =
             !(TextUtils.isEmpty(UsernameHolder) || TextUtils.isEmpty(PasswordHolder))

     }


     fun CheckingEmailAlreadyExistsOrNot() {

         sqLiteDatabaseObj = sqLiteHelper.writableDatabase;

         cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_1_Username + "=?", arrayOf(UsernameHolder), null,null,null);

         while (cursor.moveToNext()) {

             if(cursor.isFirst) {

                 cursor.moveToFirst();

                 F_Result = "Username Found"

                 cursor.close()
             }
         }

         CheckFinalResult()
     }

     fun CheckFinalResult() {

         if(F_Result.equals("Username Found", ignoreCase = true)) {

             Toast.makeText(applicationContext, "Email Already Exists", Toast.LENGTH_LONG).show()

         }
         else {

             InsertDataIntoSQLiteDatabase();
         }

         F_Result = "Not_Found"
     }
}