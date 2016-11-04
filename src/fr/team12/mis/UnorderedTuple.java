package fr.team12.mis;

/**
 * Represent a kind tuple that can be compared with another tuple that having
 * inversed value.
 */
public class UnorderedTuple<T>
{
    private final T fst; ///< First value
    private final T snd; ///< Second value

    /**
     * Construct new tuple.
     *
     * @param fst The first value.
     * @param snd The second value.
     */
    public UnorderedTuple(T fst, T snd)
    {
        this.fst = fst;
        this.snd = snd;
    }

    /**
     * Return the first value.
     *
     * @return the first value.
     */
    public T getFst() { return fst; }

    /**
     * Return the second value.
     *
     * @return the second value.
     */
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
