package w54710.autofinanse;

import android.provider.BaseColumns;

/**
 * Created by Piotrek on 2016-07-22.
 */
public class Contract {
    public static final String DB_NAME = "w54710.autofinanse.db";
    public static final int DB_VERSION = 7;

    public class ContractEntry implements BaseColumns {
        public static final String TABLE = "wydatki";
        public static final String COL_TITLE = "title";
        public static final String COL_PRICE = "price";
        public static final String COL_CONTENT = "content";
    }
}