package signal_model;

import java.util.*;

public class SignalFlowGraph {

    private int nodes_number ; 
    private Gain[][] flowGraph ;
    private ArrayList<ArrayList<Integer>> forwardPaths ;
    private ArrayList<ArrayList<Integer>> loops ;
    private HashSet<Integer> takens;
    private boolean [] sieve;
    private int [] factors;
    private ArrayList<Integer> loopOrder;
    private ArrayList<Integer> nontouching ;
    
    public SignalFlowGraph(int nodes_number)
    {
        this.nodes_number = nodes_number ;
        flowGraph = new Gain[nodes_number][nodes_number];
        forwardPaths = new ArrayList<ArrayList<Integer>>();
        loops = new ArrayList<ArrayList<Integer>>();
        sieve = new boolean[1000];
        factors = new int[nodes_number];
        takens = new HashSet<Integer>();
        loopOrder = new ArrayList<Integer>();
        nontouching = new ArrayList<Integer>();
        
        sieve();
        fillFactors();
    }
    
    public static int GCD(int a ,int b)
    {
	if(b==0) return a;
	return GCD(b,a%b);
    }
    
    private void fillFactors()
    {
        int count =0;
        for(int i=2;i<sieve.length&&count<factors.length;i++)
        {
            if(!sieve[i])
            {
                factors[count]=i;
                count++;
            }
        }
    }
    
    
    private void sieve()
    {
        sieve[0]=true;
        sieve[1]=true;
        for(int i = 2; i <sieve.length ; i++)
        {
            if(!sieve[i])
            {
                for(int j = 2*i;j<sieve.length; j+=i)
                    sieve[j]=true;
            }
        }
    }
    
    public void join(int from,int to,Gain gain) throws Exception
    {
        if(flowGraph[from][to]!=null)
        {
            throw new Exception();
        } else {
            flowGraph[from][to] = gain;
        }
    }
    
    public void generatePaths()
    {
        int[] path = new int[flowGraph.length];
        boolean[] check = new boolean[flowGraph.length];
        check[0] = true ;
        path[0] = 0;
        makePath(1,0,path,check);
        for(int i =0;i<nodes_number;i++)
        {
            path = new int[nodes_number];
            check = new boolean[nodes_number];
            path[0] = i;
            makeLoop(i,1,i,path,check);
        }
    }
    
    public void nontouchingloops()
    {
        int x = 1<<loops.size();
        for(int i=3;i<x;i++)
        {
            if(Integer.bitCount(i)>1)
            {
               if(isValid(Integer.toBinaryString(i).toCharArray()))
               {
                   nontouching.add(i);
               }
            }
        }
    }
    
    
    private boolean isValid(char[] a)
    {
        
        for(int i=0;i<a.length;i++)
        {
            for(int j=i+1;j<a.length;j++)
            {
                if(a[i]==a[j]&a[i]=='1')
                {
                    if(GCD(loopOrder.get(i), loopOrder.get(j))!=1)
                        return false;
                }
            }
        }
        
        return true;
    }
    
    
    
    
    
    public void makePath(int index , int cur , int[] path, boolean[] check)
    {
        if(cur == nodes_number-1)
        {
            forwardPaths.add(new ArrayList<Integer>());
            for(int i=0;i<index;i++)
            {
                forwardPaths.get(forwardPaths.size()-1).add(path[i]);
            }
            
        }
        for(int i=0;i<nodes_number;i++)
        {
            if(flowGraph[cur][i]!=null&&!check[i])
            {
                check[i] = true;
                path[index] = i ;
                makePath(index+1, i , path, check);
                check[i] = false;
            }
        }
    }
    
    public void makeLoop(int destination ,int index , int cur , int[] path, boolean[] check)
    {
        if(cur == destination&&check[cur])
        {
            int p_value = 1;
            for(int i=0;i<index-1;i++)
            {
                p_value *= factors[path[i]];
            }
            
            if(!takens.contains(p_value))
            {
                takens.add(p_value);
                loops.add(new ArrayList<Integer>());
                loopOrder.add(p_value);
                for(int i=0;i<index;i++)
                {
                    loops.get(loops.size()-1).add(path[i]);
                }
                
            }
            
            
        }
        for(int i=(cur==destination ? cur:0);i<nodes_number;i++)
        {
            if(flowGraph[cur][i]!=null&&!check[i])
            {
                check[i] = true;
                path[index] = i ;
                makeLoop(destination , index+1, i , path, check);
                check[i] = false;
            }
        }
    }
    
    
    
    public void printForwardPaths()
    {
        /*
         *  abo3mera by7eb elprintat
         */
        for(int i=0;i<forwardPaths.size();i++)
        {
            for(int j=0;j<forwardPaths.get(i).size();j++)
            {
                System.out.print("y"+(forwardPaths.get(i).get(j)+1)+" ");
            }
            System.out.println();
        }
    }
    public void printLoops()
    {
        /*
         *  abo3mera by7eb elprintat
         */
        for(int i=0;i<loops.size();i++)
        {
            for(int j=0;j<loops.get(i).size();j++)
            {
                System.out.print("y"+(loops.get(i).get(j)+1)+" ");
            }
            System.out.println();
        }
    }
    
    public void printnontouching()
    {
        System.out.println(Arrays.toString(nontouching.toArray()));
    }
    
    
    
    
    
    
}
