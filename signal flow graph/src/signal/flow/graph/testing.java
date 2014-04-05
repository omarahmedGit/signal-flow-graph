/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signal.flow.graph;

/**
 *
 * @author omar
 */
public class testing {
    
    
    public static void main(String[] args) throws Exception
    {
        SignalFlowGraph test1 = new SignalFlowGraph(6);
    
        test1.join(0,1,new Gain(true,"a"));
        test1.join(1,2,new Gain(true,"c"));
        test1.join(1,3,new Gain(true,"b"));
        test1.join(2,3,new Gain(true,"d"));
        test1.join(3,4,new Gain(true,"e"));
        test1.join(4,5,new Gain(true,"f"));
        test1.join(2,1,new Gain(false,"g"));
        test1.join(4,3,new Gain(false,"h"));
        test1.join(4,1,new Gain(false,"l"));
        test1.generatePaths();
        test1.printForwardPaths();
        System.out.println();
        test1.printLoops();
        test1.nontouchingloops();
        System.out.println();
        test1.printnontouching();
        
    
    }
    
    
    
}
