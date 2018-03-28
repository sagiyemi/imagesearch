package com.example.sagi.imagesearch.data.local;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class Db {

    private static final String PRIMARY_KEY = " PRIMARY KEY ";
    private static final String TEXT = " TEXT ";
    private static final String INTEGER = " INTEGER ";

    public Db() { }

    public static final class ImageTable {

        public static final String TABLE_NAME = "image";

        // TODO: ID, Primary key, page number, ordinal?

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_LINK = "link";
        public static final String COLUMN_SEARCH_TERM = "search_term";
        public static final String COLUMN_PAGE_NUMBER = "page_number";
        public static final String COLUMN_CONTEXT_LINK = "context_link";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_WIDTH = "width";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                         + COLUMN_SEARCH_TERM + TEXT
                        + "," + COLUMN_PAGE_NUMBER + INTEGER
                        + "," + COLUMN_LINK + TEXT + PRIMARY_KEY
                        + "," + COLUMN_TITLE + TEXT
                        + "," + COLUMN_CONTEXT_LINK + TEXT
                        + "," + COLUMN_HEIGHT + INTEGER
                        + "," + COLUMN_WIDTH + INTEGER
                        + " );";
    }

}
