import java.util.ArrayList;

public class BTree {

	private static final int T = 4;
    Node mRootNode;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private int size = 0;

	class Node {
        public int value = 0;
        public int[] values = new int[2 * T - 1];
        public Object[] mObjects = new Object[2 * T - 1];
        public Node[] mChildNodes = new Node[2 * T];
        public boolean mIsLeafNode;
        
        int binarySearch(int key) {
                int leftIndex = 0;
        int rightIndex = value - 1;        
 
        while (leftIndex <= rightIndex) {
            final int middleIndex = leftIndex + ((rightIndex - leftIndex) / 2);
            if (values[middleIndex] < key) {
                leftIndex = middleIndex + 1;
            } else if (values[middleIndex] > key) {
                rightIndex = middleIndex - 1;
            } else {
                return middleIndex;
            }
        }
 
        return -1;
        }
        
        boolean contains(int key) {
                return binarySearch(key) != -1;
        }               
        
        void remove(int index, int leftOrRightChild) {
                if (index >= 0) {
                        int i;
                        for (i = index; i < value - 1; i++) {
                                values[i] = values[i + 1];
                                mObjects[i] = mObjects[i + 1];
                                if (!mIsLeafNode) {
                                        if (i >= index + leftOrRightChild) {
                                                mChildNodes[i] = mChildNodes[i + 1];
                                        }
                                }
                        }
                        values[i] = 0;
                        mObjects[i] = null;
                        if (!mIsLeafNode) {
                                if (i >= index + leftOrRightChild) {
                                        mChildNodes[i] = mChildNodes[i + 1];
                                }
                                mChildNodes[i + 1] = null;
                        }
                        value--;
                }
        }
        
        void shiftRightByOne() {
                if (!mIsLeafNode) {
                        mChildNodes[value + 1] = mChildNodes[value];
                }
                for (int i = value - 1; i >= 0; i--) {
                		values[i + 1] = values[i];
                        mObjects[i + 1] = mObjects[i];
                        if (!mIsLeafNode) {
                                mChildNodes[i + 1] = mChildNodes[i];
                        }
                }
        }
        
        int subtreeRootNodeIndex(int key) {
                for (int i = 0; i < value; i++) {                            
                        if (key < values[i]) {
                                return i;
                        }                               
                }
                return value;
        }

    }
    
    public BTree() {
        mRootNode = new Node();
        mRootNode.mIsLeafNode = true;           
    }
    
    public boolean add(int value) {
    	boolean a = false;
    	Node node = mRootNode;
    	if(a == false) {
    		add((Integer)value, node);
    	}
    	size++;
    	return a; 
    }
    
    public void add(int key, Object object) {
    	Node rootNode = mRootNode;      
        if (!update(mRootNode, key, object)) {
                if (rootNode.value == (2 * T - 1)) {
                        Node newRootNode = new Node();
                        mRootNode = newRootNode;
                        newRootNode.mIsLeafNode = false;
                        mRootNode.mChildNodes[0] = rootNode;
                        splitChildNode(newRootNode, 0, rootNode);
                        insertIntoNonFullNode(newRootNode, key, object); 
                } else {
                        insertIntoNonFullNode(rootNode, key, object); 
                }
        }
    }
    
    public void splitChildNode(Node parentNode, int i, Node node) {                
        Node newNode = new Node();
        newNode.mIsLeafNode = node.mIsLeafNode;
        newNode.value = T - 1;
        for (int j = 0; j < T - 1; j++) { 
                newNode.values[j] = node.values[j + T];
                newNode.mObjects[j] = node.mObjects[j + T];
        }
        if (!newNode.mIsLeafNode) {
                for (int j = 0; j < T; j++) { 
                        newNode.mChildNodes[j] = node.mChildNodes[j + T];
                }
                for (int j = T; j <= node.value; j++) {                      
                        node.mChildNodes[j] = null;
                }
        }
        for (int j = T; j < node.value; j++) {
                node.values[j] = 0;
                node.mObjects[j] = null;
        }
        node.value = T - 1;
        
        for (int j = parentNode.value; j >= i + 1; j--) {
                parentNode.mChildNodes[j + 1] = parentNode.mChildNodes[j];
        }
        parentNode.mChildNodes[i + 1] = newNode;        
        for (int j = parentNode.value - 1; j >= i; j--) {
                parentNode.values[j + 1] = parentNode.values[j];
                parentNode.mObjects[j + 1] = parentNode.mObjects[j];
        }               
        parentNode.values[i] = node.values[T - 1];
        parentNode.mObjects[i] = node.mObjects[T - 1];
        node.values[T - 1] = 0;
        node.mObjects[T - 1] = null;
        parentNode.value++;          
    }
 
