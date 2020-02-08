package com.mycompany.app;
import java.util.ArrayList;
public class App{
    public static boolean search(ArrayList<Integer> array1, ArrayList<Integer> array2, int start, int end){
        if (array2 == null || array1 == null){
            return false;
        }
        if (end>array1.size() || end>array2.size()){
            return false;
        }
	for (int i=0; i<end; i++){
            if (i>=start && i<=end){
                if (array1.get(i)!=array2.get(i)){
                    return false;
                }
              
            }
        }
        return true;
    }   
}


