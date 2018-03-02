package groupid.dcmnan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.common.cache.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author bachv
 */
public class PrimeUsingCache {
    private static LoadingCache<Integer, FibSeq> cache;
    static{
	cache = CacheBuilder.newBuilder()
	    .expireAfterAccess(10, TimeUnit.SECONDS)
	    .build(
		new CacheLoader<Integer, FibSeq>(){
		    @Override
		    public FibSeq load(Integer id) throws Exception{
			System.out.println("nocache found, tao primeTbl voi n = "+id);
			return getFibSequence(id);
		    }
		}
	    );
    }
    public static LoadingCache<Integer, FibSeq> getCache() {
	return cache;
    }
    public static FibSeq getFibSequence(int n){
	FibSeq fib = new FibSeq(n);
	
	return fib;
    }
    public long size(){
	return cache.size();
    }
}
class FibSeq{
    private ArrayList<Integer> Seq;
    private int n;

    public FibSeq(int n) {
	Seq = new ArrayList<Integer>();
	this.n = n;
	boolean [] isPrime = new boolean[n+1];
	for(int i = 0; i < isPrime.length; i++) isPrime[i] = true;
	isPrime[2] = true;
	for(int i = 2; i <= n; i++){
	    if(isPrime[i] == false)continue;
	    Seq.add(i);
	    for(int j = i*2; j <= n; j+=i){
		isPrime[j] = false;
	    }
	}
    }

    public ArrayList<Integer> getFibSeq() {
	return Seq;
    }
    
}