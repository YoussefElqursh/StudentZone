package com.studentzone.Data_Base;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import androidx.annotation.NonNull;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;

public class My_DB extends SQLiteOpenHelper {

    /**
     * Declaration and initiation of My_DB
     ***********************************************************************************************/
    public static final String DB_Name = "Education";

    public static final int DB_Version = 27;


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

        SharedPreferences pref = context.getSharedPreferences("userName",Context.MODE_PRIVATE);
        String firstName = null;
        String lastName = null;
        String Email = null;

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
                Cursor doctorCursor = db.query("" + Education_Table_Doctors + "", new String[]{Doctors_col_first_name,Doctors_col_last_name,Doctors_col_email},
                        "" + Doctors_col_email + "=? AND " + Doctors_col_password + "=?", new String[]{email, password},
                        null, null, null, null);

                if (doctorCursor.moveToFirst()) {
                    isValid = doctorCursor.moveToFirst();

                    int firstNameColumnIndex = doctorCursor.getColumnIndex(Doctors_col_first_name);
                    int lastNameColumnIndex = doctorCursor.getColumnIndex(Doctors_col_last_name);
                    int emailColumnIndex = doctorCursor.getColumnIndex(Doctors_col_email);
                    if (firstNameColumnIndex >= 0 && lastNameColumnIndex >= 0) {
                        firstName = doctorCursor.getString(firstNameColumnIndex);
                        lastName  = doctorCursor.getString(lastNameColumnIndex);
                        Email  = doctorCursor.getString(emailColumnIndex);

                        pref.edit().putString("fName",firstName).apply();
                        pref.edit().putString("lName",lastName).apply();
                        pref.edit().putString("email",Email).apply();


                    }
                }
                doctorCursor.close();
                break;
            case 2:
                Cursor studentCursor = db.query("" + Education_Table_Students + "", new String[]{Student_col_first_name,Student_col_last_name,Student_col_email},
                        "" + Student_col_email + "=? AND " + Student_col_password + "=?", new String[]{email, password},
                        null, null, null, null);

                if (studentCursor.moveToFirst()) {
                    isValid = studentCursor.moveToFirst();
                    int firstNameColumnIndex = studentCursor.getColumnIndex(Student_col_first_name);
                    int lastNameColumnIndex = studentCursor.getColumnIndex(Student_col_last_name);
                    int emailColumnIndex = studentCursor.getColumnIndex(Student_col_email);
                    if (firstNameColumnIndex >= 0 && lastNameColumnIndex >= 0) {
                        firstName = studentCursor.getString(firstNameColumnIndex);
                        lastName  = studentCursor.getString(lastNameColumnIndex);
                        Email  = studentCursor.getString(emailColumnIndex);

                        pref.edit().putString("fName",firstName).apply();
                        pref.edit().putString("lName",lastName).apply();
                        pref.edit().putString("email",Email).apply();

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
            SubjectRegestrationModel model = new SubjectRegestrationModel( cursor.getString(2), cursor.getString(1),false);
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

    /**get name course for student by department*/
    public ArrayList<String> Get_all_courses_for_student(){
        ArrayList<String> courses_name=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(""+Education_Table_Courses+"", new String[] { Courses_col_name },
                null, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                String name_course = cursor.getString(0 );
                courses_name.add(name_course);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return courses_name;
    }
    public ArrayList<String> Get_all_courses_have_pre_for_student(){
        ArrayList<String> courses_name=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection="PreRequests_id is not null";

        Cursor cursor = db.query(""+Education_Table_Courses+"", new String[] { Courses_col_name },
                selection, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                String name_course = cursor.getString(0 );
                courses_name.add(name_course);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return courses_name;
    }
    public ArrayList<SubjectModel> Get_all_courses_for_student_afterRegist(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<SubjectModel> arrayList = new ArrayList();

        Cursor cursor = db.query("Courses", null, null, null, null, null, null);

        while (cursor.moveToNext()) {

            String column_code = cursor.getString(2);
            String column_name = cursor.getString(1);
            SubjectModel model = new SubjectModel( cursor.getString(2), cursor.getString(1));
            arrayList.add(model);
        }
        cursor.close();
        return arrayList;
    }
}



