package com.rbworks.dev.jdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rbworks.dev.jdm.QuizContract.MovieEntry.KEY_ANSWER;
import static com.rbworks.dev.jdm.QuizContract.MovieEntry.KEY_ID;
import static com.rbworks.dev.jdm.QuizContract.MovieEntry.KEY_OPTA;
import static com.rbworks.dev.jdm.QuizContract.MovieEntry.KEY_OPTB;
import static com.rbworks.dev.jdm.QuizContract.MovieEntry.KEY_OPTC;
import static com.rbworks.dev.jdm.QuizContract.MovieEntry.KEY_QUES;
import static com.rbworks.dev.jdm.QuizContract.MovieEntry.TABLE_QUEST;


public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "quizzer";
    // tasks table name

    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
        Question q1=new Question("Which layout is used to set the layout of Container in Java ?"," SetLayout();", "** layoutContainer();", "setLayout();", "setLayout();");
        this.addQuestion(q1);
        Question q2=new Question("---------- Method returns the string of item which is currently selected .", "getSelectedIndex();", "getSelecetedItem();", "select(int index);", "getSelecetedItem();");
        this.addQuestion(q2);
        Question q3=new Question("---------- Method is used to add menubar on frame window .","addMenuBar();", "setMenubar();","setMenuBar();", "setMenuBar();" );
        this.addQuestion(q3);
        Question q4=new Question("JTabbedPane class is present in ------- package ?", "javax.swing", "java.awt.swing", "java.awt","javax.swing");
        this.addQuestion(q4);
        Question q5=new Question("The genral form used to set a specific type of layout manager is ---","Void SetLayout()","void setLayout()","void setLayout(LayoutManager l)","void setLayout(LayoutManager l)");
        this.addQuestion(q5);
        Question q6=new Question("Which of the following is not a swing class ?","JApplet ","JButton","Canvas","Canvas");
        this.addQuestion(q6);
        Question q7=new Question("---------- Arranges the component in rows and column .","BorderLayout()","GridLayout()","CardLayout()","GridLayout()");
        this.addQuestion(q7);
        Question q8=new Question("what is the return type of getItem() ?","Object","int","String","Object");
        this.addQuestion(q8);

        Question q9=new Question("Which of following method is used to create object of Prepare Statement interface ","preparedStatement();","prepareCall()","None of these ","preparedStatement()");
        this.addQuestion(q9);

        Question q10=new Question("A cookie contains -------- ?","Prgram Information ","Client Information","Server Information","Client Information");
        this.addQuestion(q10);

        Question q11=new Question("JSP stands for ?","Java Server Pages","Java Server Program","Java Script Page","Java Server Pages");
        this.addQuestion(q11);

        Question q12=new Question("The following menthods belongs to the life cycle of the of the servlet","init();","service();","All of the Above","All of the Above");
        this.addQuestion(q12);

        Question q13=new Question("Event is called as -------  ","change in the state of Component","change in the state of Button","Change in the state of Object ","change in the state of Component");
        this.addQuestion(q13);

        Question q14=new Question("--------- Method gives current session . ","getSessionId()","session()","getSession()","change in the state of Component");
        this.addQuestion(q14);

        Question q15=new Question("--------- Method is used to retrive file name in specified URL . ","getFile()","returnFile()","receiveFile()","getFile()");
        this.addQuestion(q15);

        Question q16=new Question("--------- Method is used to return the socket and establish connection between server .","public socket accept()","public socket start()","None of these","public socket accept()");
        this.addQuestion(q16);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + " ORDER BY "+ "RANDOM()";
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
            Collections.shuffle(quesList);
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
