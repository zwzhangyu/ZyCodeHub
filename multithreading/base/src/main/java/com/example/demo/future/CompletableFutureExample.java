package com.example.demo.future;


import java.util.Set;
import java.util.concurrent.*;

/***
 *
 * CompletableFuture使用分析
 * @author ZhangYu
 * @date 2021/10/23
 */
public class CompletableFutureExample {


    public static void main(String[] args) {
        CompletableFutureExample futureExample = new CompletableFutureExample();
        Set<Integer> queryResult = futureExample.getQueryResult();
        System.out.println(queryResult);
    }



    /**
     * 获取查询结果
     */
    public   Set<Integer>  getQueryResult(){
        final  Set<Integer>   queryResult=new CopyOnWriteArraySet<>();
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(new PriceSearchTask(queryResult));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(new PriceSearchTask(queryResult));
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(new PriceSearchTask(queryResult));
        CompletableFuture<Void> allTask = CompletableFuture.allOf(future1, future2, future3);
        try {
            allTask.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return  queryResult;
      }



      static  class  PriceSearchTask implements Runnable{

          private   Set<Integer>  result;

          public PriceSearchTask(Set<Integer> result) {
              this.result = result;
          }

          @Override
          public void run() {
              int  price=0;
              try {
                  Thread.sleep((long) (Math.random()*4000));
                  price= (int) (Math.random()*4000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              result.add(price);
          }
      }
}
