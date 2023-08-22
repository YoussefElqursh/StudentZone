package com.studentzone.Data_Base;

import static java.sql.Types.NULL;

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
import com.studentzone.Student_Classes.Student_Models.SubjectModel.StudentPassedModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;
import java.util.Collections;

public class My_DB extends SQLiteOpenHelper {

    /**
     * Declaration and initiation of My_DB
     ***********************************************************************************************/
    public static final String DB_Name = "Education";

    public static final int DB_Version = 45;

    private final Context context;

    /**
     * Declaration and initiation of Students table
     ***********************************************************************************************/
    public static final String Education_Table_Students = "Students";
    public static final String Student_col_id = "id";
    public static final String Student_col_academic_number = "academic_number";
    public static final String Student_col_dept = "department";
    public static final String Student_col_first_name = "first_name";
    public static final String Student_col_last_name = "last_name";
    public static final String Student_col_gender = "gender";
    public static final String Student_col_email = "email";
    public static final String Student_col_password = "password";
    public static final String Student_col_phone = "phone";
    public static final String Student_col_image_uri = "image";
    public static final String Student_col_level = "level";


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
    public static final String Doctors_col_image_uri = "image";

    /**
     * Declaration and initiation of Admins table
     ***********************************************************************************************/
    public static final String Education_Table_Admins = "Admins";
    public static final String Admin_col_id = "id";
    public static final String Admin_col_name = "name";
    public static final String Admin_col_email = "email";
    public static final String Admin_col_password = "password";
    public static final String Admin_col_phone = "phone";
    public static final String Admin_col_image_uri =  "image";

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
    public static final String Courses_col_PreRequest_id = "PreRequests_id";
    public static final String Courses_col_level = "level";
    public static final String Courses_col_hours = "hours";


    /**
     * Declaration and initiation of Enrollment table
     ***********************************************************************************************/