    public void insertIntoNonFullNode(Node node, int key, Object object) {
        int i = node.value - 1;
        if (node.mIsLeafNode) {

                while (i >= 0 && key < node.values[i]) {
                        node.values[i + 1] = node.values[i];
                        node.mObjects[i + 1] = node.mObjects[i];
                        i--;
                }
                i++;
                node.values[i] = key;
                node.mObjects[i] = object;
                node.value++;
        } else {

                while (i >= 0 && key < node.values[i]) {
                        i--;
                }
                i++;
                if (node.mChildNodes[i].value == (2 * T - 1)) {
                        splitChildNode(node, i, node.mChildNodes[i]);
                        if (key > node.values[i]) {
                                i++;
                        }
                }
                insertIntoNonFullNode(node.mChildNodes[i], key, object);
        }
    }
    
    public void remove(int value) {
    	delete(this.mRootNode, value);
    }
    
    public void delete(Node node, int key) {        
        if (node.mIsLeafNode) { 
                int i;
                if ((i = node.binarySearch(key)) != -1) { 
                        node.remove(i, LEFT);                                
                }
        } else {
                int i;
                if ((i = node.binarySearch(key)) != -1) {                 
                        Node leftChildNode = node.mChildNodes[i];
                        Node rightChildNode = node.mChildNodes[i + 1];                          
                        if (leftChildNode.value >= T) {
                                Node predecessorNode = leftChildNode;
                                Node erasureNode = predecessorNode; 
                                while (!predecessorNode.mIsLeafNode) { 
                                        erasureNode = predecessorNode;
                                        predecessorNode = predecessorNode.mChildNodes[node.value - 1];
                                }
                                node.values[i] = predecessorNode.values[predecessorNode.value - 1];
                                node.mObjects[i] = predecessorNode.mObjects[predecessorNode.value - 1];
                                delete(erasureNode, node.values[i]);                     
                        } else if (rightChildNode.value >= T) { 
                                Node successorNode = rightChildNode;                                    
                                Node erasureNode = successorNode; 
                                while (!successorNode.mIsLeafNode) {
                                        erasureNode = successorNode;
                                        successorNode = successorNode.mChildNodes[0];
                                }
                                node.values[i] = successorNode.values[0];
                                node.mObjects[i] = successorNode.mObjects[0];
                                delete(erasureNode, node.values[i]);
                        } else { 
                                int medianKeyIndex = mergeNodes(leftChildNode, rightChildNode);
                                moveKey(node, i, RIGHT, leftChildNode, medianKeyIndex); 
                                delete(leftChildNode, key);
                        }                       
                } else { 
                        i = node.subtreeRootNodeIndex(key);
                        Node childNode = node.mChildNodes[i];                               
                        if (childNode.value == T - 1) {
                                Node leftChildSibling = (i - 1 >= 0) ? node.mChildNodes[i - 1] : null;
                                Node rightChildSibling = (i  + 1 <= node.value) ? node.mChildNodes[i + 1] : null;
                                if (leftChildSibling != null && leftChildSibling.value >= T) {                                            
                                        childNode.shiftRightByOne();
                                        childNode.values[0] = node.values[i - 1]; 
                                        childNode.mObjects[0] = node.mObjects[i - 1];
                                        if (!childNode.mIsLeafNode) {
                                                childNode.mChildNodes[0] = leftChildSibling.mChildNodes[leftChildSibling.value];
                                        }
                                        childNode.value++;
                                        node.values[i - 1] = leftChildSibling.values[leftChildSibling.value - 1];
                                        node.mObjects[i - 1] = leftChildSibling.mObjects[leftChildSibling.value - 1];
                                        leftChildSibling.remove(leftChildSibling.value - 1, RIGHT);    
                                        
                                } else if (rightChildSibling != null && rightChildSibling.value >= T) {
                                	
                                        childNode.mObjects[childNode.value] = node.mObjects[i];
                                        if (!childNode.mIsLeafNode) {
                                                childNode.mChildNodes[childNode.value + 1] = rightChildSibling.mChildNodes[0];
                                        }
                                        childNode.value++;
                                        node.values[i] = rightChildSibling.values[0];
                                        node.mObjects[i] = rightChildSibling.mObjects[0];                                                
                                        rightChildSibling.remove(0, LEFT);
                                } else { 
                                        if (leftChildSibling != null) {
                                                int medianKeyIndex = mergeNodes(childNode, leftChildSibling);
                                                moveKey(node, i - 1, LEFT, childNode, medianKeyIndex);                     
                                        } else if (rightChildSibling != null) {
                                                int medianKeyIndex = mergeNodes(childNode, rightChildSibling);
                                                moveKey(node, i, RIGHT, childNode, medianKeyIndex);
                                        }                                               
                                }
                        }
                        delete(childNode, key);
                }
        }
}
    
