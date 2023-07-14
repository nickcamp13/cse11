/*
 * Name:        Nicholas Campos
 * Email:       nicampos@ucsd.edu
 * PID:         A17621673
 * References:  Lecture Notes, JDK
 *
 * This file contains the implementation of the MyStringBuilder class which
 * is a reimplementation of the StringBuilder class.
 */

 /**
  * Reimplementation of StringBuilder. Methods include deep copying a single
  * MyStringBuilder onto another, adding a char or String to the end of an
  * existing MyStringBuilder, and returning a String of a MyStringBuilder
  * given a starting index and an ending index.
  */
public class MyStringBuilder {
    private CharNode start;
    private CharNode end;
    private int length;

    private static final String ARRAY_INDEX_OB = "Starting Index Out of Bounds";

    /**
     * Creates a MyStringBuilder with a single CharNode
     * representing the input.
     *
     * @param ch the char used to create the first CharNode
     */
    public MyStringBuilder(char ch) {
        start = new CharNode(ch);
        end = start;
        length = 1;
    }
    /**
     * Creates a MyStringBuilder from a String.
     *
     * @param str the String used to create a new MyStringBuilder
     */
    public MyStringBuilder(String str) {
        if (str.equals("")) {
            makeEmpty();
            return;
        }
        strToMSB(str);
    }
    /**
     * Creates a MyStringBuilder using a MyStringBuilder object
     * by deep copying its contents.
     *
     * @param other the MyStringBuilder used to deep copy on to the new
     *              MyStringBuilder
     */
    public MyStringBuilder(MyStringBuilder other) {
        try {
            if (other.isEmpty()) {
                makeEmpty();
                return;
            }
            start = new CharNode(other.start.getData());
            length = 1;
            CharNode currentNode = start;
            CharNode nextOtherNode = other.start.getNext();
            while (nextOtherNode != null) {
                currentNode.setNext(new CharNode(nextOtherNode.getData()));
                length++;
                currentNode = currentNode.getNext();
                nextOtherNode = nextOtherNode.getNext();
            }
            end = currentNode;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the current length of MyStringBuilder.
     *
     * @return length
     */
    public int length() {
        return length;
    }

    /**
     * Appends a single char to the end of MyStringBuilder.
     *
     * @param ch the character to add to MyStringBuilder
     * @return
     */
    public MyStringBuilder append(char ch) {
        if (isEmpty()) {
            start = new CharNode(ch);
            end = start;
            length = 1;
            return this;
        }
        CharNode newEnd = new CharNode(ch);
        end.setNext(newEnd);
        end = newEnd;
        length++;
        return this;
    }

    /**
     * Appends an entire String to the end of the
     * current MyStringBuilder object.
     *
     * @param str the String to be added to the MyStringBuilder
     * @return the updated MyStringBuilder
     */
    public MyStringBuilder append(String str) {
        try {
            if (str.equals("")) {
                return this;
            } else if (isEmpty()) {
                return strToMSB(str);
            }
            MyStringBuilder ending = new MyStringBuilder(str);
            end.setNext(ending.start);
            end = ending.end;
            length += ending.length();
            return this;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * This function takes this sequence of CharNodes
     * and turns the sequence of chars into a String
     *
     * @return String of characters in the MyStringBuilder
     */
    public String toString() {
        String result = "";
        if (isEmpty()) {
            return result;
        }
        CharNode currentNode = start;
        for (int i = 0; i < length; ++i) {
            result += currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return result;
    }

    /**
     * Advances MyStringBuilder iterator to specified starting index
     *
     * @param startIdx the index to advance to
     * @param node starting node to advance from
     * @return node to begin operating on
     */
    private CharNode goToStartIdx(int startIdx, CharNode node) {
        if (startIdx < 0) {
            throw new IndexOutOfBoundsException(startIdx);
        }
        for (int i = 0; i < startIdx; ++i) {
            if (i >= length - 1) {
                throw new IndexOutOfBoundsException(i + 1);
            }
            node = node.getNext();
        }
        return node;
    }

    /**
     * Returns a substring starting from startIdx to the end.
     *
     * @param startIdx the index of MyStringBuilder to start returning
     * @return String of characters starting at startIdx
     */
    public String subString(int startIdx) {
        String result = "";
        CharNode currentNode = start;
        currentNode = goToStartIdx(startIdx, currentNode);
        for (int i = startIdx; i < length; ++i) {
            if (currentNode.getNext() == null && i < length - 1) {
                throw new IndexOutOfBoundsException(startIdx);
            }
            result += currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return result;
    }

    /**
     * Returns a substring from startIdx (inclusive) to endIdx (exclusive).
     *
     * @param startIdx the index of MyStringBuilder to start returning
     * @param endIdx the index of MyStringBuilder to stop returning
     * @return String of characters starting at startIdx (inclusive)
     *         and ending at endIdx (exclusive)
     */
    public String subString(int startIdx, int endIdx) {
        String result = "";
        CharNode currentNode = start;
        currentNode = goToStartIdx(startIdx, currentNode);
        for (int i = startIdx; i < endIdx; ++i) {
            if (i >= length) {
                throw new IndexOutOfBoundsException(i);
            }
            result += currentNode.getData();
            currentNode = currentNode.getNext();
        }
        if (startIdx == endIdx) {
            return "";
        }
        return result;
    }

    /**
     * Takes a String and returns it as a MyStringBuilder
     *
     * @param str the String to be duplicated as a MyStringBuilder
     * @return MyStringBuilder representation of str
     */
    private MyStringBuilder strToMSB(String str) {
        start = new CharNode(str.charAt(0));
        length = 1;
        CharNode currentNode = start;
        for (int i = 1; i < str.length(); ++i) {
            currentNode.setNext(new CharNode(str.charAt(i)));
            currentNode = currentNode.getNext();
            ++length;
        }
        end = currentNode;
        return this;
    }

    /**
     * Checks if MyStringBuilder is empty
     *
     * @return true if all instance variables are 0 values, false otherwise
     */
    private boolean isEmpty() {
        if (start == null && end == null && length == 0) {
            return true;
        }
        return false;
    }

    /**
     * Empties MyStringBuilder
     */
    private void makeEmpty() {
        start = null;
        end = null;
        length = 0;
    }
}