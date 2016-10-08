package fr.team12.mis;

public class UnorderedTuple<T>
{
    private final T fst;
    private final T snd;

    public UnorderedTuple(T fst, T snd)
    {
        this.fst = fst;
        this.snd = snd;
    }

    public T getFst() { return fst; }
    public T getSnd() { return snd; }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof UnorderedTuple<?>))
            return false;

        UnorderedTuple<T> tuple = (UnorderedTuple<T>)o;
        return (tuple.fst.equals(fst) && tuple.snd.equals(snd)) ||
            (tuple.fst.equals(snd) && tuple.snd.equals(fst));
    }

    @Override
    public String toString()
    {
        return "("+ fst.toString() + ", " + snd.toString() +")";
    }

    @Override
    public int hashCode()
    {
        return 0;
    }
}
