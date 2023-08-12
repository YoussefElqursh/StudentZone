package com.studentzone.Data_Base;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.Toast;


import androidx.annotation.NonNull;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;
import java.util.Collections;

public class My_DB extends SQLiteOpenHelper {

    /**
     * Declaration and initiation of My_DB
     ***********************************************************************************************/
    public static final String DB_Name = "Education";

    public static final int DB_Version = 34;


    private final Context context;

    /**
     * Declaration and initiation of Students table
     ***********************************************************************************************/
    public static final String Education_Table_Students = "Students";
    public static final String Student_col_id = "id";
    public static final String Student_col_academic_number = "academic_number";
    public static final String Student_col_first_name = "first_name";
    public static final String Student_col_last_name = "last_name";
    public static final String Student_col_gender = "gender";
    public static final String Student_col_email = "email";
    public static final String Student_col_password = "password";


    /**
     * Declaration and initiation of Doctors table
     ***********************************************************************************************/
    public static final String Education_Table_Doctors = "Doctors";
    public static final String Doctors_col_id = "id";
    public static final String Doctors_col_gender = "gender";
    public static final String Doctors_col_first_name = "first_name";
    public static final String Doctors_col_last_name = "last_name";
    public static final String Doctors_col_email = "email";
    public static final String Doctors_col_password = "password";
    public static final String Doctors_col_phone = "phone";

    /**
     * Declaration and initiation of Admins table
     ***********************************************************************************************/
    public static final String Education_Table_Admins = "Admins";
    public static final String Admin_col_id = "id";
    public static final String Admin_col_name = "name";
    public static final String Admin_col_email = "email";
    public static final String Admin_col_password = "password";

    /**
     * Declaration and initiation of Departments table
     ***********************************************************************************************/
    public static final String Education_Table_Departments = "Departments";
    public static final String Department_col_id = "id";
    public static final String Department_col_name = "name";
    public static final String Department_col_code = "code";

    /**
     * Declaration and initiation of courses table
     ***********************************************************************************************/
    public static final String Education_Table_Courses = "Courses";
    public static final String Courses_col_id = "id";
    public static final String Courses_col_name = "name";
    public static final String Courses_col_code = "code";
    public static final String Courses_col_doctor_id = "course_doctor_id";
    public static final String Courses_col_department_id = "course_department_id";

    public static final String Courses_col_PreRequest_id = "PreRequests_id";  // may be changed to preRequests_name


    /**
     * Declaration and initiation of Enrollment table
     ***********************************************************************************************/

    public static final String Education_Table_Enrollment = "Enrollment";
    public static final String Enrollment_col_id = "id";
    public static final String Enrollment_col_student_id = "enrollment_student_id";
    public static final String Enrollment_col_course_id = "enrollment_course_id";
    public static final String Enrollment_col_student_grade = "enrollment_student_grade";
    public static final String Enrollment_col_student_degree = "enrollment_student_degree";


    /**
     * Declaration and initiation of Absence table
     ***********************************************************************************************/
    public static final String Education_Table_Absence = "Absence";
    public static final String Absence_col_id = "id";
    public static final String Absence_col_student_id = "absence_student_id";
    public static final String Absence_col_course_id = "absence_course_id";
    public static final String Absence_col_date = "absence_date";
    public static final String Absence_col_status = "absence_status";


    /**
     * My_DB()
     * Constructor Takes Context(Activity, Fragment,..)
     ***********************************************************************************************/
    public My_DB(Context context) {
        super(context, DB_Name, null, DB_Version);
        this.context = context;
    }

