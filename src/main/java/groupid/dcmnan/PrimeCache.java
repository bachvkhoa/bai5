package groupid.dcmnan;


import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.*;
import static spark.Spark.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bachv
 */
public class PrimeCache {
    public static void main(String[] args) {
	PrimeCache cache = new PrimeCache();
	get("/primecache", (req, res)->{
	    int n = Integer.parseInt(req.queryParams("n"));
	    return cache.getPrimeUsingGuava(n).getFibSeq();
	});
    }
    private FibSeq getPrimeUsingGuava(int n) throws ExecutionException{
	LoadingCache<Integer,FibSeq> primeCache = PrimeUsingCache.getCache();
	System.out.printf("Cache size: %d%n",primeCache.size());
	return primeCache.get(n);
    }
}
