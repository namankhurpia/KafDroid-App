package com.namankhurpia.apachekafka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.namankhurpia.apachekafka.model.Cluster;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ClusterManager";
    private static final String TABLE_NAME = "clusterdetails";
    private static final String SERVER_ID = "serverid";
    private static final String CLUSTER_NAME = "clustername";
    private static final String BOOTSTRAP_SERVER = "bootstrapserver";
    private static final String ZOOKEEPER_SERVER = "zookeeperserver";
    private static final String SERVER_COLOR = "servercolor";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLUSTER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + SERVER_ID + " INTEGER PRIMARY KEY,"
                + CLUSTER_NAME + " TEXT,"
                + BOOTSTRAP_SERVER + " TEXT,"
                + ZOOKEEPER_SERVER + " TEXT,"
                + SERVER_COLOR + "TEXT" + ")";
        db.execSQL(CREATE_CLUSTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void AddCluster(Cluster cluster) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLUSTER_NAME, cluster.getClusterName());
        values.put(BOOTSTRAP_SERVER, cluster.getBootstrapServer());
        values.put(ZOOKEEPER_SERVER, cluster.getZookeeperServer());
        values.put(SERVER_COLOR, cluster.getColor());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public Cluster getClusterById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { CLUSTER_NAME, BOOTSTRAP_SERVER, ZOOKEEPER_SERVER, SERVER_COLOR }, SERVER_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Cluster contact = new Cluster(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return contact;
    }


    public List<Cluster> getAllClusters() {
        List<Cluster> clusterList = new ArrayList<Cluster>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cluster cluster = new Cluster();
                cluster.setId(Integer.parseInt(cursor.getString(0)));
                cluster.setClusterName(cursor.getString(1));
                cluster.setBootstrapServer(cursor.getString(2));
                cluster.setZookeeperServer(cursor.getString(3));
                cluster.setColor(cursor.getString(4));

                clusterList.add(cluster);
            } while (cursor.moveToNext());
        }

        return clusterList;
    }


    public int updateCluster (Cluster cluster) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLUSTER_NAME, cluster.getClusterName());
        values.put(BOOTSTRAP_SERVER, cluster.getBootstrapServer());
        values.put(ZOOKEEPER_SERVER, cluster.getZookeeperServer());
        values.put(SERVER_COLOR, cluster.getColor());

        return db.update(TABLE_NAME, values, SERVER_ID + " = ?", new String[] { String.valueOf(cluster.getId()) });
    }


    public void deleteCluster(Cluster cluster) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, SERVER_ID + " = ?",
                new String[] { String.valueOf(cluster.getId()) });
        db.close();
    }

    public int getClusterListSize() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}