    /**
     * onCreate()
     * This Method Called when the database is created for the first time.
     * This is where the creation of tables and the initial population of the
     * tables should happen.
     *
     * @param db The database.
     **********************************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Education_Table_Students + " ("
                + "" + Student_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                + "" + Student_col_academic_number + " INTEGER UNIQUE,"

                + "" + Student_col_first_name + " TEXT,"
                + "" + Student_col_last_name + " TEXT,"    //Addition+++++++++++++++++++++++++++++++++
                + "" + Student_col_gender + " TEXT,"
                + "" + Student_col_email + " TEXT UNIQUE NOT NULL CHECK(email LIKE '%.edu%'),"
                + "" + Student_col_password + " TEXT)");    // Should Be Not Null

        db.execSQL("CREATE TABLE " + Education_Table_Doctors + " ("
                + "" + Doctors_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Doctors_col_first_name + " TEXT,"
                + "" + Doctors_col_last_name + " TEXT,"
                + "" + Doctors_col_gender + " TEXT,"
                + "" + Doctors_col_phone + " TEXT UNIQUE,"  //Addition+++++++++++++++++++++++++++++++++
                + "" + Doctors_col_email + " TEXT UNIQUE NOT NULL CHECK(" + Doctors_col_email + " LIKE '%.edu'),"
                + "" + Doctors_col_password + " TEXT)");    //Should Be NOT NULL

        db.execSQL("CREATE TABLE " + Education_Table_Admins + " ("
                + "" + Admin_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Admin_col_name + " TEXT,"
                + "" + Admin_col_email + " TEXT UNIQUE NOT NULL CHECK(" + Admin_col_email + " LIKE '%.edu'),"
                + "" + Admin_col_password + " TEXT)");

        db.execSQL("CREATE TABLE " + Education_Table_Departments + " ("
                + "" + Department_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Department_col_name + " TEXT UNIQUE,"
                + "" + Department_col_code + " TEXT UNIQUE)");

        db.execSQL("CREATE TABLE " + Education_Table_Courses + " ("
                + "" + Courses_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Courses_col_code + " TEXT UNIQUE,"
                + "" + Courses_col_name + " TEXT,"
                + "" + Courses_col_PreRequest_id + " INTEGER ,"    // May Be Change To Name
                + "" + Courses_col_department_id + " INTEGER NOT NULL ,"
                + "" + Courses_col_doctor_id + " INTEGER,"
                + "FOREIGN KEY(" + Courses_col_department_id + ") REFERENCES Departmen(" + Department_col_id + "),"
                + "FOREIGN KEY(" + Courses_col_doctor_id + ") REFERENCES Doctors(" + Doctors_col_id + "))");

        db.execSQL("CREATE TABLE " + Education_Table_Enrollment + " ("
                + "" + Enrollment_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Enrollment_col_student_id + " INTEGER,"
                + "" + Enrollment_col_course_id + " INTEGER,"
                + "" + Enrollment_col_student_grade + " Text,"
                + "" + Enrollment_col_student_degree + " INTEGER,"
                + "FOREIGN KEY(" + Enrollment_col_student_id + ") REFERENCES Students(" + Student_col_id + "),"
                + "FOREIGN KEY(" + Enrollment_col_course_id + ") REFERENCES Courses(" + Courses_col_id
                + "))");

        db.execSQL("CREATE TABLE " + Education_Table_Absence + " ("
                + "" + Absence_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Absence_col_student_id + " INTEGER,"
                + "" + Absence_col_course_id + " INTEGER,"
                + "" + Absence_col_date + " TEXT,"
                + "" + Absence_col_status + " TEXT,"
                + "FOREIGN KEY(" + Absence_col_student_id + ") REFERENCES Students(" + Student_col_id + "),"
                + "FOREIGN KEY(" + Absence_col_course_id + ") REFERENCES Courses(" + Courses_col_id + "))");


        /**Insert Sample Data
         ******************************************************************************************/
        db.execSQL("INSERT INTO " + Education_Table_Admins + " (" + Admin_col_name + ", " + Admin_col_email + ", " + Admin_col_password + ")" + " VALUES ('Jon', 'jon.edu', '10')");

        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Hammad', 'Ahmed','Male', '01220403050','hammad00@monufia.edu', '100')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Mulhat', 'Mohamed','Male', '010108787111','mulhat11@monufia.edu', '200')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Sondos', 'Fadl','Female', '01553536567789','sondos22@monufia.edu', '300')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Nader', 'Mohamed','Male', '011001187654','nader33@monufia.edu', '400')");

        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + "," + " " + Student_col_last_name + "," + " " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + ")" + " VALUES (1000,'Ahmed', 'Shosha','Male', 'ahmed111@monufia.edu', '1000')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + ")" + " VALUES (2000,'Youssef', 'Ramadan','Male', 'yousse222f@monufia.edu', '2000')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + ")" + " VALUES (3000,'Momen', 'Ahmed','Male', 'momen333@monufia.edu', '3000')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + ")" + " VALUES (4000,'Ali', 'Ahmed','Male', 'ali444@monufia.edu', '4000')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + ")" + " VALUES (5000,'Mohamed', 'Mosaad','Male', 'mohamed555@monufia.edu', '5000')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + ")" + " VALUES (6000,'Karim', 'Morsy','Male', 'k666@monufia.edu', '6000')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + ")" + " VALUES (7000,'Alaa', 'Ali','Female', 'alaa777@monufia.edu', '7000')");

        db.execSQL("INSERT INTO " + Education_Table_Departments + " (" + Department_col_code + "," + Department_col_name + ")" + " VALUES ('CS0','Computer Scince')");
        db.execSQL("INSERT INTO " + Education_Table_Departments + " (" + Department_col_code + "," + Department_col_name + ")" + " VALUES ('IT1','Information Technology')");
        db.execSQL("INSERT INTO " + Education_Table_Departments + " (" + Department_col_code + "," + Department_col_name + ")" + " VALUES ('IS2','Information System')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,5,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,5,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,5,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,7,70,'C+')");


        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,7,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,7,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,7,70,'C+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (1,1,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,1,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,1,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,1,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,1,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,1,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,1,90,'A+')");


        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (1,4,85,'A')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,4,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,4,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,4,85,'A')");


        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +")" + " VALUES ('CS105','Android',1,4)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id +"," + Courses_col_doctor_id +")" + " VALUES ('CS103','Operating System1',1,2)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id + ")" + " VALUES ('IS111','Data Base1',3,4)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id + "," + Courses_col_PreRequest_id + ")" + " VALUES ('CS106','Flutter',1,4,1)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id + ")" + " VALUES ('IT080','Computer Netowrk1',2,1)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +")" + " VALUES ('IT100','Image Processing',2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id + "," + Courses_col_PreRequest_id + ")" + " VALUES ('IT081','Computer Netowrk2',2,1,5)");





    }


    /**
     * onUpgrade()
     * This Method Called when the database needs to be upgraded.
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     **********************************************************************************************/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Education_Table_Students);
        db.execSQL("DROP TABLE IF EXISTS " + Education_Table_Doctors);
        db.execSQL("DROP TABLE IF EXISTS " + Education_Table_Admins);
        db.execSQL("DROP TABLE IF EXISTS " + Education_Table_Departments);
        db.execSQL("DROP TABLE IF EXISTS " + Education_Table_Courses);
        db.execSQL("DROP TABLE IF EXISTS " + Education_Table_Enrollment);
        db.execSQL("DROP TABLE IF EXISTS " + Education_Table_Absence);
        onCreate(db);
    }



    /**
     * checkLogin()
     * This Method Return True = Account Found In My_DB or False = Not Found In My_DB.
     * It's Search On Admins Tale Or In  Doctors Table Or On Student Table , UpOn Value Of
     *
     * @param kindCheckedId (Admin, Doctor, Student)
     ***********************************************************************************************/
    public boolean checkLogin(int kindCheckedId, String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isValid = false;

        SharedPreferences pref = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        String firstName = null;
        String lastName = null;
        String Email = null;
        String id = null;

        switch (kindCheckedId) {
            case -1:
                return isValid;
            case 0:
                Cursor adminCursor = db.query("" + Education_Table_Admins + "", new String[]{Admin_col_name,Admin_col_email},
                        "" + Admin_col_email + "=? AND " + Admin_col_password + "=?", new String[]{email, password},
                        null, null, null, null);

                if (adminCursor.moveToFirst()) {
                    isValid = adminCursor.moveToFirst();
                    int firstNameColumnIndex = adminCursor.getColumnIndex(Admin_col_name);
                    int emailColumnIndex = adminCursor.getColumnIndex(Admin_col_email);
                    if (firstNameColumnIndex >= 0) {
                        firstName = adminCursor.getString(firstNameColumnIndex);
                        Email = adminCursor.getString(emailColumnIndex);

                        pref.edit().putString("fName",firstName).apply();
                        pref.edit().putString("email",Email).apply();

                    }
                }
                adminCursor.close();
                break;
            case 1:
                Cursor doctorCursor = db.query("" + Education_Table_Doctors + "", new String[]{Doctors_col_first_name,Doctors_col_last_name,Doctors_col_email,Doctors_col_id},
                        "" + Doctors_col_email + "=? AND " + Doctors_col_password + "=?", new String[]{email, password},
                        null, null, null, null);

                if (doctorCursor.moveToFirst()) {
                    isValid = doctorCursor.moveToFirst();

                    int firstNameColumnIndex = doctorCursor.getColumnIndex(Doctors_col_first_name);
                    int lastNameColumnIndex = doctorCursor.getColumnIndex(Doctors_col_last_name);
                    int emailColumnIndex = doctorCursor.getColumnIndex(Doctors_col_email);
                    int idColumnIndex = doctorCursor.getColumnIndex(Doctors_col_id);
                    if (firstNameColumnIndex >= 0 && lastNameColumnIndex >= 0) {
                        firstName = doctorCursor.getString(firstNameColumnIndex);
                        lastName  = doctorCursor.getString(lastNameColumnIndex);
                        Email  = doctorCursor.getString(emailColumnIndex);
                        id  = doctorCursor.getString(idColumnIndex);

                        pref.edit().putString("fName",firstName).apply();
                        pref.edit().putString("lName",lastName).apply();
                        pref.edit().putString("email",Email).apply();
                        pref.edit().putString("id",id).apply();


                    }
                }
                doctorCursor.close();
                break;
            case 2:
                Cursor studentCursor = db.query("" + Education_Table_Students + "", new String[]{Student_col_first_name,Student_col_last_name,Student_col_email,Student_col_id},
                        "" + Student_col_email + "=? AND " + Student_col_password + "=?", new String[]{email, password},
                        null, null, null, null);

                if (studentCursor.moveToFirst()) {
                    isValid = studentCursor.moveToFirst();
                    int firstNameColumnIndex = studentCursor.getColumnIndex(Student_col_first_name);
                    int lastNameColumnIndex = studentCursor.getColumnIndex(Student_col_last_name);
                    int emailColumnIndex = studentCursor.getColumnIndex(Student_col_email);
                    int idColumnIndex = studentCursor.getColumnIndex(Student_col_id);

                    if (firstNameColumnIndex >= 0 && lastNameColumnIndex >= 0) {
                        firstName = studentCursor.getString(firstNameColumnIndex);
                        lastName  = studentCursor.getString(lastNameColumnIndex);
                        Email  = studentCursor.getString(emailColumnIndex);
                        id  = studentCursor.getString(idColumnIndex);


                        pref.edit().putString("fName",firstName).apply();
                        pref.edit().putString("lName",lastName).apply();
                        pref.edit().putString("email",Email).apply();
                        pref.edit().putString("id",id).apply();


                    }
                }
                studentCursor.close();
                break;
        }

        return isValid;
    }


    public ArrayList<SubjectRegestrationModel> getCourses_for_students() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<SubjectRegestrationModel> arrayList = new ArrayList();

        Cursor cursor = db.query("Courses", null, null, null, null, null, null);

        while (cursor.moveToNext()) {

            String column_code = cursor.getString(2);
            String column_name = cursor.getString(1);
            SubjectRegestrationModel model = new SubjectRegestrationModel( cursor.getString(2), cursor.getString(1));
            arrayList.add(model);
        }
        cursor.close();
        return arrayList;
    }


    /**Add New Student()
     **********************************************************************************************/
    public boolean addNewStudent(Students student){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor;

        values.put(Student_col_academic_number,student.getAcademic_Number());
        values.put(Student_col_first_name,student.getFName());
        values.put(Student_col_last_name,student.getLastName());
        values.put(Student_col_gender,student.getGender());
        values.put(Student_col_email,student.getEmail());
        values.put(Student_col_password,student.getPassword());

        //To Check If This Academic Number Is Received
        cursor = db.rawQuery("SELECT "+Student_col_academic_number+" FROM "+Education_Table_Students+" WHERE "+Student_col_academic_number+"=? ",new String []{student.getAcademic_Number()});
        if(cursor.moveToFirst()){
            Toast.makeText(context, "This Academic Number Is Received.", Toast.LENGTH_SHORT).show();
            return false;
        }

        //To Check If This Email Is Received
        cursor = db.rawQuery("SELECT "+Student_col_email+" FROM "+Education_Table_Students+" WHERE "+Student_col_email+"=? ",new String []{student.getEmail()});
        if(cursor.moveToFirst()){
            Toast.makeText(context, "This Email Is Received.", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Store Number Of Item Which Inserted Or Return -1 If Item Not Inserted
        long result = db.insert(Education_Table_Students,null,values);
//        db.close();

        return result !=-1;
    }

    /**displayAllStudents()
     **********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Students> displayAllStudents(){

        ArrayList<Students> studentsArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM "+Education_Table_Students,null);

        if(cursor != null && cursor.moveToFirst()){

            do{
                String fName = cursor.getString(cursor.getColumnIndex(Student_col_first_name));
                String aid = cursor.getString(cursor.getColumnIndex(Student_col_academic_number));
                String email = cursor.getString(cursor.getColumnIndex(Student_col_email));
                String password = cursor.getString(cursor.getColumnIndex(Student_col_password));
                String gender = cursor.getString(cursor.getColumnIndex(Student_col_gender));

                Students students = new Students(fName,aid,email,password,gender);

                studentsArrayList.add(students);

            }while (cursor.moveToNext());
        }
        cursor.close();
//        db.close();
        db = this.getWritableDatabase();

        return studentsArrayList;
    }


    /**updateStudent()
     **********************************************************************************************/
    public boolean updateStudent(Students student){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Student_col_first_name,student.getFName());
        values.put(Student_col_password,student.getPassword());
        values.put(Student_col_gender,student.getGender());

        String args [] = {student.getEmail()};

        int result =  db.update(Education_Table_Students,values,""+Student_col_email+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated

        return result > 0;
    }

    /**deleteStudent()
     **********************************************************************************************/
    public boolean deleteStudent(String email){

        SQLiteDatabase db =getWritableDatabase();
        String args[] ={email};

        int numDeletedDoctor =db.delete(Education_Table_Students,""+Student_col_email+"=?",args);

//        db.close();

        return numDeletedDoctor>0;
    }

    /**Add New Doctor()
     **********************************************************************************************/
    public boolean addNewDoctor(Doctors doctor){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Doctors_col_first_name,doctor.getFName());
        values.put(Doctors_col_last_name,doctor.getLName());
        values.put(Doctors_col_gender,doctor.getGender());
        values.put(Doctors_col_email,doctor.getEmail());
        values.put(Doctors_col_password,doctor.getPassword());


        //To Check If This Email Is Received
        Cursor cursor = db.rawQuery("SELECT "+Doctors_col_email+" FROM "+Education_Table_Doctors+" WHERE "+Doctors_col_email+"=? ",new String []{doctor.getEmail()});
        if(cursor.moveToFirst()){
            Toast.makeText(context, "This Email Is Received.", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Store Number Of Item Which Inserted Or Return -1 If Item Not Inserted
        long result = db.insert(Education_Table_Doctors,null,values);
//        db.close();

        return result !=-1;
    }


    /**display All Doctors()
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Doctors> displayAllDoctors(){

        ArrayList<Doctors> doctorsArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM "+Education_Table_Doctors,null);

        if(cursor != null && cursor.moveToFirst()){

            do{
                String fName = cursor.getString(cursor.getColumnIndex(Doctors_col_first_name));
                String email = cursor.getString(cursor.getColumnIndex(Doctors_col_email));
                String password = cursor.getString(cursor.getColumnIndex(Doctors_col_password));
                String gender = cursor.getString(cursor.getColumnIndex(Doctors_col_gender));

                Doctors doctors = new Doctors(fName,email,password,gender);

                doctorsArrayList.add(doctors);

            }while (cursor.moveToNext());
        }
        cursor.close();
//        db.close();
        db = this.getWritableDatabase();

        return doctorsArrayList;
    }


    /**updateDoctor()
     **********************************************************************************************/
    public boolean updateDoctor(Doctors doctor){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Doctors_col_first_name,doctor.getFName());
        values.put(Doctors_col_password,doctor.getPassword());
        values.put(Doctors_col_gender,doctor.getGender());

        String args [] = {doctor.getEmail()};

        int result =  db.update(Education_Table_Doctors,values,""+Doctors_col_email+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated

        if(result>0)
            Toast.makeText(context, "Changes saved." , Toast.LENGTH_SHORT).show();

        return result > 0;
    }

    /**Delete Doctor()
     **********************************************************************************************/
    public boolean deleteDoctor(String email){

        SQLiteDatabase db =getWritableDatabase();
        String args[] ={email};

        int numDeletedDoctor =db.delete(Education_Table_Doctors,""+Doctors_col_email+"=?",args);

//        db.close();

        return numDeletedDoctor>0;
    }

    /**getAllDoctorsNames()
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<String> getAllDoctorsNames(){

        ArrayList<String> docNames = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM "+Education_Table_Doctors,null);

        if(cursor != null && cursor.moveToFirst()){

            do{
                String docName = cursor.getString(cursor.getColumnIndex(Doctors_col_first_name));

                String name =docName;

                docNames.add(name);

            }while (cursor.moveToNext());
        }
        cursor.close();
//        db.close();
        db = this.getWritableDatabase();

        return docNames;
    }



    /**Add New Department()
     **********************************************************************************************/
    public boolean addNewDepartment(Departments department) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Department_col_name, department.getName());
        values.put(Department_col_code, department.getCode());

        //To Check If This Department Is Exist
        Cursor cursor = db.rawQuery("SELECT  "+Department_col_name+", "+Department_col_code+" FROM "+Education_Table_Departments+" WHERE "+Department_col_name+"=? OR "+Department_col_code+"=?  ",new String []{department.getName(),department.getCode()});
        if(cursor.moveToFirst()){
            Toast.makeText(context, "This Department's Name or Code Is Exist.", Toast.LENGTH_LONG).show();
            return false;
        }

        //Store Number Of Item Which Inserted Or Return -1 If Item Not Inserted
        long result = db.insert(Education_Table_Departments,null,values);

        return  result !=-1;
    }

    /**display All Departments()
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Departments> displayAllDepartments()
    {
        ArrayList<Departments> departmentsArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Education_Table_Departments,null);


        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                String name = cursor.getString(cursor.getColumnIndex(Department_col_name));
                String code = cursor.getString(cursor.getColumnIndex(Department_col_code));
                int id = cursor.getInt(cursor.getColumnIndex(Department_col_id));

                Departments departments = new Departments(id,name, code);

                departmentsArrayList.add(departments);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        db = this.getWritableDatabase();

        return departmentsArrayList;

    }

    /**updateDepartment()
     **********************************************************************************************/
    public boolean updateDepartment(Departments department, String codeBeforeUpdate,String nameBeforeUpdate){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        int result = 0;

        values.put(Department_col_name,department.getName());
        values.put(Department_col_code,department.getCode());

        String args [] = {codeBeforeUpdate};


        //To Check If This Department Is Exist
        // Check if the department name or code already exist, excluding the current record being updated
        Cursor cursor = db.rawQuery("SELECT " + Department_col_name + ", " + Department_col_code + " FROM " + Education_Table_Departments + " WHERE (" + Department_col_name + "=? OR " + Department_col_code + "=?) AND (" + Department_col_code + "!=? OR " + Department_col_name + "!=?)", new String[]{ department.getName(), department.getCode(), codeBeforeUpdate, nameBeforeUpdate });
        if(cursor.moveToFirst()){
            Toast.makeText(context, "Sorry Can't Edit , This Department's Name or Code Is Exist.", Toast.LENGTH_LONG).show();
        }
        else {
            result =  db.update(Education_Table_Departments,values,""+Department_col_code+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated
            Toast.makeText(context, "Changes saved." , Toast.LENGTH_SHORT).show();

        }
        return result > 0;
    }


    /**Delete Department()
     **********************************************************************************************/
    public boolean deleteDepartment(String code,String id){

        SQLiteDatabase db =getWritableDatabase();
        String args[] ={code};

        int numDeletedDepartments =db.delete(Education_Table_Departments,""+Department_col_code+"=?",args);
        db.delete(Education_Table_Courses,""+Courses_col_department_id+"=?",new  String[]{id});

//        db.close();
        return numDeletedDepartments>0;
    }


    /**getAllDepartmentsNames()
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<String> getAllDepartmentsNames(){

        ArrayList<String> deptName = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM "+Education_Table_Departments,null);

        if(cursor != null && cursor.moveToFirst()){

            do{
                String name = cursor.getString(cursor.getColumnIndex(Department_col_name));

                String n = name;

                deptName.add(n);

            }while (cursor.moveToNext());
        }
        cursor.close();
//        db.close();
        db = this.getWritableDatabase();

        return deptName;
    }

    /**Add New Course()
     **********************************************************************************************/

    public boolean addNewCourse(@NonNull Courses course) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Courses_col_name, course.getName());
        values.put(Courses_col_code, course.getCode());
        values.put(Courses_col_department_id, course.getDepartment());
        values.put(Courses_col_doctor_id, course.getDoctor());
        if(course.getPreRequest() != -1)
            values.put(Courses_col_PreRequest_id, course.getPreRequest());
        else
            values.put(Courses_col_PreRequest_id, (Integer) null);


        //To Check If This Courses Is Exist
        Cursor cursor = db.rawQuery("SELECT  " + Courses_col_name + ", " + Courses_col_code + " FROM " + Education_Table_Courses + " WHERE " + Courses_col_name + "=? OR " + Courses_col_code + "=?  ", new String[]{course.getName(), course.getCode()});
        if (cursor.moveToFirst()) {
            Toast.makeText(context, "This Course's Name or Code Is Exist.", Toast.LENGTH_LONG).show();
            return false;
        }
        //Store Number Of Item Which Inserted Or Return -1 If Item Not Inserted
        long result = db.insert(Education_Table_Courses,null,values);

        return  result !=-1;

    }


    /**display All Courses()
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Courses> displayAllCourses()
    {
        ArrayList<Courses> coursesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Education_Table_Courses,null);


        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                String name = cursor.getString(cursor.getColumnIndex(Courses_col_name));
                String code = cursor.getString(cursor.getColumnIndex(Courses_col_code));
                int dept = cursor.getInt(cursor.getColumnIndex(Courses_col_department_id));
                int doctor = cursor.getInt(cursor.getColumnIndex(Courses_col_doctor_id));
                int preRequest = cursor.getInt(cursor.getColumnIndex(Courses_col_PreRequest_id));
                int id = cursor.getInt(cursor.getColumnIndex(Courses_col_id));

                Courses course = new Courses(id,code,name,preRequest,dept,doctor);

                coursesList.add(course);
            }while (cursor.moveToNext());
        }

        cursor.close();
//        db.close();
        db = this.getWritableDatabase();

        return coursesList;

    }


    /**display Doctor's Courses()
     * This method return only courses which this doctor teach it , not all courses
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Courses> displayDoctorCourses()
    {
        ArrayList<Courses> coursesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        SharedPreferences preferences = context.getSharedPreferences("userInfo",context.MODE_PRIVATE);
        String doctorId = preferences.getString("id", "");


        Cursor cursor = db.rawQuery("SELECT * FROM " + Education_Table_Courses + " WHERE " + Courses_col_doctor_id + " =?", new String[]{doctorId});

        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                String name = cursor.getString(cursor.getColumnIndex(Courses_col_name));
                String code = cursor.getString(cursor.getColumnIndex(Courses_col_code));
                int dept = cursor.getInt(cursor.getColumnIndex(Courses_col_department_id));
                int doctor = cursor.getInt(cursor.getColumnIndex(Courses_col_doctor_id));
                int preRequest = cursor.getInt(cursor.getColumnIndex(Courses_col_PreRequest_id));
                int id = cursor.getInt(cursor.getColumnIndex(Courses_col_id));

                Courses course = new Courses(id,code,name,preRequest,dept,doctor);

                coursesList.add(course);
            }while (cursor.moveToNext());
        }

        cursor.close();
//        db.close();
        db = this.getWritableDatabase();

        return coursesList;

    }



    /**getEnrolledStudentCountByCourseId()
     * ********************************************************************************************/
    public int getEnrolledStudentCountByCourseId(int courseId){
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Education_Table_Enrollment + " WHERE " + Enrollment_col_course_id + " =?", new String[]{courseId+""});

        count = cursor.getCount();

        return count;
    }

    /**getDegreeByCourseAndStudentId()
     *********************************************************************************************/
    @SuppressLint("Range")
    public Enrollments getDegreeByCourseAndStudentId(int courseId, int studentId){

        SQLiteDatabase db = getReadableDatabase();
        int degree = 0;
        String grade = null;

        String query = "SELECT " + Enrollment_col_student_degree + ", " + Enrollment_col_student_grade +" FROM " + Education_Table_Enrollment +
                " WHERE " + Enrollment_col_course_id + " = ? AND " + Enrollment_col_student_id + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{courseId+"",studentId+""});

        if (cursor.moveToFirst()) {
            degree = cursor.getInt(cursor.getColumnIndex(Enrollment_col_student_degree));
            grade = cursor.getString(cursor.getColumnIndex(Enrollment_col_student_grade));
        }

        cursor.close();
        return new Enrollments(degree,grade);
    }

    /**addOrUpdateGrade()
     **********************************************************************************************/
    @SuppressLint("SuspiciousIndentation")
    public boolean addOrUpdateGrade(Enrollments enrollment){

        int result = 0;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        if(enrollment.getStudent_degree() == -1)
            values.put(Enrollment_col_student_degree,(Integer) null);
        else
            values.put(Enrollment_col_student_degree,enrollment.getStudent_degree());
        values.put(Enrollment_col_student_grade,enrollment.getStudent_grade());


        String args [] = {String.valueOf(enrollment.getStudent_id()),String.valueOf(enrollment.getCourse_id())};


        result =  db.update(Education_Table_Enrollment,values," "+Enrollment_col_student_id+"=? AND "+Enrollment_col_course_id+"=? ",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated
        Toast.makeText(context, "Changes saved." , Toast.LENGTH_SHORT).show();

        return result > 0;
    }

    /**updateCourse()
     **********************************************************************************************/
    public boolean updateCourse(Courses course, String codeBeforeUpdate,String nameBeforeUpdate){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        int result = 0;

        values.put(Courses_col_name,course.getName());
        values.put(Courses_col_code,course.getCode());
        values.put(Courses_col_department_id,course.getDepartment());
        values.put(Courses_col_doctor_id,course.getDoctor());
        if(course.getPreRequest() != -1)
            values.put(Courses_col_PreRequest_id, course.getPreRequest());
        else
            values.put(Courses_col_PreRequest_id, (Integer) null);
        String args [] = {codeBeforeUpdate};


        //To Check If This Course Is Exist
        // Check if the Course name or code already exist, excluding the current record being updated
        Cursor cursor = db.rawQuery("SELECT " + Courses_col_name + ", " + Courses_col_code + " FROM " + Education_Table_Courses + " WHERE (" + Courses_col_name + "=? OR " + Courses_col_code + "=?) AND (" + Courses_col_code + "!=? OR " + Courses_col_name + "!=?)", new String[]{ course.getName(), course.getCode(), codeBeforeUpdate, nameBeforeUpdate });
        if(cursor.moveToFirst()){
            Toast.makeText(context, "Sorry Can't Edit , This Course's Name or Code Is Exist.", Toast.LENGTH_LONG).show();
        }
        else {
            result =  db.update(Education_Table_Courses,values,""+Courses_col_code+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated
            Toast.makeText(context, "Changes saved." , Toast.LENGTH_SHORT).show();

        }
        return result > 0;
    }

    /**Delete Course()
     **********************************************************************************************/
    public boolean deleteCourse(String code,String id){

        SQLiteDatabase db =getWritableDatabase();
        String args[] ={code};

        int numDeletedCourses = db.delete(Education_Table_Courses,""+Courses_col_code+"=?",args);

        ContentValues values = new ContentValues();

        values.put(Courses_col_PreRequest_id,(Integer) null);

        db.update(Education_Table_Courses,values,""+Courses_col_PreRequest_id+"=?",new String[]{id}); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated


//        db.close();
        return numDeletedCourses>0;
    }

    /**getAllCoursesNames()
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<String> getAllCoursesNames(){

        ArrayList<String> coursesNames = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM "+Education_Table_Courses,null);

        if(cursor != null && cursor.moveToFirst()){

            do{
                String courseName = cursor.getString(cursor.getColumnIndex(Courses_col_name));

                String name = courseName;

                coursesNames.add(name);

            }while (cursor.moveToNext());
        }
        cursor.close();
//        db.close();
        db = this.getWritableDatabase();

        return coursesNames;
    }

    /**getDepartmentIdByName()
     * ********************************************************************************************/
    @SuppressLint("Range")
    public int getDepartmentIdByName(String departmentName){
        SQLiteDatabase db = getReadableDatabase();
        int departmentId = -1; // Default value if department ID is not found

        String query = "SELECT " + Department_col_id + " FROM " + Education_Table_Departments +
                " WHERE " + Department_col_name + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{departmentName});

        if (cursor.moveToFirst()) {
            departmentId = cursor.getInt(cursor.getColumnIndex(Department_col_id));
        }

        cursor.close();
        return departmentId;

    }

    /**getDepartmentNameById()
     *********************************************************************************************/
    @SuppressLint("Range")
    public String getDepartmentNameById(int departmentId){
        SQLiteDatabase db = getReadableDatabase();
        String departmentName = "hhh"; // Default value if department name is not found

        String query = "SELECT " + Department_col_name + " FROM " + Education_Table_Departments +
                " WHERE " + Department_col_id + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{departmentId+""});

        if (cursor.moveToFirst()) {
            departmentName = cursor.getString(cursor.getColumnIndex(Department_col_name));
        }

        cursor.close();
        return departmentName;

    }

    /**getDoctorNameById()
     *********************************************************************************************/
    @SuppressLint("Range")
    public String getDoctorNameById(int doctorId){
        SQLiteDatabase db = getReadableDatabase();
        String doctorName = "None"; // Default value if doctor name is not found

        String query = "SELECT " + Doctors_col_first_name + " FROM " + Education_Table_Doctors +
                " WHERE " + Doctors_col_id + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{doctorId+""});

        if (cursor.moveToFirst()) {
            doctorName = cursor.getString(cursor.getColumnIndex(Doctors_col_first_name));
        }

        cursor.close();
        return doctorName;

    }


    /**getPreRequestNameById()
     *********************************************************************************************/
    @SuppressLint("Range")
    public String getPreRequestNameById(int preRequestId){
        SQLiteDatabase db = getReadableDatabase();
        String preRequestName = "None"; // Default value if getPreRequest name is not found

        String query = "SELECT " + Courses_col_name + " FROM " + Education_Table_Courses +
                " WHERE " + Courses_col_id + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{preRequestId+""});

        if (cursor.moveToFirst()) {
            preRequestName = cursor.getString(cursor.getColumnIndex(Courses_col_name));
        }

        cursor.close();
        return preRequestName;

    }


    /**getDoctorIdByName()
     **********************************************************************************************/
    @SuppressLint("Range")
    public int getDoctorIdByName(String doctorName){
        SQLiteDatabase db = getReadableDatabase();
        int doctorId = -1; // Default value if department ID is not found

        String query = "SELECT " + Doctors_col_id + " FROM " + Education_Table_Doctors +
                " WHERE " + Doctors_col_first_name + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{doctorName});

        if (cursor.moveToFirst()) {
            doctorId = cursor.getInt(cursor.getColumnIndex(Doctors_col_id));
        }

        cursor.close();
        return doctorId;

    }

    /**getEnrolledStudentsByCourseId()
     **********************************************************************************************/
    /**
     * Retrieves a list of enrolled students for a given course ID.
     *
     * @param courseId The ID of the course.
     * @return An ArrayList of Students representing the enrolled students.
     */
    @SuppressLint("Range")
    public ArrayList<Students> getEnrolledStudentsByCourseId(int courseId) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> studentsIdList = new ArrayList<>();

        // Retrieve the enrollment records for the given course ID
        Cursor cursor1 = db.rawQuery("SELECT * FROM " + Education_Table_Enrollment + " WHERE " + Enrollment_col_course_id + " =?", new String[]{String.valueOf(courseId)});

        if (cursor1 != null && cursor1.moveToFirst()) {
            do {
                int studentId = cursor1.getInt(cursor1.getColumnIndex(Enrollment_col_student_id));

                studentsIdList.add(String.valueOf(studentId));
            } while (cursor1.moveToNext());
        }
        String[] studentIdsArray = studentsIdList.toArray(new String[studentsIdList.size()]);

        // Construct the query to retrieve the students based on their IDs
        String studentIds = TextUtils.join(",", Collections.nCopies(studentIdsArray.length, "?"));
        String query = "SELECT * FROM " + Education_Table_Students + " WHERE " + Student_col_id + " IN (" + studentIds + ")";

        // Execute the query with the student IDs as parameters
        Cursor cursor2 = db.rawQuery(query, studentIdsArray);

        ArrayList<Students> studentsList = new ArrayList<>();

        if (cursor2 != null && cursor2.moveToFirst()) {
            do {
                int studentId = cursor2.getInt(cursor2.getColumnIndex(Student_col_id));
                String studentFName = cursor2.getString(cursor2.getColumnIndex(Student_col_first_name));
                String studentAID = cursor2.getString(cursor2.getColumnIndex(Student_col_academic_number));
                String studentEmail = cursor2.getString(cursor2.getColumnIndex(Student_col_email));
                String studentGender = cursor2.getString(cursor2.getColumnIndex(Student_col_gender));

                // Create a Students object for each student and add it to the list
                Students students = new Students(studentId, studentAID, studentFName, studentEmail, studentGender);
                studentsList.add(students);
            } while (cursor2.moveToNext());
        }

        return studentsList;

    }



    /**getPreRequestIdByName()
     **********************************************************************************************/
    @SuppressLint("Range")
    public int getPreRequestIdByName(String courseName){
        SQLiteDatabase db = getReadableDatabase();
        int preRequestId = -1; // Default value if department ID is not found

        String query = "SELECT " + Courses_col_id + " FROM " + Education_Table_Courses +
                " WHERE " + Courses_col_name + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{courseName});

        if (cursor.moveToFirst()) {
            preRequestId = cursor.getInt(cursor.getColumnIndex(Courses_col_id));
        }

        cursor.close();
        return preRequestId;

    }


