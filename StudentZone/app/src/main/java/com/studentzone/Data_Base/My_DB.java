package com.studentzone.Data_Base;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Switch;
import android.widget.Toast;

import com.studentzone.Login_Classes.Login_Activities.LoginActivity;

import java.util.ArrayList;

public class My_DB extends SQLiteOpenHelper {

    /** Declaration and initiation of My_DB
    ***********************************************************************************************/
    public static final String DB_Name="Education";
    public static final int DB_Version=6;

    private Context context;

    /** Declaration and initiation of Students table
    ***********************************************************************************************/
    public static final String Education_Table_Students="Students" ;
    public static final String Student_col_id="id" ;
    public static final String Student_col_academic_number="academic_number" ;
    public static final String Student_col_first_name="first_name" ;
    public static final String Student_col_last_name="last_name" ;
    public static final String Student_col_gender="gender" ;
    public static final String Student_col_email="email" ;
    public static final String Student_col_password="password" ;


    /** Declaration and initiation of Doctors table
    ***********************************************************************************************/
    public static final String Education_Table_Doctors="Doctors" ;
    public static final String Doctors_col_id="id" ;
    public static final String Doctors_col_gender="gender" ;
    public static final String Doctors_col_first_name="first_name" ;
    public static final String Doctors_col_last_name="last_name" ;
    public static final String Doctors_col_email="email" ;
    public static final String Doctors_col_password="password" ;
    public static final String Doctors_col_phone="phone" ;

    /** Declaration and initiation of Admins table
    ***********************************************************************************************/
    public static final String Education_Table_Admins="Admins" ;
    public static final String Admin_col_id="id" ;
    public static final String Admin_col_name="name" ;
    public static final String Admin_col_email="email" ;
    public static final String Admin_col_password="password" ;

    /**  Declaration and initiation of Departments table
    ***********************************************************************************************/
    public static final String Education_Table_Departments="Departments" ;
    public static final String Department_col_id="id" ;
    public static final String Department_col_name="name" ;
    public static final String Department_col_code="code" ;

    /**  Declaration and initiation of courses table
    ***********************************************************************************************/
    public static final String Education_Table_Courses="Courses" ;
    public static final String Courses_col_id="id" ;
    public static final String Courses_col_name="name" ;
    public static final String Courses_col_code="code" ;
    public static final String Courses_col_doctor_id="course_doctor_id" ;
    public static final String Courses_col_department_id="course_department_id" ;
    public static final String Courses_col_PreRequest_id="PreRequests_id" ;  // may be change to preRequests_name

    /** Declaration and initiation of Enrollment table
    ***********************************************************************************************/

    public static final String Education_Table_Enrollment="Enrollment" ;
    public static final String Enrollment_col_id="id" ;
    public static final String Enrollment_col_student_id="enrollment_student_id" ;
    public static final String Enrollment_col_course_id="enrollment_course_id" ;


    /**  Declaration and initiation of Absence table
    ***********************************************************************************************/
    public static final String Education_Table_Absence="Absence" ;
    public static final String Absence_col_id="id" ;
    public static final String Absence_col_student_id="absence_student_id" ;
    public static final String Absence_col_course_id="absence_course_id" ;
    public static final String Absence_col_date="absence_date" ;
    public static final String Absence_col_status="absence_status" ;


    /** My_DB()
    * Constructor Takes Context(Activity, Fragment,..)
    ***********************************************************************************************/
    public  My_DB(Context context)
    {super(context,DB_Name,null, DB_Version);this.context = context;}


