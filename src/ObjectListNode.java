/**
 * ObjectListNode Class
 * Description: Contains methods in order to create nodes
 * @author Prof
 */

public class ObjectListNode implements ObjectListNodeInterface {
    private Object info;
    private ObjectListNode next;
    
    /**
     * Default constructor  
     */
    public ObjectListNode() {
        info = null;
        next = null;
    }

    /**
     *  One-arg ctor
     * @param o object sent in for info
     */
    public ObjectListNode (Object o) {
        info = o;
        next = null;
    }    
    
    /**
     *  Two-arg ctor
     * @param o object sent in for info
     * @param p the next node
     */
    public ObjectListNode (Object o, ObjectListNode p) {
        info = o;
        next = p;
    }       

    /**
     *  Sets info field
     *  @param o specific object
     */
    public void setInfo(Object o) {
        info = o;
    }    

    /**
     *  Returns object in info field
     *  @return info field
     */
    public Object getInfo() {
        return info;
    }

    /**
     *  Sets next field
     *  @param p the node being set for next
     */
    public void setNext(ObjectListNode p) {
        next = p;
    }

    /**
     *  Returns object in info field
     *  @return the next field
     */
    public ObjectListNode getNext() {
        return next;
    }
}
