/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signal.flow.graph;

/**
 *
 * @author omar
 */
public class Gain {
    /*
     * true positive gain
     * false feedback 
     */
    private boolean sign ;
    /*
     * Gain value
     */
    private String value ;
    
    public Gain()
    {
        this.sign = true;
        this.value = null;
    }
    
    
    public Gain(boolean sign , String value)
    {
        this.sign = sign ;
        this.value = value;
    }
    
    
    public boolean getSign()
    {
        return sign ;
    }
    
    public String getValue()
    {
        return value;
    }
}
