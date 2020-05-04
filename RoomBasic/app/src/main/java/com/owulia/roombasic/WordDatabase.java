package com.owulia.roombasic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

// singleton
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;

    public abstract WordDao getWordDao();

    static synchronized WordDatabase getDatabase (Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
//                    .allowMainThreadQueries() // 强制运行在主线程
//                    .fallbackToDestructiveMigration() // 破坏性迁移，清除数据库从新建立数据结构
//                    .addMigrations(MIGRATION_1_2) // 版本迁移策略
                    .build();
        }
        return INSTANCE;
    }

    // 新增字段策略
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1");
        }
    };

    // 删除字段策略
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 创建一个新的
            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL, english_word TEXT," +
                    "chinese_meaning TEXT)");
            // 插入数据
            database.execSQL("INSERT INTO word_temp (id, english_word, chinest_meaning)" +
                    "SELECT id, english_word, chinest_meaning FROM word");
            // 删除数据
            database.execSQL("DROP TABLE word");
            // 修改表
            database.execSQL("ALTER TABLE word_temp RENAME TO word");

        }
    };

}
