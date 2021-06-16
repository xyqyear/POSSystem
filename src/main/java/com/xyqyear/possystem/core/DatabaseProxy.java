package com.xyqyear.possystem.core;

import java.util.ArrayList;
import java.util.List;

public class DatabaseProxy implements IDatabase {
    private static DatabaseProxy singleton = null;

    private IDatabase realDatabase;

    private List<Sale> saleCache;

    public static DatabaseProxy getInstance() {
        if (singleton == null) {
            singleton = new DatabaseProxy();
        }
        return singleton;
    }

    private DatabaseProxy() {
        realDatabase = new SqliteDatabase();
        saleCache = new ArrayList<Sale>();
        new DatabaseProxyThread(this).start();
    }

    @Override
    public ProductDescription getProductDesc(int id) {
        return realDatabase.getProductDesc(id);
    }

    @Override
    public boolean saveSale(Sale s) {
        saleCache.add(s);
        return true;
    }

    public void flushSale() {
        while (!saleCache.isEmpty()) {
            Sale sale = saleCache.remove(0);
            if (!realDatabase.saveSale(sale)) {
                saleCache.add(sale);
            }
        }
    }
}

class DatabaseProxyThread extends Thread {
    private DatabaseProxy dbProxy;

    public DatabaseProxyThread(DatabaseProxy dbProxy) {
        this.dbProxy = dbProxy;
    }

    @Override
    public void run() {
        while (true) {
            dbProxy.flushSale();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
