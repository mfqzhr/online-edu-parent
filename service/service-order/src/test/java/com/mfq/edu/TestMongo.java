package com.mfq.edu;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

class MongoDB {
//1.建立一个 Mongo 的数据库连接对象

    static Mongo connection = null;
    static DB db = null;

    //2.创建相关数据库的连接创建相关数据库的连接static DB db = null;
    public MongoDB(String dbName) throws UnknownHostException, MongoException {
        connection = new Mongo("121.36.42.109:27017");
        db = connection.getDB(dbName);
    }

    public static void main(String[] args) throws UnknownHostException, MongoException {
//实例化实例化
        MongoDB mongoDb = new MongoDB("foobar");
/**
 * 1.创建一个名字叫创建一个名字叫 javadb 的数据库的数据库
 */
// mongoDb.createCollection("javadb");
//若不用创建则直接连接对应的数据库DBCollection collection = db.getCollection("javadb");
/**
 * 2.为集合为集合 javadb 添加一条数据添加一条数据
 */try {
            DBObject dbs = new BasicDBObject();
            dbs.put("name", "uspcat.com");
            dbs.put("age", 2);
            List<String> books = new ArrayList<String>();
            books.add("EXTJS");
            books.add("MONGODB");
            dbs.put("books", books);
            mongoDb.insert(dbs, "javadb");
        } catch (Exception e) {
// TODO Auto-generated catch block e.printStackTrace();
        }
/**
 * 3.批量插入数据批量插入数据
 */
        DBObject lisi;
        try {
            List<DBObject> dbObjects = new ArrayList<DBObject>();
            DBObject jim = new BasicDBObject("name", "jim");
            lisi = new BasicDBObject("name", "lisi");
            dbObjects.add(jim);
            dbObjects.add(lisi);
            mongoDb.insertBatch(dbObjects, "javadb");
        } catch (Exception e) {


        }
/**

 // TODO Auto-generated catch block e.printStackTrace();
 * 4.根据根据 ID 删除数据删除数据
 */
// mongoDb.deleteById("502870dab9c368bf5b151a04", "javadb");
/**
 * 5.根据条件删除数据根据条件删除数据4
 */try {
            lisi = new BasicDBObject();
            lisi.put("name", "lisi");
            int count = mongoDb.deleteByDbs(lisi, "javadb");
            System.out.println("删除数据的条数是删除数据的条数是: " + count);
        } catch (Exception e) {
// TODO Auto-generated catch block e.printStackTrace();
        }
/**
 * 6.更新操作更新操作,为集合增加为集合增加 email 属性属性
 */try {
            DBObject update = new BasicDBObject();
            update.put("$set",
                    new BasicDBObject("eamil", "uspcat@126.com"));
            mongoDb.update(new BasicDBObject(), update, false, true, "javadb");
        } catch (Exception e) {
// TODO Auto-generated catch block e.printStackTrace();
        }
/**
 * 7.查询出查询出 persons 集合中的集合中的 name 和和 age
 */try {
            DBObject keys = new BasicDBObject();
            keys.put("_id", false);
            keys.put("name", true);
            keys.put("age", true);
            DBCursor cursor = mongoDb.find(null, keys, "persons");
            while (cursor.hasNext()) {
                DBObject object = cursor.next();

                System.out.println(object.get("name"));
            }
        } catch (Exception e) {
// TODO Auto-generated catch block e.printStackTrace();
        }
/**
 * 7.查询出年龄大于查询出年龄大于 26 岁并且英语成绩小于岁并且英语成绩小于 80 分
 分
 */try {
            DBObject ref = new BasicDBObject();
            ref.put("age", new BasicDBObject("$gte", 26));
            ref.put("e", new BasicDBObject("$lte", 80));
            DBCursor cursor = mongoDb.find(ref, null, "persons");
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                System.out.print(object.get("name") + "-->");
                System.out.print(object.get("age") + "-->");
                System.out.println(object.get("e"));
            }
        } catch (Exception e) {
// TODO Auto-generated catch block e.printStackTrace();
        }
//关闭连接对象关闭连接对象connection.close();
    }

    /**
     * 创建一个数据库集合创建一个数据库集合
     *
     * @param collName 集合名称集合名称
     * @param db       数据库实例数据库实例
     */
    public void createCollection(String collName) {
        DBObject dbs = new BasicDBObject();
        db.createCollection("javadb", dbs);
    }

    /**
     * 为相应的集合添加数据为相应的集合添加数据
     *
     * @param dbs
     * @param collName
     */
    public void insert(DBObject dbs, String collName) {


//1.得到集合得到集合
        DBCollection coll = db.getCollection(collName);
//2.插入操作插入操作coll.insert(dbs);
    }

    /**
     * 为集合批量插入数据为集合批量插入数据
     *
     * @param dbses
     * @param collName
     */
    public void insertBatch(List<DBObject> dbses, String collName) {
//1.得到集合得到集合
        DBCollection coll = db.getCollection(collName);
//2.插入操作插入操作coll.insert(dbses);
    }

    /**
     * 根据根据 id 删除数据删除数据
     *
     * @param id
     * @param collName
     * @return 返回影响的数据条数返回影响的数据条数
     */
    public int deleteById(String id, String collName) {
//1.得到集合得到集合
        DBCollection coll = db.getCollection(collName);
        DBObject dbs = new BasicDBObject("_id", new ObjectId(id));
        int count = coll.remove(dbs).getN();
        return count;
    }

    /**
     * 根据条件删除数据根据条件删除数据
     *
     * @param id
     * @param collName
     * @return 返回影响的数据条数返回影响的数据条数
     */
    public int deleteByDbs(DBObject dbs, String collName) {
//1.得到集合得到集合
        DBCollection coll = db.getCollection(collName);
        int count = coll.remove(dbs).getN();
        return count;
    }

    /**
     * 更新数据更新数据
     *
     * @param find     查询器查询器
     * @param update   更新器更新器
     * @param upsert   更新或插入更新或插入
     * @param multi    是否批量更新是否批量更新
     * @param collName 集合名称集合名称
     * @return 返回影响的数据条数返回影响的数据条数
     */
    public int update(DBObject find, DBObject update,
                      boolean upsert, boolean multi, String collName) {
//1.得到集合得到集合
        DBCollection coll = db.getCollection(collName);
        int count = coll.update(find, update, upsert, multi).getN();
        return count;
    }

    /**
     * 查询器查询器
     *
     * @param ref
     * @param keys     7
     * @param start
     * @param limit
     * @param collName
     * @return
     */
    public DBCursor find(DBObject ref, DBObject keys,
                         String collName) {
//得到集合得到集合
        DBCollection coll = db.getCollection(collName);
        DBCursor cur = coll.find(ref, keys);
        return cur;
    }
}