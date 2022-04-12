package cursul2_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void ex1() throws ExecutionException, InterruptedException {
        Double number = 20.d;

        CompletableFuture<Double> getSQRT = CompletableFuture.supplyAsync(() -> {
            if (number < 0){
                throw new IllegalArgumentException("Number can't be negative.");
            }
            Double number_sqrt = Math.sqrt(number);
            return number_sqrt;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return Double.valueOf(-1.d);
        });

        Double result = getSQRT.get();
        System.out.println(result);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ex1();
    }
}