    /**onCreate()
     * This Method Called when the database is created for the first time.
     * This is where the creation of tables and the initial population of the
     * tables should happen.
     * @param db The database.
     **********************************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+Education_Table_Students+" ("
                + ""+Student_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ""+Student_col_academic_number+" INTEGER UNIQUE,"
                + ""+Student_col_first_name+" TEXT,"
                + ""+Student_col_last_name+" TEXT,"    //Addition+++++++++++++++++++++++++++++++++
                + ""+Student_col_gender+" TEXT,"
                + ""+Student_col_email+" TEXT UNIQUE NOT NULL CHECK(email LIKE '%.edu%'),"
                + ""+Student_col_password+" TEXT)");    // Should Be Not Null

        db.execSQL("CREATE TABLE "+Education_Table_Doctors+" ("
                + ""+Doctors_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ""+Doctors_col_first_name+" TEXT,"
                + ""+Doctors_col_last_name+" TEXT,"
                + ""+Doctors_col_gender+" TEXT,"
                + ""+Doctors_col_phone+" TEXT UNIQUE,"  //Addition+++++++++++++++++++++++++++++++++
                + ""+Doctors_col_email+" TEXT UNIQUE NOT NULL CHECK("+Doctors_col_email+" LIKE '%.edu'),"
                + ""+Doctors_col_password+" TEXT)");    //Should Be NOT NULL

        db.execSQL("CREATE TABLE "+Education_Table_Admins+" ("
                + ""+Admin_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ""+Admin_col_name+" TEXT,"
                + ""+Admin_col_email+" TEXT UNIQUE NOT NULL CHECK("+Admin_col_email+" LIKE '%.edu'),"
                +""+Admin_col_password+" TEXT)");

        db.execSQL("CREATE TABLE "+Education_Table_Departments+" ("
                + ""+Department_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ""+Department_col_name+" TEXT UNIQUE,"
                +""+Department_col_code+" TEXT UNIQUE)");

        db.execSQL("CREATE TABLE "+Education_Table_Courses+" ("
                + ""+Courses_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ""+Courses_col_code+" TEXT UNIQUE,"
                + ""+Courses_col_name+" TEXT,"
                + ""+Courses_col_PreRequest_id+" INTEGER,"    // May Be Change To Name
                + ""+Courses_col_department_id+" INTEGER,"
                + ""+Courses_col_doctor_id+" INTEGER,"
                + "FOREIGN KEY("+Courses_col_department_id+") REFERENCES Departmen("+Department_col_id+"),"
                + "FOREIGN KEY("+Courses_col_doctor_id+") REFERENCES Doctors("+Doctors_col_id+"))");

        db.execSQL("CREATE TABLE "+Education_Table_Enrollment+" ("
                + ""+Enrollment_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ""+Enrollment_col_student_id+" INTEGER,"
                + ""+Enrollment_col_course_id+" INTEGER,"
                + "FOREIGN KEY("+Enrollment_col_student_id+") REFERENCES Students("+Student_col_id+"),"
                + "FOREIGN KEY("+Enrollment_col_course_id+") REFERENCES Courses("+Courses_col_id
                +"))");

        db.execSQL("CREATE TABLE "+Education_Table_Absence+" ("
                + ""+Absence_col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ""+Absence_col_student_id+" INTEGER,"
                + ""+Absence_col_course_id+" INTEGER,"
                + ""+Absence_col_date+" TEXT,"
                + ""+Absence_col_status+" TEXT,"
                + "FOREIGN KEY("+Absence_col_student_id+") REFERENCES Students("+Student_col_id+"),"
                + "FOREIGN KEY("+Absence_col_course_id+") REFERENCES Courses("+Courses_col_id+"))");


        /**Insert Sample Data
         ******************************************************************************************/
        db.execSQL("INSERT INTO "+Education_Table_Admins+" ("+Admin_col_name+", "+Admin_col_email+", "+Admin_col_password+")" + " VALUES ('Jon', 'jon.edu', '10')");

        db.execSQL("INSERT INTO "+Education_Table_Doctors+" ("+Doctors_col_first_name+", "+Doctors_col_last_name+", " +Doctors_col_gender +","+Doctors_col_phone+", "+Doctors_col_email+", "+Doctors_col_password+")" + " VALUES ('Hamad', 'Ahmed','Male', '01220403050','hamad.edu', '100')");


        db.execSQL("INSERT INTO "+Education_Table_Students+" ("+Student_col_academic_number+","+Student_col_first_name+"," + " "+Student_col_last_name+"," + " "+Student_col_gender+", "+Student_col_email+", "+Student_col_password+")" + " VALUES (1000,'Ahmed', 'Shosha','Male', 'ahmed.edu', '1000')");

        db.execSQL("INSERT INTO "+Education_Table_Students+" ("+Student_col_academic_number+","+Student_col_first_name+", " + ""+Student_col_last_name+", "+Student_col_gender+", "+Student_col_email+", "+Student_col_password+")" + " VALUES (2000,'Adam', 'Mohamed','Male', 'adam.edu', '2000')");
    }

    /**onUpgrade()
     * This Method Called when the database needs to be upgraded.
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     **********************************************************************************************/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Education_Table_Students);
        db.execSQL("DROP TABLE IF EXISTS "+ Education_Table_Doctors);
        db.execSQL("DROP TABLE IF EXISTS "+ Education_Table_Admins);
        db.execSQL("DROP TABLE IF EXISTS "+ Education_Table_Departments);
        db.execSQL("DROP TABLE IF EXISTS "+ Education_Table_Courses);
        db.execSQL("DROP TABLE IF EXISTS "+ Education_Table_Enrollment);
        db.execSQL("DROP TABLE IF EXISTS "+ Education_Table_Absence);
        onCreate(db);
    }



    /**checkLogin()
    *  This Method Return True = Account Found In My_DB or False = Not Found In My_DB.
    *  It's Search On Admins Tale Or In  Doctors Table Or On Student Table , UpOn Value Of
    *  @param kindCheckedId  (Admin, Doctor, Student)
    ***********************************************************************************************/
    public boolean checkLogin(int kindCheckedId, String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isValid = false;
        Cursor cursor = null;

        switch(kindCheckedId){
            case -1:
                return isValid;
            case 0:
                cursor = db.query(""+Education_Table_Admins+"", new String[] { Admin_col_id },
                        ""+Admin_col_email+"=? AND "+Admin_col_password+"=?", new String[] { email, password },
                        null, null, null, null);
                isValid = cursor.moveToFirst();
                break;
            case 1:
                cursor = db.query(""+Education_Table_Doctors+"", new String[] { Doctors_col_id},
                        ""+Doctors_col_email+"=? AND "+Doctors_col_password+"=?", new String[] { email, password },
                        null, null, null, null);
                isValid = cursor.moveToFirst();
                break;
            case 2:
                cursor = db.query(""+Education_Table_Students+"", new String[] { Student_col_id},
                        ""+Student_col_email+"=? AND "+Student_col_password+"=?", new String[] { email, password },
                        null, null, null, null);
                isValid = cursor.moveToFirst();
                break;
        }
        cursor.close();
//        db.close();
        return isValid;
    }
//__________________________________Departments Function_______________________________________________
    public void insert_department(String name, int code)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Department_col_name, name);
        values.put(Department_col_code, code);

        db.insert(Education_Table_Departments, null, values);
    }




}