    public static final String Education_Table_Enrollment = "Enrollment";
    public static final String Enrollment_col_id = "id";
    public static final String Enrollment_col_student_id = "enrollment_student_id";
    public static final String Enrollment_col_course_id = "enrollment_course_id";
    public static final String Enrollment_col_student_grade = "enrollment_student_grade";
    public static final String Enrollment_col_student_degree = "enrollment_student_degree";
    public static final String Enrollment_col_student_course_hours = "student_course_hours";


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
                + "" + Student_col_dept + " INTEGER,"
                + "" + Student_col_first_name + " TEXT,"
                + "" + Student_col_last_name + " TEXT,"    //Addition+++++++++++++++++++++++++++++++++
                + "" + Student_col_gender + " TEXT,"
                + "" + Student_col_phone + " TEXT,"
                + "" + Student_col_email + " TEXT UNIQUE NOT NULL CHECK(email LIKE '%.edu%'),"
                + "" + Student_col_password + " TEXT,"
                + "" + Student_col_image_uri + " TEXT,"
                + "FOREIGN KEY(" + Student_col_dept + ") REFERENCES Departmen(" + Department_col_id + "))");

        db.execSQL("CREATE TABLE " + Education_Table_Doctors + " ("
                + "" + Doctors_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Doctors_col_first_name + " TEXT,"
                + "" + Doctors_col_last_name + " TEXT,"
                + "" + Doctors_col_gender + " TEXT,"
                + "" + Doctors_col_image_uri + " TEXT,"
                + "" + Student_col_level + " INTEGER,"
                + "" + Doctors_col_phone + " TEXT UNIQUE,"  //Addition+++++++++++++++++++++++++++++++++
                + "" + Doctors_col_email + " TEXT UNIQUE NOT NULL CHECK(" + Doctors_col_email + " LIKE '%.edu'),"
                + "" + Doctors_col_password + " TEXT)");    //Should Be NOT NULL

        db.execSQL("CREATE TABLE " + Education_Table_Admins + " ("
                + "" + Admin_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Admin_col_name + " TEXT,"
                + "" + Admin_col_phone + " TEXT,"
                + "" + Admin_col_image_uri + " TEXT,"
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
                + "" + Courses_col_level + " INTEGER,"
                + "" + Courses_col_hours + " INTEGER,"
                + "FOREIGN KEY(" + Courses_col_department_id + ") REFERENCES Departmen(" + Department_col_id + "),"
                + "FOREIGN KEY(" + Courses_col_doctor_id + ") REFERENCES Doctors(" + Doctors_col_id + "))");

        db.execSQL("CREATE TABLE " + Education_Table_Enrollment + " ("
                + "" + Enrollment_col_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "" + Enrollment_col_student_id + " INTEGER,"
                + "" + Enrollment_col_course_id + " INTEGER,"
                + "" + Enrollment_col_student_grade + " Text,"
                + "" + Enrollment_col_student_course_hours + " INTEGER,"
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

        //Departments
        db.execSQL("INSERT INTO " + Education_Table_Departments + " (" + Department_col_code + "," + Department_col_name + ")" + " VALUES ('GN0','General')");
        db.execSQL("INSERT INTO " + Education_Table_Departments + " (" + Department_col_code + "," + Department_col_name + ")" + " VALUES ('CS1','Computer Science')");
        db.execSQL("INSERT INTO " + Education_Table_Departments + " (" + Department_col_code + "," + Department_col_name + ")" + " VALUES ('IS2','Information Systems')");
        db.execSQL("INSERT INTO " + Education_Table_Departments + " (" + Department_col_code + "," + Department_col_name + ")" + " VALUES ('IT3','Information Technology')");
        db.execSQL("INSERT INTO " + Education_Table_Departments + " (" + Department_col_code + "," + Department_col_name + ")" + " VALUES ('OD4','OR and Decision Support')");

        //Admins Accounts
        db.execSQL("INSERT INTO " + Education_Table_Admins + " (" + Admin_col_name + ", " + Admin_col_email + ", " + Admin_col_password + ", " + Admin_col_phone + ")" + " VALUES ('Jon', 'jon10@monufia.edu', '10','010102030400')");

        //Doctors Accounts
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Hammad', 'Ahmed','Male', '01220403050','hammad00@monufia.edu', '100')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Mulhat', 'Mohamed','Male', '010108787111','mulhat11@monufia.edu', '200')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Sondos', 'Fadl','Female', '01553536567789','sondos22@monufia.edu', '300')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Nader', 'Mohamed','Male', '011001187654','nader33@monufia.edu', '400')");

        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Ossama', 'Abdul-Raouf','Male', '015001907654','ossama44@monufia.edu', '500')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Kafafi', 'Ahmed','Male', '010001187650','ahmed55@monufia.edu', '600')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Ashraf', 'AlSisi','Male', '01581187650','ashraf66@monufia.edu', '700')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Hadhoud', 'Mohie','Male', '01511111876','mohie77@monufia.edu', '800')");

        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Khaled', 'Amin','Male', '01211111876','khaled88@monufia.edu', '900')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Hatem', 'AlSyed','Male', '01212221876','hatem99@monufia.edu', '1000')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Hamdy', 'Musa','Male', '01590800876','hamdy100@monufia.edu', '1100')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Araby', 'Kishk','Male', '01190008076','araby110@monufia.edu', '1200')");

        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Sameh', 'Zaref','Male', '01009000806','sameh120@monufia.edu', '1300')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Amera', 'Abdel Muati','Female', '01229000806','amer130@monufia.edu', '1400')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Anas', 'Youssef','Male', '05119000806','anas140@monufia.edu', '1500')");
        db.execSQL("INSERT INTO " + Education_Table_Doctors + " (" + Doctors_col_first_name + ", " + Doctors_col_last_name + ", " + Doctors_col_gender + "," + Doctors_col_phone + ", " + Doctors_col_email + ", " + Doctors_col_password + ")" + " VALUES ('Gamal', 'Farouk','Male', '01119000806','gamal150@monufia.edu', '1600')");


        //Level 1 Courses
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('MA111','Mathematics-1',1,6,1,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('OD111','Discrete Mathematics',5,5,1,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('GN170','Scientific & Technical Report Writing',1,8,1,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('CS111','Computer Introduction',2,12,1,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('CS110','Semiconductors',2,7,1,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('GN140','Professional Ethics',1,9,1,3)");


        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('GN112','Fundamentals of Management',1,10,1,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('CS131','Fundamentals of Programming',2,4,11,1,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('MA112','Mathematics-2',1,1,6,1,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('IS111','Introduction to IS',3,12,'1',3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('ST190','Statistics & Probabilities',1,1,8,'1',3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('IT181','Logic Design-1',4,5,9,'1',3)");

        //Level 2 Courses
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('IT261','Multimedia-1',4,8,13,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('IS251','Web Design and Development',3,8,14,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('CS132','Computer Programming-1',2,8,8,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('OD213','Introduction to Operation & Decision Support Research',5,9,6,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('CS212','Data Structure',2,8,12,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('IS212','Systems Analysis & Design-1',3,9,10,2,3)");

        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('IT282','Computer Architecture',4,13,15,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('CS233','Computer Programming-2',2,15,8,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('CS261','Operating Systems-1',2,15,7,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('IT211','Computer Networks-1',4,12,1,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('OD342','Simulation & Modeling',5,16,16,2,3)");
        db.execSQL("INSERT INTO " + Education_Table_Courses + " (" + Courses_col_code + "," + Courses_col_name + "," + Courses_col_department_id + "," + Courses_col_PreRequest_id + "," + Courses_col_doctor_id +"," + Courses_col_level +"," + Courses_col_hours +")" + " VALUES ('MA213','Mathematics-3',5,9,10,2,3)");


        //Students Accounts
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + "," + Student_col_dept + "," + Student_col_phone + ")" + " VALUES (1000,'Ahmed', 'Shosha','Male', 'ahmed111@monufia.edu', '1000',1,'01281913317')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + "," + Student_col_dept + "," + Student_col_phone + ")" + " VALUES (2000,'Youssef', 'Ramadan','Male', 'yousse222f@monufia.edu', '2000',1,'01284486834')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + "," + Student_col_dept + "," + Student_col_phone + ")" + " VALUES (3000,'Momen', 'Ahmed','Male', 'momen333@monufia.edu', '3000',1,'01202617505')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + "," + Student_col_dept + "," + Student_col_phone + ")" + " VALUES (4000,'Ali', 'Ahmed','Male', 'ali444@monufia.edu', '4000',1,'0155943023')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + "," + Student_col_dept + "," + Student_col_phone + ")" + " VALUES (5000,'Mohamed', 'Mosaad','Male', 'mohamed555@monufia.edu', '5000',1,'01029826607')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + "," + Student_col_dept + "," + Student_col_phone + ")" + " VALUES (6000,'Karim', 'Morsy','Male', 'k666@monufia.edu', '6000',1,'01279722049')");
        db.execSQL("INSERT INTO " + Education_Table_Students + " (" + Student_col_academic_number + "," + Student_col_first_name + ", " + "" + Student_col_last_name + ", " + Student_col_gender + ", " + Student_col_email + ", " + Student_col_password + "," + Student_col_dept + "," + Student_col_phone + ")" + " VALUES (7000,'Alaa', 'Ali','Female', 'alaa777@monufia.edu', '7000',1,'01104060400')");


        //Students Grades
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,1,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,2,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,3,3,95,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,4,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,5,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,6,3,80,'B+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,7,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,8,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,9,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,10,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,11,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (2,12,3,80,'B+')");


        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,1,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,2,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,3,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,4,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,5,3,88,'A')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,6,3,80,'B+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,7,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,8,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,9,3,88,'A')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,10,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,11,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (3,12,3,80,'B+')");



        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,1,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,2,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,3,3,88,'A')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,4,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,5,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,6,3,80,'B+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,7,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,8,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,9,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,10,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,11,3,85,'A')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (4,12,3,80,'B+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,1,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,2,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,3,3,84,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,4,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,5,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,6,3,80,'B+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,7,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,8,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,9,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,10,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,11,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (5,12,3,80,'B+')");


        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,1,3,72,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,2,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,3,3,74,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,4,3,69,'C')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,5,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,6,3,75,'B')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,7,3,68,'C')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,8,3,55,'D')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,9,3,89,'A')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,10,3,66,'C')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,11,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,12,3,68,'C')");


        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,13,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,14,3,78,'B')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,15,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,16,3,74,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,17,3,52,'D')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,18,3,64,'D+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,19,3,67,'C')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,20,3,78,'B')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,21,3,61,'D+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,22,3,78,'B')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,23,3,59,'D')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (6,24,3,84,'B+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,1,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,2,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,3,3,85,'A')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,4,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,5,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,6,3,80,'B+')");

        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,7,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,8,3,90,'A+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,9,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,10,3,80,'B+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,11,3,70,'C+')");
        db.execSQL("INSERT INTO " + Education_Table_Enrollment + " (" + Enrollment_col_student_id + "," + Enrollment_col_course_id + "," + Enrollment_col_student_course_hours + "," + Enrollment_col_student_degree + "," + Enrollment_col_student_grade + ")" + " VALUES (7,12,3,89,'A')");

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
        String firstName;
        String lastName;
        String Email;
        String id;
        String phoneNumber;
        String aid;
        int department;
        String image_uri;

        switch (kindCheckedId) {
            case -1:
                return isValid;
            case 0:
                Cursor adminCursor = db.query("" + Education_Table_Admins + "", new String[]{Admin_col_name,Admin_col_email,Admin_col_phone,Admin_col_image_uri},
                        "" + Admin_col_email + "=? AND " + Admin_col_password + "=?", new String[]{email, password},
                        null, null, null, null);

                if (adminCursor.moveToFirst()) {
                    isValid = adminCursor.moveToFirst();
                    int firstNameColumnIndex = adminCursor.getColumnIndex(Admin_col_name);
                    int emailColumnIndex = adminCursor.getColumnIndex(Admin_col_email);
                    int phoneColumnIndex = adminCursor.getColumnIndex(Admin_col_phone);
                    int imageColumnIndex = adminCursor.getColumnIndex(Admin_col_image_uri);

                    if (firstNameColumnIndex >= 0) {
                        firstName = adminCursor.getString(firstNameColumnIndex);
                        Email = adminCursor.getString(emailColumnIndex);
                        phoneNumber = adminCursor.getString(phoneColumnIndex);
                        image_uri = adminCursor.getString(imageColumnIndex);

                        pref.edit().putString("fName",firstName).apply();
                        pref.edit().putString("email",Email).apply();
                        pref.edit().putString("phoneNumber",phoneNumber).apply();
                        pref.edit().putString("image_uri",image_uri).apply();

                    }
                }
                adminCursor.close();
                break;
            case 1:
                Cursor doctorCursor = db.query("" + Education_Table_Doctors + "", new String[]{Doctors_col_first_name,Doctors_col_last_name,Doctors_col_email,Doctors_col_id,Doctors_col_phone,Doctors_col_image_uri},
                        "" + Doctors_col_email + "=? AND " + Doctors_col_password + "=?", new String[]{email, password},
                        null, null, null, null);

                if (doctorCursor.moveToFirst()) {
                    isValid = doctorCursor.moveToFirst();

                    int firstNameColumnIndex = doctorCursor.getColumnIndex(Doctors_col_first_name);
                    int lastNameColumnIndex = doctorCursor.getColumnIndex(Doctors_col_last_name);
                    int emailColumnIndex = doctorCursor.getColumnIndex(Doctors_col_email);
                    int idColumnIndex = doctorCursor.getColumnIndex(Doctors_col_id);
                    int phoneColumnIndex = doctorCursor.getColumnIndex(Doctors_col_phone);
                    int imageColumnIndex = doctorCursor.getColumnIndex(Doctors_col_image_uri);

                    if (firstNameColumnIndex >= 0 && lastNameColumnIndex >= 0 ) {
                        firstName = doctorCursor.getString(firstNameColumnIndex);
                        lastName  = doctorCursor.getString(lastNameColumnIndex);
                        Email  = doctorCursor.getString(emailColumnIndex);
                        id  = doctorCursor.getString(idColumnIndex);
                        phoneNumber  = doctorCursor.getString(phoneColumnIndex);
                        image_uri  = doctorCursor.getString(imageColumnIndex);

                        pref.edit().putString("fName",firstName).apply();
                        pref.edit().putString("lName",lastName).apply();
                        pref.edit().putString("email",Email).apply();
                        pref.edit().putString("id",id).apply();
                        pref.edit().putString("image_uri",image_uri).apply();
                        pref.edit().putString("phoneNumber",phoneNumber).apply();


                    }
                }
                doctorCursor.close();
                break;
            case 2:
                Cursor studentCursor = db.query("" + Education_Table_Students + "", new String[]{Student_col_first_name,Student_col_last_name,Student_col_email,Student_col_id,Student_col_phone,Student_col_academic_number,Student_col_dept,Student_col_image_uri},
                        "" + Student_col_email + "=? AND " + Student_col_password + "=?", new String[]{email, password},
                        null, null, null, null);

                if (studentCursor.moveToFirst()) {
                    isValid = studentCursor.moveToFirst();
                    int firstNameColumnIndex = studentCursor.getColumnIndex(Student_col_first_name);
                    int lastNameColumnIndex = studentCursor.getColumnIndex(Student_col_last_name);
                    int emailColumnIndex = studentCursor.getColumnIndex(Student_col_email);
                    int idColumnIndex = studentCursor.getColumnIndex(Student_col_id);
                    int phoneColumnIndex = studentCursor.getColumnIndex(Student_col_phone);
                    int aidColumnIndex = studentCursor.getColumnIndex(Student_col_academic_number);
                    int deptColumnIndex = studentCursor.getColumnIndex(Student_col_dept);
                    int imageColumnIndex = studentCursor.getColumnIndex(Student_col_image_uri);

                    if (firstNameColumnIndex >= 0 && lastNameColumnIndex >= 0) {
                        firstName = studentCursor.getString(firstNameColumnIndex);
                        lastName  = studentCursor.getString(lastNameColumnIndex);
                        Email  = studentCursor.getString(emailColumnIndex);
                        id  = studentCursor.getString(idColumnIndex);
                        phoneNumber  = studentCursor.getString(phoneColumnIndex);
                        aid  = studentCursor.getString(aidColumnIndex);
                        department  = studentCursor.getInt(deptColumnIndex);
                        image_uri  = studentCursor.getString(imageColumnIndex);


                        pref.edit().putString("fName",firstName).apply();
                        pref.edit().putString("lName",lastName).apply();
                        pref.edit().putString("email",Email).apply();
                        pref.edit().putString("id",id).apply();
                        pref.edit().putString("phoneNumber",phoneNumber).apply();
                        pref.edit().putString("aid",aid).apply();
                        pref.edit().putString("image_uri",image_uri).apply();
                        pref.edit().putString("department",getDepartmentNameById(department)).apply();


                    }
                }
                studentCursor.close();
                break;
        }

        return isValid;
    }


    public ArrayList<SubjectRegestrationModel> getCourses_for_students() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<SubjectRegestrationModel> arrayList = new ArrayList<>();

        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String studentId = preferences.getString("id", "");
        int student_Id = Integer.parseInt(studentId);

        // Get the student department ID



        int studentDepartmentId = getStudentDepartmentId_byStudent_id(student_Id);



        // Exclude the registered subjects in the query
        String selection = "Courses.id NOT IN (SELECT enrollment_course_id FROM Enrollment WHERE enrollment_student_id = ? )" +
                " AND Courses.course_department_id = ?";
        String[] selectionArgs = { String.valueOf(student_Id), String.valueOf(studentDepartmentId) };

        Cursor cursor = db.query("Courses", new String[]{Courses_col_name, Courses_col_code},
                selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String columnCode = cursor.getString(cursor.getColumnIndex(Courses_col_code));
            @SuppressLint("Range") String columnName = cursor.getString(cursor.getColumnIndex(Courses_col_name));
            SubjectRegestrationModel model = new SubjectRegestrationModel(columnName, columnCode);
            arrayList.add(model);
        }

        cursor.close();
        db.close();
        return arrayList;
    }



    @SuppressLint("Range")
    private int getStudentDepartmentId_byStudent_id(int student_id) {
        SQLiteDatabase db = getReadableDatabase();
        int departmentId = -1; // Default value if department ID is not found

        String query = "SELECT " + Student_col_dept + " FROM " + Education_Table_Students +
                " WHERE " + Student_col_id + " = ?";
        String[] selectionArgs = { String.valueOf(student_id) };
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            departmentId = cursor.getInt(cursor.getColumnIndex(Student_col_dept));
        }

        cursor.close();

        return departmentId;
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
        values.put(Student_col_dept,student.getDept());
        values.put(Student_col_gender,student.getGender());
        values.put(Student_col_email,student.getEmail());
        values.put(Student_col_password,student.getPassword());
        values.put(Student_col_phone,student.getPhone());

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
     * this method for display and search at the same time
     **********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Students> displayStudents(String searchKye){

        ArrayList<Students> studentsArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        if(searchKye.isEmpty())
            cursor =  db.rawQuery("SELECT * FROM "+Education_Table_Students,null);
        else
            cursor = db.rawQuery("SELECT * FROM " + Education_Table_Students + " WHERE LOWER(" + Student_col_first_name + ") LIKE '%' || ? || '%' OR LOWER(" + Student_col_academic_number + ") LIKE '%' || ? || '%'", new String[] { searchKye, searchKye });


        if(cursor != null && cursor.moveToFirst()){

            do{
                String fName = cursor.getString(cursor.getColumnIndex(Student_col_first_name));
                String aid = cursor.getString(cursor.getColumnIndex(Student_col_academic_number));
                int dept = cursor.getInt(cursor.getColumnIndex(Student_col_dept));
                String email = cursor.getString(cursor.getColumnIndex(Student_col_email));
                String password = cursor.getString(cursor.getColumnIndex(Student_col_password));
                String gender = cursor.getString(cursor.getColumnIndex(Student_col_gender));
                String phone = cursor.getString(cursor.getColumnIndex(Student_col_phone));

                Students students = new Students(fName,aid,email,password,gender,phone,dept);

                studentsArrayList.add(students);

            }while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
//        db.close();

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
        values.put(Student_col_dept,student.getDept());
        values.put(Student_col_phone,student.getPhone());

        String[] args = {student.getEmail()};

        int result =  db.update(Education_Table_Students,values,""+Student_col_email+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated

        return result > 0;
    }

    /**deleteStudent()
     **********************************************************************************************/
    public boolean deleteStudent(String email){

        SQLiteDatabase db =getWritableDatabase();
        String[] args ={email};

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
        values.put(Doctors_col_phone,doctor.getPhone());


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


    /**display  Doctors()
     * this method for display and search at the same time
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Doctors> displayDoctors(String searchKye){

        ArrayList<Doctors> doctorsArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        if(searchKye.isEmpty())
            cursor =  db.rawQuery("SELECT * FROM "+Education_Table_Doctors,null);
        else
            cursor = db.rawQuery("SELECT * FROM " + Education_Table_Doctors + " WHERE LOWER(" +Doctors_col_first_name+ ") LIKE '%' || ? || '%' OR LOWER(" + Doctors_col_email + ") LIKE '%' || ? || '%'", new String[] { searchKye, searchKye });




        if(cursor != null && cursor.moveToFirst()){

            do{
                String fName = cursor.getString(cursor.getColumnIndex(Doctors_col_first_name));
                String email = cursor.getString(cursor.getColumnIndex(Doctors_col_email));
                String password = cursor.getString(cursor.getColumnIndex(Doctors_col_password));
                String gender = cursor.getString(cursor.getColumnIndex(Doctors_col_gender));
                String phone = cursor.getString(cursor.getColumnIndex(Doctors_col_phone));

                Doctors doctors = new Doctors(fName,email,password,gender,phone);

                doctorsArrayList.add(doctors);

            }while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
//        db.close();

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
        values.put(Doctors_col_phone,doctor.getPhone());

        String[] args = {doctor.getEmail()};

        int result =  db.update(Education_Table_Doctors,values,""+Doctors_col_email+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated

        if(result>0)
            Toast.makeText(context, "Changes saved." , Toast.LENGTH_SHORT).show();

        return result > 0;
    }

    /**Delete Doctor()
     **********************************************************************************************/
    public boolean deleteDoctor(String email){

        SQLiteDatabase db =getWritableDatabase();
        String[] args ={email};

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

                String name = cursor.getString(cursor.getColumnIndex(Doctors_col_first_name));

                docNames.add(name);

            }while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
//        db.close();


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

    /**display Departments()
     * this method for display and search at the same time
     * ********************************************************************************************/
    @SuppressLint({"Range", "SuspiciousIndentation"})
    public ArrayList<Departments> displayDepartments(String searchKye)
    {
        ArrayList<Departments> departmentsArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        if(searchKye.isEmpty())
            cursor = db.rawQuery("SELECT * FROM "+Education_Table_Departments,null);
        else
            cursor = db.rawQuery("SELECT * FROM " + Education_Table_Departments + " WHERE LOWER(" + Department_col_name + ") LIKE '%' || ? || '%' OR LOWER(" + Department_col_code + ") LIKE '%' || ? || '%'", new String[] { searchKye, searchKye });

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

        assert cursor != null;
        cursor.close();
        db.close();

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

        String[] args = {codeBeforeUpdate};


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
        String[] args ={code};

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

                deptName.add(name);

            }while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
//        db.close();

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
        values.put(Courses_col_level, course.getLevel());
        values.put(Courses_col_hours, course.getNumberOfHours());
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


    /**display Courses()
     * this method for display and search at the same time
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Courses> displayCourses(String searchKye)
    {
        ArrayList<Courses> coursesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        if(searchKye.isEmpty())
            cursor = db.rawQuery("SELECT * FROM "+Education_Table_Courses,null);
        else
            cursor = db.rawQuery("SELECT * FROM " + Education_Table_Courses + " WHERE LOWER(" + Courses_col_name + ") LIKE '%' || ? || '%' OR LOWER(" + Courses_col_code + ") LIKE '%' || ? || '%'", new String[] { searchKye, searchKye });


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
                int level = cursor.getInt(cursor.getColumnIndex(Courses_col_level));
                int numberOfHours = cursor.getInt(cursor.getColumnIndex(Courses_col_hours));

                Courses course = new Courses(id,code,name,preRequest,dept,doctor,level,numberOfHours);

                coursesList.add(course);
            }while (cursor.moveToNext());
        }

        assert cursor != null;
        cursor.close();
//        db.close();

        return coursesList;

    }

    /**updateAdminImage()
     * This method To update Admin In image And save image_uri in data base
     * ********************************************************************************************/
    public void updateAdminImage(String email, String image_uri){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Admin_col_image_uri,image_uri);

        String[] args = {email};

        db.update(Education_Table_Admins,values,""+Admin_col_email+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated

        }

    /**updateDoctorImage()
     * This method To update Admin In image And save image_uri in data base
     * ********************************************************************************************/
    public void updateDoctorImage(String email, String image_uri){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Doctors_col_image_uri,image_uri);

        String[] args = {email};

        db.update(Education_Table_Doctors,values,""+Doctors_col_email+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated

    }

    /**updateStudentImage()
     * This method To update Admin In image And save image_uri in data base
     * ********************************************************************************************/
    public void updateStudentImage(String email, String image_uri){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Student_col_image_uri,image_uri);

        String[] args = {email};

        db.update(Education_Table_Students,values,""+Student_col_email+"=?",args); //return Number Of Rows Which Are Updated Or Return 0 If No Item Updated

    }


    /**display Doctor's Courses()
     * This method return only courses which this doctor teach it , not all courses
     * ********************************************************************************************/
    @SuppressLint("Range")
    public ArrayList<Courses> displayDoctorCourses()
    {
        ArrayList<Courses> coursesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        SharedPreferences preferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
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

        assert cursor != null;
        cursor.close();
//        db.close();

        return coursesList;

    }

    /**getEnrolledStudentCountByCourseId()
     * ********************************************************************************************/
    public int getEnrolledStudentCountByCourseId(int courseId){
        int count;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Education_Table_Enrollment + " WHERE " + Enrollment_col_course_id + " =?", new String[]{courseId+""});

        count = cursor.getCount();

        return count;
    }

    /**getStudentDegreeByCourseAndStudentId()
     *********************************************************************************************/
    @SuppressLint("Range")
    public Enrollments getStudentDegreeByCourseAndStudentId(int courseId, int studentId){

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

        int result;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        if(enrollment.getStudent_degree() == -1){//if exam not corrected till now
            values.put(Enrollment_col_student_degree,(Integer) null);
            values.put(Enrollment_col_student_course_hours,(Integer) null );
        }
        else{
            values.put(Enrollment_col_student_degree,enrollment.getStudent_degree());
            if(enrollment.getStudent_degree() >= 50)//if student passed in this course , set number of hours
               values.put(Enrollment_col_student_course_hours, getCourseHoursByCourseId(enrollment.getCourse_id()));
            else //if student didn't pass in this course , set number of hours = 0
                values.put(Enrollment_col_student_course_hours,0);
        }
            values.put(Enrollment_col_student_grade,enrollment.getStudent_grade());


        String[] args = {String.valueOf(enrollment.getStudent_id()),String.valueOf(enrollment.getCourse_id())};


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
        values.put(Courses_col_hours,course.getNumberOfHours());
        values.put(Courses_col_level,course.getLevel());
        if(course.getPreRequest() != -1)
            values.put(Courses_col_PreRequest_id, course.getPreRequest());
        else
            values.put(Courses_col_PreRequest_id, (Integer) null);
        String[] args = {codeBeforeUpdate};


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
        String[] args ={code};

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

                String name = cursor.getString(cursor.getColumnIndex(Courses_col_name));

                coursesNames.add(name);

            }while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
//        db.close();

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
        String departmentName = ""; // Default value if department name is not found

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


    /**getDoctorNameById()
     *********************************************************************************************/
    @SuppressLint("Range")
    public int getCourseHoursByCourseId(int courseId){
        SQLiteDatabase db = getReadableDatabase();
        int courseNumberOfHours = -1;

        String query = "SELECT " + Courses_col_hours + " FROM " + Education_Table_Courses +
                " WHERE " + Courses_col_id + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(courseId)});

        if (cursor.moveToFirst()) {
            courseNumberOfHours = cursor.getInt(cursor.getColumnIndex(Courses_col_hours));
        }

        cursor.close();
        return courseNumberOfHours;

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
     * Retrieves a list of enrolled students for a given course ID.
     * @param courseId The ID of the course.
     * @return An ArrayList of Students representing the enrolled students.
     **********************************************************************************************/

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


    /*********************************************************/

    public ArrayList<SubjectModel> Get_all_courses_for_student_afterRegist() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<SubjectModel> arrayList = new ArrayList<>();
        SharedPreferences preferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String StudentId = preferences.getString("id", "");
        int S_id = Integer.parseInt(StudentId);
        String selection = "Courses.id = Enrollment.enrollment_course_id AND Enrollment.enrollment_student_id = " + S_id +"";

        String query = "SELECT Courses.name, Courses.code FROM Courses JOIN Enrollment ON Courses.id = Enrollment.enrollment_course_id WHERE " + selection+"";

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String column_code = cursor.getString(cursor.getColumnIndex("code"));
            @SuppressLint("Range") String column_name = cursor.getString(cursor.getColumnIndex("name"));
            SubjectModel model = new SubjectModel(column_name,column_code);
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
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"PreRequests_id"};
        String selection = "name = ?";
        String[] selectionArgs = {subjectName};
        Cursor cursor = db.query("Courses", columns, selection, selectionArgs, null, null, null);

        boolean hasPrerequisite = false;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("PreRequests_id");
            int prerequisiteId = cursor.getInt(columnIndex);
            hasPrerequisite = (prerequisiteId != 0);
           if(hasPrerequisite==true){System.out.println("have pre request"+prerequisiteId);} else {
               System.out.println("does not have pre");
           }
        }

        cursor.close();
        return hasPrerequisite;
    }







    public boolean pre_thereExist_inEnrollment_course_id(int pre_id) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "enrollment_course_id = ? AND enrollment_student_id = ? ";
        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String studentId = preferences.getString("id", "");
        int student_id = Integer.parseInt(studentId);
        String[] selectionArgs = {String.valueOf(pre_id), String.valueOf(student_id)};

        Cursor cursor = db.query("Enrollment", new String[]{Enrollment_col_course_id}, selection, selectionArgs, null, null, null);
        int coursePreId = 0;

        if (cursor.moveToFirst()) {
            coursePreId = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        if (coursePreId > 0) {
            System.out.println("The student has registered the prerequisite.");
            return true;
        } else {
            return false;
        }
    }

    public int getPreRequestIdBy_Name(String subjectName) {

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
        System.out.println("pre request id"+coursePreId);

        return coursePreId;
    }
    public boolean deleteSubjectfromEnrollment(int course_id) {
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

    //*****************************************************************

    public String getPreRequestNameId(int id) {

        SQLiteDatabase db = getReadableDatabase();
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query("Courses", new String[]{"name"}, selection, selectionArgs, null, null, null);
        String coursePreId = "None";

        if (cursor.moveToFirst()) {
            coursePreId = cursor.getString(0);
        }

        cursor.close();
        db.close();

        return coursePreId;
    }
    //**************************************
    public String getDoctorName_ById(int Id){
        SQLiteDatabase db = getReadableDatabase();
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(Id)};
        String doctorName = "None"; // Default value if doctor name is not found


        Cursor cursor = db.query("Courses", new String[]{Courses_col_doctor_id}, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            doctorName = cursor.getString(0);
        }

        cursor.close();
        db.close();


        return doctorName;

    }
    //**************************************
    public int getDoctorIdByCourseName(String CourseName){
        SQLiteDatabase db = getReadableDatabase();
        String selection = "name = ?";
        String[] selectionArgs = {String.valueOf(CourseName)};
        int doctorId = 0; // Default value if doctor name is not found


        Cursor cursor = db.query("Courses", new String[]{Courses_col_doctor_id}, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            doctorId = cursor.getInt(0);
        }

        cursor.close();
        db.close();


        return doctorId;

    }

    public int getDepartmentIdByCourseName(String CourseName){
        SQLiteDatabase db = getReadableDatabase();
        String selection = "name = ?";
        String[] selectionArgs = {String.valueOf(CourseName)};
        int departmentId = 0; // Default value if doctor name is not found


        Cursor cursor = db.query("Courses", new String[]{Courses_col_department_id}, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            departmentId = cursor.getInt(0);
        }

        cursor.close();
        db.close();


        return departmentId;

    }
    public String getDepartmentName_ById(int id){
        SQLiteDatabase db = getReadableDatabase();
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        String departmentName = "None"; // Default value if doctor name is not found


        Cursor cursor = db.query("Departments", new String[]{Department_col_name}, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            departmentName = cursor.getString(0);
        }

        cursor.close();
        db.close();


        return departmentName;

    }
    //******************************


    public ArrayList<StudentPassedModel> getPassedCoursesForStudents() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<StudentPassedModel> arrayList = new ArrayList<>();

        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String studentId = preferences.getString("id", "");
        int sId = Integer.parseInt(studentId);

        String selection = "enrollment_student_id = ? AND enrollment_student_degree >= 50 ";
        String[] selectionArgs = { String.valueOf(sId) };

        Cursor cursor = db.query("Enrollment",
                new String[]{ "enrollment_student_grade", "enrollment_student_degree", "enrollment_course_id" },
                selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int courseId = cursor.getInt(cursor.getColumnIndex("enrollment_course_id"));
            @SuppressLint("Range") String studentGrade = cursor.getString(cursor.getColumnIndex("enrollment_student_grade"));
            @SuppressLint("Range") int studentDegree = cursor.getInt(cursor.getColumnIndex("enrollment_student_degree"));
            String courseName=get_course_by_Course_Id(courseId);
            StudentPassedModel model = new StudentPassedModel(courseName, studentDegree, studentGrade);
            arrayList.add(model);
        }

        cursor.close();
        db.close();
        return arrayList;
    }

    public String get_course_by_Course_Id(int Course_Id) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "id =' " + Course_Id + "'";

        Cursor cursor = db.query("Courses", new String[]{Courses_col_name}, selection, null, null, null, null);
        String Course_Name = null;
        if (cursor.moveToFirst()) {
            do {
                Course_Name = cursor.getString(0);

            } while (cursor.moveToNext());
            cursor.close();
            db.close();

        }
        return Course_Name;
    }

    public int getSumOfSubjectHours() {
        int sum=0;
        SQLiteDatabase db = getReadableDatabase();
        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String studentId = preferences.getString("id", "");
        int sId = Integer.parseInt(studentId);

        String selection = "enrollment_student_id =' " + sId + "'AND enrollment_student_degree>=50";

        Cursor cursor = db.query("Enrollment", new String[]{Enrollment_col_student_course_hours}, selection, null, null, null, null);
        int Course_Hours = 0;
        if (cursor.moveToFirst()) {
            do {
                Course_Hours = cursor.getInt(0);
                sum+=Course_Hours;

            } while (cursor.moveToNext());
            cursor.close();
            db.close();

        }
        return sum;
    }

    public int getNumberOfPassedSubjects() {
        SQLiteDatabase db = getReadableDatabase();
        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String studentId = preferences.getString("id", "");
        int sId = Integer.parseInt(studentId);
        int result=0;
        String query = "SELECT COUNT(*) FROM Enrollment WHERE enrollment_student_id = ? AND enrollment_student_degree >= 50";
        String[] selectionArgs = {String.valueOf(sId)};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            result = cursor.getInt(0);

        }
        return result;
    }

    public void deleteFailedSubjectsFromRegistedSubject() {
        SQLiteDatabase db = getWritableDatabase();
        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String studentId = preferences.getString("id", "");
        int student_id = Integer.parseInt(studentId);

        String selection = Enrollment_col_student_degree + " <50 AND " + Enrollment_col_student_id + " = ?";
        String[] selectionArgs = { String.valueOf(student_id)};
        int result = db.delete(Education_Table_Enrollment, selection, selectionArgs);


    }

    public Integer getSubjectDegree_Id(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "enrollment_course_id ='" + id + "'";

        Cursor cursor = db.query("Enrollment", new String[]{Enrollment_col_student_degree}, selection, null, null, null, null);
        Integer CourseDegree = NULL;
        if (cursor.moveToFirst()) {
            CourseDegree = cursor.getInt(0);
        }
        cursor.close();
        db.close();

        return CourseDegree;
    }

    public int getCreditbySubjectName(int subjectName_Id) {
        SQLiteDatabase db = getReadableDatabase();
        SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        String studentId = preferences.getString("id", "");
        int sId = Integer.parseInt(studentId);
        String selection = "enrollment_course_id =' " + subjectName_Id + "'AND enrollment_student_id="+sId;

        Cursor cursor = db.query("Enrollment", new String[]{Enrollment_col_student_course_hours}, selection, null, null, null, null);
        int CourseHours = 0;
        if (cursor.moveToFirst()) {
            do {
                CourseHours = cursor.getInt(0);

            } while (cursor.moveToNext());
            cursor.close();
            db.close();

        }

        return CourseHours;
    }
}

