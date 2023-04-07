package com.example.jeucalcul.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jeucalcul.model.entities.Score;

import java.util.ArrayList;
import java.util.List;


public class ScoreDao extends BaseDao<Score> {
    public static String TABLE_NAME = "SCORES";
    public static String COLUMN_NICKNAME = "NICKNAME";
    public static String COLUMN_SCORE = "SCORE";

    public ScoreDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void putValues(ContentValues values, Score entity) {
        values.put(COLUMN_NICKNAME,entity.getNickname());
        values.put(COLUMN_SCORE,entity.getScore());
    }

    @Override
    protected Score getEntity(Cursor cursor) {
        Integer indexColumnNickname = cursor.getColumnIndex(COLUMN_NICKNAME);
        Integer indexColumnScore = cursor.getColumnIndex(COLUMN_SCORE);
        if(cursor.getCount()>0) {
            Score monScore = new Score(cursor.getString(indexColumnNickname), cursor.getInt(indexColumnScore));
            return monScore;
        }
        return null;
    }

    public List<Score> getHighscores() {
        List<Score> scores = new ArrayList<>();

        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        String query = "SELECT " + COLUMN_NICKNAME + ", MAX(" + COLUMN_SCORE + ") AS " + COLUMN_SCORE +
                " FROM " + TABLE_NAME +
                " GROUP BY " + COLUMN_NICKNAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Score highscore = this.getEntity(cursor);
                scores.add(highscore);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return scores;
    }
}
