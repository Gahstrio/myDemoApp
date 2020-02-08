package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App
{
    public static boolean search(ArrayList<Integer> array1, ArrayList<Integer> array2, int start, int end) {
        if (array1 == null || array2  == null){
            return false;
        }
        if (end >= array1.size() || end >= array2.size() ){
            return false;
        }
        for (int i=0; i<end+1; i++){
            if (array1.get(i)!=array2.get(i)){
                return false;
            }
        }
        return true;
        
    }
public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));

          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          java.util.ArrayList<Integer> inputList1 = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          String input2 = req.queryParams("input2");
          java.util.Scanner sc2 = new java.util.Scanner(input2);
          while (sc2.hasNext()){
              int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
              inputList1.add(value);
          }
          System.out.println(inputList);


          String input3 = req.queryParams("input3").replaceAll("\\s","");
          String input4 = req.queryParams("input4").replaceAll("\\s","");
          int input3AsInt = Integer.parseInt(input3);
          int input4AsInt = Integer.parseInt(input4);

          boolean result = App.search(inputList,inputList1,input3AsInt,input4AsInt);

         Map map = new HashMap();
          map.put("result", result);
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) -> {
              Map map = new HashMap();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
       return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