//__________________________________Subject Function_______________________________________________


    public ArrayList<SubjectModel> Get_all_courses_for_student_afterRegist() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<SubjectModel> arrayList = new ArrayList();
        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String StudentId = preferences.getString("id", "");
        int S_id = Integer.parseInt(StudentId);
        String selection = "Courses.id = Enrollment.enrollment_course_id AND Enrollment.enrollment_student_id = " + S_id;

        String query = "SELECT Courses.name, Courses.code FROM Courses JOIN Enrollment ON Courses.id = Enrollment.enrollment_course_id WHERE " + selection;

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String column_code = cursor.getString(cursor.getColumnIndex("code"));
            @SuppressLint("Range") String column_name = cursor.getString(cursor.getColumnIndex("name"));
            SubjectModel model = new SubjectModel(column_name, column_code);
            arrayList.add(model);
        }
        cursor.close();
        return arrayList;
    }

    /*********************************************************/


    public boolean insertEnrollmentTable(int id_Course) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String StudentId = preferences.getString("id", "");
        int S_id = Integer.parseInt(StudentId);

        values.put(Enrollment_col_course_id, id_Course);
        values.put(Enrollment_col_student_id, S_id);
        long result = 0;
        if(SubjectDuplicated(S_id,id_Course)==false)

            result = db.insert(Education_Table_Enrollment, null, values);
        else Toast.makeText(context, "Subject have Pre request", Toast.LENGTH_SHORT).show();




        return result != -1;
    }

    /*********************************************************/
    public boolean SubjectDuplicated(int enrollment_student_id, int enrollment_course_id) {
        boolean result = false;

        SQLiteDatabase db = getReadableDatabase();

        // Prepare the query to check if the record exists
        String query = "SELECT COUNT(*) FROM Enrollment WHERE enrollment_student_id = ? AND enrollment_course_id = ?";
        String[] selectionArgs = {String.valueOf(enrollment_student_id), String.valueOf(enrollment_course_id)};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            result = count > 0;
        }
        return result;
    }

    /*********************************************************/


    public int get_Id_course_by_CourseName(String Course_Name) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "name = '" + Course_Name + "'";

        Cursor cursor = db.query("Courses", new String[]{Courses_col_id}, selection, null, null, null, null);
        int Course_id = 0;
        if (cursor.moveToFirst()) {
            do {
                Course_id = cursor.getInt(0);

            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return Course_id;
    }

    /*********************************************************/

    public boolean subject_have_Prerequest(String subjectName) {
        System.out.println("subject_have_Prerequest");

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"PreRequests_id"};
        String selection = "name = ?";
        String[] selectionArgs = {subjectName};
        Cursor cursor = db.query("Courses", columns, selection, selectionArgs, null, null, null);

        boolean hasPrerequisite = false;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("PreRequests_id");
            String prerequisiteId = cursor.getString(columnIndex);
            hasPrerequisite = (prerequisiteId != null);
        }

        cursor.close();
        return hasPrerequisite;
    }



    public boolean AskForRegistedPre(String subjectName) {
        int pre_id = getPreRequestIdBy_Name(subjectName);
        System.out.println("registed pre AskForRegistedPre");
        if(pre_thereExist_inEnrollment_course_id(pre_id)){
            return true;
        }else{ return false;}

    }

    private boolean
    pre_thereExist_inEnrollment_course_id(int pre_id) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "enrollment_course_id = ?";
        String[] selectionArgs = {String.valueOf(pre_id)};
        System.out.println("in the pre_thereExist_inEnrollment_course_id");

        Cursor cursor = db.query("Enrollment", new String[]{Enrollment_col_course_id}, selection, selectionArgs, null, null, null);
        int coursePreId = 0;

        if (cursor.moveToFirst()) {
            coursePreId = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        if (coursePreId > 0) {
            System.out.println("He registered the prerequisite.");
            return true;
        } else {
            return false;
        }


    }

    private int getPreRequestIdBy_Name(String subjectName) {

        SQLiteDatabase db = getReadableDatabase();
        String selection = "name = ?";
        String[] selectionArgs = {subjectName};

        Cursor cursor = db.query("Courses", new String[]{"PreRequests_id"}, selection, selectionArgs, null, null, null);
        int coursePreId = 0;

        if (cursor.moveToFirst()) {
            coursePreId = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return coursePreId;
    }
    public boolean deleteSubject(int course_id) {
        SQLiteDatabase db = getWritableDatabase();
        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String studentId = preferences.getString("id", "");
        int student_id = Integer.parseInt(studentId);

        String selection = Enrollment_col_course_id + " = ? AND " + Enrollment_col_student_id + " = ?";
        String[] selectionArgs = {String.valueOf(course_id), String.valueOf(student_id)};
        int result = db.delete(Education_Table_Enrollment, selection, selectionArgs);
        db.close();

        return result != 0;
    }

}



