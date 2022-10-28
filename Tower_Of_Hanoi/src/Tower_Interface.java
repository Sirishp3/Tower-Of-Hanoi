import java.util.ArrayList;

public interface Tower_Interface<E>
{
    public void push(E o);
    public E peek();
    public E pop();
    public boolean empty();
    public int size();
    public E get(int x);
    public void clear();
}
