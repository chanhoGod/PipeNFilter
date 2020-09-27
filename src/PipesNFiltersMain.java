

/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */


/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description
 *     
 */
public class PipesNFiltersMain {

    public static void main(String[] args) {
        try {
            CommonForFilter filter1 = new SourceFilter("Students.txt");
            CommonForFilter filter2 = new SinkFilter("Output.txt");
            CommonForFilter filter3 = new MiddleFilter();
            
            filter1.connectOutputTo(filter3);
            filter3.connectOutputTo(filter2);
            
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
            
            thread1.start();
            thread2.start();
            thread3.start();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}