    public int mergeNodes(Node dstNode, Node srcNode) {            
        int medianKeyIndex;
        if (srcNode.values[0] < dstNode.values[dstNode.value - 1]) {                   
                int i;
               
                if (!dstNode.mIsLeafNode) {
                        dstNode.mChildNodes[srcNode.value + dstNode.value + 1] = dstNode.mChildNodes[dstNode.value];
                }
                for (i = dstNode.value; i > 0 ; i--) {
                        dstNode.values[srcNode.value + i] = dstNode.values[i - 1];
                        dstNode.mObjects[srcNode.value + i] = dstNode.mObjects[i - 1];
                        if (!dstNode.mIsLeafNode) {
                                dstNode.mChildNodes[srcNode.value + i] = dstNode.mChildNodes[i - 1];
                        }
                }
                
                medianKeyIndex = srcNode.value;
                dstNode.values[medianKeyIndex] = 0;
                dstNode.mObjects[medianKeyIndex] = null;
                
                for (i = 0; i < srcNode.value; i++) {
                        dstNode.values[i] = srcNode.values[i];
                        dstNode.mObjects[i] = srcNode.mObjects[i];
                        if (!srcNode.mIsLeafNode) {
                                dstNode.mChildNodes[i] = srcNode.mChildNodes[i];
                        }
                }
                if (!srcNode.mIsLeafNode) {
                        dstNode.mChildNodes[i] = srcNode.mChildNodes[i];
                }
        } else {
        	
                medianKeyIndex = dstNode.value;
                dstNode.values[medianKeyIndex] = 0;
                dstNode.mObjects[medianKeyIndex] = null;
                
                int offset = medianKeyIndex + 1;
                int i;                  
                for (i = 0; i < srcNode.value; i++) {
                        dstNode.values[offset + i] = srcNode.values[i];
                        dstNode.mObjects[offset + i] = srcNode.mObjects[i];
                        if (!srcNode.mIsLeafNode) {
                                dstNode.mChildNodes[offset + i] = srcNode.mChildNodes[i];
                        }
                }
                if (!srcNode.mIsLeafNode) {
                        dstNode.mChildNodes[offset + i] = srcNode.mChildNodes[i];
                }
        }
        dstNode.value += srcNode.value;
        return medianKeyIndex;
    }

    public void moveKey(Node srcNode, int srcKeyIndex, int childIndex, Node dstNode, int medianKeyIndex) {
        dstNode.values[medianKeyIndex] = srcNode.values[srcKeyIndex];
        dstNode.mObjects[medianKeyIndex] = srcNode.mObjects[srcKeyIndex];
        dstNode.value++;
        
        srcNode.remove(srcKeyIndex, childIndex);
        
        if (srcNode == mRootNode && srcNode.value == 0) {
                mRootNode = dstNode;
        }
	}       

	public Object search(int key) {
        return search(mRootNode, key);
	}

	public Object search(Node node, int key) {              
        int i = 0;
        
        while (i < node.value && key > node.values[i]) {
                i++;
        }
        
        if (i < node.value && key == node.values[i]) {
                return node.mObjects[i];
        }
        if (node.mIsLeafNode) {
                return null;
        } else {
                return search(node.mChildNodes[i], key);
        }       
	}
	
	public Object search2(int key) {
        return search2(mRootNode, key);
	}

	public Object search2(Node node, int key) {
        while (node != null) {
                int i = 0;
                while (i < node.value && key > node.values[i]) {
                        i++;
                }
                if (i < node.value && key == node.values[i]) {                                
                        return node.mObjects[i];
                }
                if (node.mIsLeafNode) {
                        return null;
                } else {
                        node = node.mChildNodes[i];
                }
        }
        return null;
	}
	
	private boolean update(Node node, int key, Object object) {
        while (node != null) {
                int i = 0;
                while (i < node.value && key > node.values[i]) {
                        i++;
                }
                if (i < node.value && key == node.values[i]) {
                        node.mObjects[i] = object;
                        return true;
                }
                if (node.mIsLeafNode) {
                        return false;
                } else {
                        node = node.mChildNodes[i];
                }
        }
        return false;
	}
    
	public String print(Node node) {
        String string = "";
        if (node != null) {
                if (node.mIsLeafNode) {
                        for (int i = 0; i < node.value; i++) {
                                string += node.mObjects[i] + ", ";
                        }
                } else {
                        int i;
                        for (i = 0; i < node.value; i++) {
                                string += print(node.mChildNodes[i]);
                                string += node.mObjects[i] + ", ";
                        }
                        string += print(node.mChildNodes[i]);
                }                       
        }
        return string;
	}

	public String toString() {
        return print(mRootNode);
	}
	
	public void validate() throws Exception {
        ArrayList<Integer> array = getKeys(mRootNode);
        for (int i = 0; i < array.size() - 1; i++) {            
                if (array.get(i) >= array.get(i + 1)) {
                        throw new Exception("B-Tree invalid: " + array.get(i)  + " greater than " + array.get(i + 1));
                }
        }           
    }
	
	public ArrayList<Integer> getKeys(Node node) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        if (node != null) {
                if (node.mIsLeafNode) {
                        for (int i = 0; i < node.value; i++) {
                                array.add(node.values[i]);
                        }
                } else {
                        int i;
                        for (i = 0; i < node.value; i++) {
                                array.addAll(getKeys(node.mChildNodes[i]));
                                array.add(node.values[i]);
                        }
                        array.addAll(getKeys(node.mChildNodes[i]));
                }                       
        }
        return array;
}
	
    public void clear() {
    	this.mRootNode = null;
    }

    public boolean contains(int value) {
        return false;
    }

    public int size() {
        return size;
    }
}