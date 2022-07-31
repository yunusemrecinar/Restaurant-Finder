package com.emrecinar.xxxx.aa.xa.x.restaurantfinderworkbench

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.net.URL


class MainActivity : AppCompatActivity() {

    lateinit var LogInButton : Button;
    lateinit var RegisterButton : Button;

    lateinit var Username: EditText;
    lateinit var Password: EditText;

    lateinit var USERname: String;

    lateinit var UsernameHolder: String;
    lateinit var PasswordHolder: String;

    var EditTextEmptyHolder : Boolean? = null;

    lateinit var sqLiteDatabaseObj: SQLiteDatabase;
    lateinit var sqLiteHelper: SQLiteHelper;

    lateinit var cursor: Cursor;

    var TempPassword: String = "NOT_FOUND";
    val userName : String = ""
    lateinit var url: URL;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LogInButton = findViewById(R.id.buttonLogin);

        RegisterButton = findViewById(R.id.buttonRegister);

        Username = findViewById(R.id.editUsername);
        Password = findViewById(R.id.editPassword);

        sqLiteHelper = SQLiteHelper(this);

        LogInButton.setOnClickListener {

            CheckEditTextStatus()

            LoginFunction()

        }

        RegisterButton.setOnClickListener {
                val intent = Intent(applicationContext, Register::class.java);
                startActivity(intent);
        }
    }

    @SuppressLint("Range")
    fun LoginFunction() {

        if(EditTextEmptyHolder == true) {

            sqLiteDatabaseObj = sqLiteHelper.writableDatabase

            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_1_Username + "=?", arrayOf(UsernameHolder),null, null, null)

            while (cursor.moveToNext()) {

                if(cursor.isFirst) {

                    cursor.moveToFirst()

                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Password))
                    USERname = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Username))


                    cursor.close()
                }

            }

            CheckFinalResult()

        }else {

            Toast.makeText(applicationContext, "Please Enter Username and Password!!", Toast.LENGTH_LONG).show()
        }

    }

    fun CheckEditTextStatus() {

        UsernameHolder = Username.text.toString()
        PasswordHolder = Password.text.toString()

        EditTextEmptyHolder =
            !(TextUtils.isEmpty(UsernameHolder) || TextUtils.isEmpty(PasswordHolder))

    }

    fun CheckFinalResult() {

        if(TempPassword.equals(PasswordHolder, ignoreCase = true)) {

            Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_LONG).show()

            val intent = Intent(applicationContext, mainMenu::class.java)

            intent.putExtra(userName, USERname)

            startActivity(intent)

        }else {

            Toast.makeText(applicationContext, "Username or Password is Wrong, Please Try Again!!", Toast.LENGTH_LONG).show()

        }
        TempPassword = "Not_Found"
    }
}