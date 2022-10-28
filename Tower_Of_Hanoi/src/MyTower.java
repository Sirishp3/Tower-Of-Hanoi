import java.util.ArrayList;

public class MyTower<E> implements Tower_Interface<E> {
    private ArrayList<E> data = new ArrayList<>();

    public MyTower()
    {
        this.data = data;
    }

    @Override
    public void push(E o)
    {
        data.add(0,o);
    }

    @Override
    public E peek()
    {
        E val = data.get(0);
        return val;
    }

    @Override
    public E pop()
    {
        return data.remove(0);
    }

    @Override
    public boolean empty() {
        if(data.size()==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public E get(int x) {
        return data.get(x);
    }

    @Override
    public void clear()
    {
        data = new ArrayList<>();
    }
}