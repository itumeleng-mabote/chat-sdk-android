package wanderingdevelopment.tk.chatsdkcore.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import wanderingdevelopment.tk.chatsdkcore.entities.JoinThreadWithUser;

import wanderingdevelopment.tk.chatsdkcore.entities.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property PhotoPath = new Property(2, String.class, "photoPath", false, "PHOTO_PATH");
        public final static Property Metadata = new Property(3, String.class, "metadata", false, "METADATA");
        public final static Property UserName = new Property(4, String.class, "userName", false, "USER_NAME");
    }

    private Query<User> thread_UsersInConversationQuery;

    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"PHOTO_PATH\" TEXT," + // 2: photoPath
                "\"METADATA\" TEXT," + // 3: metadata
                "\"USER_NAME\" TEXT);"); // 4: userName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String photoPath = entity.getPhotoPath();
        if (photoPath != null) {
            stmt.bindString(3, photoPath);
        }
 
        String metadata = entity.getMetadata();
        if (metadata != null) {
            stmt.bindString(4, metadata);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(5, userName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String photoPath = entity.getPhotoPath();
        if (photoPath != null) {
            stmt.bindString(3, photoPath);
        }
 
        String metadata = entity.getMetadata();
        if (metadata != null) {
            stmt.bindString(4, metadata);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(5, userName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // photoPath
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // metadata
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // userName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhotoPath(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMetadata(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUserName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "usersInConversation" to-many relationship of Thread. */
    public List<User> _queryThread_UsersInConversation(Long threadId) {
        synchronized (this) {
            if (thread_UsersInConversationQuery == null) {
                QueryBuilder<User> queryBuilder = queryBuilder();
                queryBuilder.join(JoinThreadWithUser.class, JoinThreadWithUserDao.Properties.UserId)
                    .where(JoinThreadWithUserDao.Properties.ThreadId.eq(threadId));
                thread_UsersInConversationQuery = queryBuilder.build();
            }
        }
        Query<User> query = thread_UsersInConversationQuery.forCurrentThread();
        query.setParameter(0, threadId);
        return query.list();
    }

}